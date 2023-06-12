package farming;

public class Veggie {

	private String datePlanted = "";
	
	public Veggie()
	{
		
	}
	
	public Veggie(String datePlanted) 
	{
		this.datePlanted = datePlanted;
	}
	
	public String getDatePlanted()
	{
		return this.datePlanted;
	}
	
	public String toString()
	{
		return "I am an unknown type of vegetable and I was planted on " + datePlanted + "\n";
	}
	

}