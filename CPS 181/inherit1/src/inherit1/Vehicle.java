package inherit1;

public class Vehicle extends Crashable implements Drivable { //Creates a public class called Vehicle that extends the abstract Crashable class and implements the interface Drivable
	int numOfWheels = 2; //Initializes an int variable numOfWheels to 2									 
	double theSpeed = 0; //Initializes a double variable theSpeed to 0
	int carStrength = 0; //Initializes an int variable carStrength to 0

	public Vehicle() //Creates a default constructor for the Vehicle class
	{
		
	}
	
	public Vehicle(int wheels, double speed) //Creates a constructor for the Vehicle class that takes in an int input wheels and double input speed.
	{

		this.numOfWheels = wheels; //Sets the objects numOfWheels variable to the wheels variable taken in by the constructor
		this.theSpeed = speed; //Sets the objects theSpeed variable to the speed variable taken in by the constructor
	}
	
	public int getWheels() { //Creates a public method getWheels that returns an int
		return this.numOfWheels; //Returns the objects numOfWheels variable
	}

	public void setWheels(int numWheels) { //Creates a public method setWheels that takes in an int numWheels and returns nothing
		this.numOfWheels = numWheels; //Sets the objects numOfWheels variable to the numWheels variable taken in
		
	}

	public double getSpeed() { //Creates a public method getSpeed that returns a double
		return this.theSpeed; //Returns the objects theSpeed variable
	}

	public void setSpeed(double speed) { //Creates a public method setSpeed that takes in a double speed and returns nothing
		this.theSpeed = speed; //Sets the objects theSpeed variable to the speed variable taken in
	}
	
	public void setCarStrength(int carStrength) { //Creates a public method setCarStrength that takes in an int carStrength and returns nothing
		this.carStrength = carStrength; //Sets the objects carStrength variable to the carStrength variable taken in
	}
	
	public int getStrength() { //Creates a public method getStrength that returns an int
		return this.carStrength; //Returns the objects carStrength variable
	}
	
//	public String toString()
//	{
//		String retval;
//		retval = numOfWheels + " " +theSpeed;
//		return retval;
//	}
	public int hashCode() //Creates a public method hashCode that returns an int
	{
		return -1; //Returns -1
	}

}