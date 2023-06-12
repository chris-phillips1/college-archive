package farming;

import java.util.ArrayList;

public class Farm {

	private ArrayList<Veggie> patchArr = new ArrayList<Veggie>(5); 
	private int numVeggies;
	
	//Used when actually 'planting' the veggies
	Carrot ca;
	Tomato to;
	Veggie ve;
	
	public Farm()
	{
		
	}
	
	
	/*
	 *  This function allows the user to 'plant' a
	 * veggie in one of the plots on the farm in the 
	 * veggie array "patchArr". 
	 * @param v This is the veggie to be planted
	 * @param typeV This the type of the veggie to be planted
	 * @param datePlanted This is when the veggie was planted
	 * @return void
	 */
	public void plant(Veggie v, String typeV, String datePlanted)
	{
		if(numVeggies < 5) //Used to make sure there is an available plot to plant in
		{
			if(v instanceof Carrot) //if the veggie to be planted is a carrot, create a new carrot and add it to the patch
			{
				ca = new Carrot(typeV, datePlanted);
				patchArr.add(ca);
				System.out.println("Planted a carrot in plot " + numVeggies);
				numVeggies++;
			}
			else if(v instanceof Tomato) //if the veggie to be planted is a tomato, create a new tomato and add it to the patch
			{
				to = new Tomato(typeV, datePlanted);
				patchArr.add(to);
				System.out.println("Planted a tomato in plot " + numVeggies);
				numVeggies++;
			}
			else //if the veggie to be planted is unidentified, create a new veggie and add it to the patch
			{
				ve = new Veggie(datePlanted);
				patchArr.add(ve);
				System.out.println("Planted some vegetable in plot " + numVeggies);
				numVeggies++;
			}
		}
		else
			System.out.println("Not enough room to plant.");	
	}
	
	/*
	 * This function allows the user to find how many carrots
	 * in total are in the patch, it simply goes through the
	 * patchArr and if a given veggie is a carrot, adds it to
	 * the return string.
	 * @return String This returns the combined toString of all the carrots in the patch
	 */
	public String getCarrots()
	{
		String retval = "";
		
		for(int i = 0; i < patchArr.size(); i++)
		{
			ve = patchArr.get(i);
			
			if(ve instanceof Carrot)
				retval += ve.toString() + "I am in plot " + i + "\n";
		}
		
		return retval;
	}
	
	/*
	 * This function allows the user to print out all
	 * of the veggies that they are growing on their farm.
	 * @return String This returns all of the veggies on the farm
	 */
	public String toString()
	{
		String retval = "";
		
		for(Veggie v: patchArr)
			retval += v.toString();
		
		return retval;
	}
	
}
