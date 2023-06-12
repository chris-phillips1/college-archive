package inherit1;

public class TheMain { //Creates a public class TheMain

	public static void main(String[] args) { //Creates a main method
		
		Vehicle car = new Vehicle(4, 100.00); //Creates a new vehicle car with 4 wheels and the speed 100
		Vehicle tank = new Vehicle(8, 50); //Creates a new vehicle tank with 8 wheels and the speed 50
		System.out.println(" Cars Max Speed " + car.getSpeed()); //Prints out "Cars Max Speed 100"
		System.out.println(" Cars number of Wheels " + car.getWheels()); //Prints out "Cars number of Wheels 4"
		
		car.setCarStrength(10); //Sets the car vehicle's strength to 10
		
		System.out.println(" Car Strength " + car.getStrength()); //Prints out "Car Strength 10"
		
		Object superCar = new Vehicle(); //Creates a new object superCar that is a Vehicle
		Vehicle superTruck = new Vehicle(); //Creates a new vehicle superTruck

		
		System.out.println(((Vehicle)superCar).getSpeed()); //Casts superCar as a vehicle and calls the getSpeed method
		System.out.println(superCar.equals(superTruck)); //Checks if superTruck and superCar are equal
		
		System.out.println(superCar.getClass()); //Prints superCar's class
		
		System.out.println(superCar.getClass().getName()); //Prints superCar's class's name
		if(superCar.getClass() == superTruck.getClass()) //Checks if superCars class is equal to superTrucks class
		{
			System.out.println(" They are equal"); //Prints "They are equal"
		}
		
		System.out.println(superCar.toString()); //Prints using superCar's toString method

		System.out.println(superTruck.hashCode()); //Prints superTruck's hashCode
		
	}

}
