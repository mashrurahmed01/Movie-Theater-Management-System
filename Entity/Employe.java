package Entity;
public class Employe{
	private int id;
	private String name;
	private String designation;
	private double salary;
	

	public Employe(int id,String name,String designation,double salary){
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary ;
	}

	public String getdesignation() {
		return designation;
	}

	public void setdesignation(String designation) {
		this.designation = designation;
	}

	public void setID(int id){this.id = id;}
	public int getID(){return id;}

	public void setName(String name){this.name = name;}
	public String getName(){return this.name;}

	public void setsalary(double salary)
	{this.salary = salary;
	
	}
	public double getsalary(){return this.salary;}
		
	
	
	public void show(){
		System.out.println("Employe Details : ");
		System.out.println(" ");
		System.out.println("ID               : "+id);
		System.out.println("Name             : "+name);
		System.out.println("Designation    : "+designation);
		System.out.println("Salary     : "+salary);
		System.out.println("====================");
	}	

}