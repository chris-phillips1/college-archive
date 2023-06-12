package transportation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * The Transportation program informs the user of
 * how to book a mode of transportation and the risk
 * of dying in an accident for that mode of transportation
 * 
 * @author Chris Phillips
 * @version 1.0
 * @date February 23, 2017
 * 
 */

public class Main {

	public static void main(String[] args) {
		
		Airplane airplane1 = new Airplane();
		System.out.println(airplane1.howToBook());
		System.out.println(airplane1.getSafetyInfo());
		
		Car car1 = new Car();
		System.out.println(car1.howToBook());
		System.out.println(car1.getSafetyInfo());
		
		RocketShip rocketship1 = new RocketShip();
		System.out.println(rocketship1.howToBook());
		System.out.println(rocketship1.getSafetyInfo());
		
		ArrayList<Transportation> transportArray = new ArrayList<Transportation>();
		transportArray.add(airplane1);
		transportArray.add(car1);
		transportArray.add(rocketship1);
		
		Collections.sort(transportArray);
		System.out.println(Arrays.toString(transportArray.toArray()));
		

	}

}
