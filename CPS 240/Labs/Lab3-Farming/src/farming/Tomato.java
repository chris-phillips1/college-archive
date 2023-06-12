package farming;

public class Tomato extends Veggie {

	private String typeTomato;
	private String color = "";
	
	public Tomato()
	{
		
	}
	
	public Tomato(String typeTomato, String datePlanted) 
	{
		super(datePlanted);
		this.color = "red";
		this.typeTomato = typeTomato;
	}
	
	public String toString()
	{
		return "I am a " + this.typeTomato + " tomato, my color is " + this.color + 
				" and I was planted on " + super.getDatePlanted() + "\n";
	}
		
}
