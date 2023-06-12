package transportation;

import java.math.BigDecimal;

public abstract class Transportation implements Comparable {

	private BigDecimal stat;
	
	protected void setState(BigDecimal newStat)
	{
		stat = newStat;
	}
	
	protected BigDecimal getStat()
	{
		return stat;
	}
	
	/*
	 * This method compares the stat of two transportation objects
	 * It uses the BigDecimal compareTo method to see how they compare
	 * @param transport This is the object you want to compare
	 * @return int This returns a number based on how they compare
	 */
	public int compareTo(Transportation transport)
	{
		if(stat.compareTo(transport.getStat()) < 0)
			return -1;
		else if(stat.compareTo(transport.getStat()) > 0)
			return 1;
		else
			return 0;
	}
	
	public abstract String getSafetyInfo();
}
