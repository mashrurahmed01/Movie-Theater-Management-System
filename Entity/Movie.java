package Entity;
public class Movie{
	private int id;
	private String movieName;
	private double ticketPrice;
	private int seatsAvailable;

	public Movie(int id,String movieName){
		this.id = id;
		this.movieName = movieName;
	}

	public Movie(int id,String movieName, int seatsAvailable,double ticketPrice){
		this.id = id;
		this.movieName = movieName;
		this.ticketPrice = ticketPrice;
		this.seatsAvailable = seatsAvailable;
	}
	public void setMovieName(String movieName){this.movieName = movieName;}
	public String getMovieName(){return this.movieName;}
	public void setID(int id){this.id = id;}

	public int getID(){return id;}
	public void setTicketPrice(double ticketPrice){this.ticketPrice = ticketPrice;}

	public double getTicketPrice(){return ticketPrice;}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public void show(){
		System.out.println("Movie ID      : "+id);
		System.out.println("Movie Name      : "+movieName);
		System.out.println("Ticket Price    : "+ticketPrice);
		System.out.println("Seats Available : "+seatsAvailable);
	}	   
}