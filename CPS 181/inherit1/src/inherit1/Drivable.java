package inherit1;

public interface Drivable { //Create a public interface called Drivable

	double PI = 3.141592;  // This is final in an interface
						   // Initializes a double variable PI to 3.141592
	int getWheels(); //Defines an int method getWheels that must be implemented in any class that implements this interface.
	
	void setWheels(int numWheels); //Defins a method setWheels that takes in an int numWheels and returns nothing. This must be implemented.
	
	double getSpeed(); //Defines a double method getSpeed that must be implemented.
	
	void setSpeed(double speed); //Defines a method setSpeed that takes in a double speed and returns nothing. This must be implemented.
}