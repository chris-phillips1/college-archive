package transportation;

import java.math.BigDecimal;

public class RocketShip extends Transportation implements Travel {

	public RocketShip()
	{
		
	}
	
	/*
	 * This method prints out how the user can book a rocket ship
	 * @return String This returns the reservation text
	 */
	
	public String howToBook() {
		return "How to book: Become an astronaut";
	}
	
	public String toString()
	{
		return "Rocketship";
	}

	public int compareTo(Object o) {
		return 0;
	}
	
	/* 
	 * This method calculates the odds of perishing in a rocket ship
	 * @return String This returns the odds of perishing in a rocket ship in BigDecimal format
	 */

	public String getSafetyInfo() {
		String retval = "The odds of perishing due to an accident on a single rocketship flight is\n";
		retval += new BigDecimal(1.0/100) + "\n";
		return retval;
	}
	
}
