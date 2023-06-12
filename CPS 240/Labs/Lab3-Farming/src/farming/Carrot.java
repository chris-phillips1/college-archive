package farming;

public class Carrot extends Veggie {
	
	private String typeCarrot;
	private String color = "";
	
	public Carrot()
	{
		
	}
	
	public Carrot(String typeCarrot, String datePlanted) 
	{
		super(datePlanted);
		this.color = "orange";
		this.typeCarrot = typeCarrot;
	}

	
	public String toString()
	{
		return "I am a " + this.typeCarrot + " carrot, my color is " + this.color + 
				" and I was planted on " + super.getDatePlanted() + "\n";
	}
	
	
}
