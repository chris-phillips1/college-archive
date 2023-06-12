package farming;

/* 
 * The Farming program allows you to create a
 * farm with multiple plots. You may plant 
 * either a tomato, carrot, or generic veggie.
 * The farm also lets see what has been planted
 * where and how many carrots there are.
 *
 * @author Chris Phillips
 * @version 2.0
 * @date February 2nd, 2017
 */

public class Main {

	public static void main(String[] args) {
		
		Farm farm = new Farm();
		
		//These objects are used for when you're planting
		Tomato t = new Tomato();
		Carrot c = new Carrot();
		Veggie v = new Veggie();
		
		farm.plant(t,"beefsteak","1/22/2016");
		farm.plant(c,"napa","1/21/2016");
		farm.plant(c,"parano","1/20/2016");
		farm.plant(v,"unknown","1/16/2016");
		farm.plant(t,"roma","1/25/2016");
		farm.plant(t,"roma","1/21/2016");
		
		System.out.println(farm); //prints out farm's toString statement
		System.out.println(farm.getCarrots()); //calls the getCarrots method in the farm object
		
	}

}
