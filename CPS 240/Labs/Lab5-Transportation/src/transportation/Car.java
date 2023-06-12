package transportation;

import java.math.BigDecimal;

public class Car extends Transportation implements Travel{
	
	public Car()
	{
		
	}
	
	/*
	 * This method prints out how the user can book a car
	 * @return String This returns the reservation text
	 */
	
	public String howToBook()
	{
		return "How to book: Make a reservation at Enterprise";
	}
	
	
	public String toString()
	{
		return "Car";
	}

	public int compareTo(Object o) {
		return 0;
	}

	/* 
	 * This method calculates the odds of perishing in a car
	 * @return String This returns the odds of perishing in a car in BigDecimal format
	 */
	
	public String getSafetyInfo() {
		String retval = "The odds of perishing due to a car accident per car ride is \n";
		retval += new BigDecimal(1.0/47718) + "\n";
		return retval;
	}
	
}
