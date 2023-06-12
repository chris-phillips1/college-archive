package transportation;

import java.math.BigDecimal;

public class Airplane extends Transportation implements Travel {

	public Airplane()
	{
		
	}
	
	/*
	 * This method prints out how the user can book an airplane
	 * @return String This returns the reservation text
	 */

	public String howToBook() {
		return "How to book: Make a reservation on Travelocity";
	}
	
	public String toString()
	{
		return "Airplane";
	}

	public int compareTo(Object o) {
		return 0;
	}

	/* 
	 * This method calculates the odds of perishing in an airplane
	 * @return String This returns the odds of perishing in an airplane in BigDecimal format
	 */
	public String getSafetyInfo() {
		String retval = "The odds of perishing due to an accident on a single commercial flight is\n";
		retval += new BigDecimal(1.0/294000000) + "\n";
		return retval;
	}

	
	
}
