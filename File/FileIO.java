package File;

import java.util.Scanner;
import java.io.*;
import Entity.*;
import EntityList.*;

public class FileIO {

    public static int checkUser(String uname, String upass, String fname) {
        int status = 0;
        try {
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
				if(row != null){
					String cols[] = row.split(";");

					if (cols.length >= 2) {
						String name = cols[0];
						String pass = cols[1];

						if (uname.equals(name) && upass.equals(pass)) {
							status = 1;
							break;
						} else if (uname.equals(name) && !upass.equals(pass)) {
							status = 2;
						}
					} else {
						System.out.println("Invalid data format: " + row);
					}
				}
            }
            sc.close();
        } catch (FileNotFoundException e) {
            handleFileException("Cannot read from file", e);
        }
        return status;
    }
    public static boolean registerUser(String uname, String upass, String fname) {
        if (checkUser(uname, upass, fname) == 0) {
            writeInFile(uname + ";" + upass, fname, true);
            return true;
        }
        return false;
    }
    public static void readFromFile(String fname) {
        try {
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String cols[] = row.split(";");
                String name = cols[0];
                String pass = cols[1];

                System.out.println("-------------");
                System.out.println("User Name : " + name);
                System.out.println("User Pass : " + pass);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            handleFileException("Cannot read from file", e);
        }
    }

    public static void writeInFile(String line, String fname, boolean append) {
        try {
            FileWriter fw = new FileWriter(new File(fname), append);
            fw.write(line + "\n");
            fw.close();
        } catch (IOException e) {
            handleFileException("Cannot write to file", e);
        }
    }

  
    public static void loadMoviesFromFile(MovieList movies, String fname) {
        try {
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String cols[] = row.split(";");

                int id = Integer.parseInt(cols[0]);
                String movieName = cols[1];
                int seatsAvailable = Integer.parseInt(cols[2]);
                double ticketPrice = Double.parseDouble(cols[3]);

                movies.insert(new Movie(id, movieName, seatsAvailable, ticketPrice));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            handleFileException("Cannot read from file", e);
        }
    }
	
	public static void loadEmployeesFromFile(EmployeList employes, String fname) {
        try {
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String cols[] = row.split(";");

                int id = Integer.parseInt(cols[0]);
                String name = cols[1];
                String designation = cols[2];
                double salary = Double.parseDouble(cols[3]);

                employes.insert(new Employe(id, name, designation, salary));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            handleFileException("Cannot read from file", e);
        }
    }

    public static void saveCustomersToFile(CustomerList customers, String fname) {
        try {
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String cols[] = row.split(";");

                int id = Integer.parseInt(cols[0]);
                String customerName = cols[1];
                int ticketsBought = Integer.parseInt(cols[2]);
                double totalSpent = Double.parseDouble(cols[3]);

                customers.insert(new Customer(id, customerName, ticketsBought, totalSpent));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            handleFileException("Cannot read from file", e);
        }
    }

    private static void handleFileException(String message, Exception e) {
        System.out.println(message);
        e.printStackTrace();
    }
}
