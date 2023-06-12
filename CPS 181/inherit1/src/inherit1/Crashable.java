package inherit1;

public abstract class Crashable { //Creates a public class called Crashable that is abstract
	boolean carDrivable = true; //initializes a boolean carDrivable to true

	public void youCrashed() { //Creates a public method that doesn't return anything named youCrashed
		this.carDrivable = false; //Sets whichever object that is calling this method's carDrivable variable to false
	}
	
	public abstract void setCarStrength(int carStrength);  //Defines an abstract method setCarStrength that is public and doesn't return 
														   //anything and takes in an int carStrength. This method must be can by subclasses.
	
	public abstract int getStrength(); //Defines an abstract method getStrength that returns an int. This method can be used by subclasses.
}