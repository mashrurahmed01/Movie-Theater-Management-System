package EntityList;
import Entity.Employe;
import java.util.ArrayList;

public class EmployeList{
	private ArrayList<Employe> Employes =new ArrayList<Employe>();
	public void insert(Employe c){
		Employes.add(c);
	}
	public Employe getByID(int id){
		for(int i=0; i<Employes.size();i++){
			if(Employes.get(i).getID()==id){
				return Employes.get(i);
			}
		}
		return null;
		
	}
	public boolean removeByID(int id){
		for(int i=0; i<Employes.size();i++){
			if(Employes.get(i).getID()==id){
				 Employes.remove(i);
				 return true;
			}
		}
		return false;		
	}
	public void showAll(){
		for(int i=0; i<Employes.size();i++){
			Employes.get(i).show();
		}
	}
	public ArrayList<Employe> getAll(){
		return Employes;
	}
	
}