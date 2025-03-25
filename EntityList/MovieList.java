package EntityList;
import Entity.Movie;
import java.util.ArrayList;

public class MovieList{
	private ArrayList<Movie> movies =new ArrayList<Movie>();
	public void insert(Movie c){
		movies.add(c);
	}
	public Movie getByID(int id){
		for(int i=0; i<movies.size();i++){
			if(movies.get(i).getID()==id){
				return movies.get(i);
			}
		}
		return null;
		
	}
	public boolean removeByID(int id){
		for(int i=0; i<movies.size();i++){
			if(movies.get(i).getID()==id){
				 movies.remove(i);
				 return true;
			}
		}
		return false;		
	}
	public void showAll(){
		for(int i=0; i<movies.size();i++){
			movies.get(i).show();
		}
	}
	public ArrayList<Movie> getAll(){
		return movies;
	}
	
}