package Entity;
public class Customer{
	private int id;
	private String name;
	private double totalSpent;
	private int ticketsBought;

	public Customer(int id,String name,int ticketsBought, double totalSpent){
		this.id = id;
		this.name = name;
		this.totalSpent= totalSpent;
		this.ticketsBought = ticketsBought;
	}

	public int getTicketsBought() {
		return ticketsBought;
	}

	public void setTicketsBought(int ticketsBought) {
		this.ticketsBought = ticketsBought;
	}

	public void setID(int id){this.id = id;}
	public int getID(){return id;}

	public void setName(String name){this.name = name;}
	public String getName(){return this.name;}

	public void setTotalSpent(double totalSpent)
	{this.totalSpent = totalSpent;
		totalSpent*= ticketsBought;
	}
	public double  getTotalSpent(){return totalSpent;}	
	
	
	public void show(){
		System.out.println("Customer Details : ");
		System.out.println(" ");
		System.out.println("ID               : "+id);
		System.out.println("Name             : "+name);
		System.out.println("Ticket Bought    : "+ticketsBought);
		System.out.println("Total Amount     : "+totalSpent);
		System.out.println("====================");
	}	

}