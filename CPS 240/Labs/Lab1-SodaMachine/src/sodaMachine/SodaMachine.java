package sodaMachine;

import java.util.Scanner;

public class SodaMachine {

	private static int DrPepperCount = 3; 
	private static int CokeCount = 3; 
	private static int FantaCount = 3; 
	private static int DietCokeCount = 3;
	
	public SodaMachine()
	{
		
	}
	
	public void getSoda() throws InterruptedException
	{
		int selection = selectionMenu();

		while(selection != 5)
		{
			switch(selection) {
		
			case 1: 
				if(DrPepperCount >= 1)
				{
					DrPepperCount--;
					System.out.println("You recieved a can of Dr. Pepper.");
				}
				else
					System.out.println("No more Dr. Pepper available!");
				break;
			case 2:
				if(CokeCount >= 1)
				{
					CokeCount--;
					System.out.println("You recieved a can of Coke.");
				}
				else
					System.out.println("No more Coke available!");
				break;
			case 3:
				if(FantaCount >= 1)
				{
					FantaCount--;
					System.out.println("You recieved a can of Fanta.");
				}
				else
					System.out.println("No more Fanta available!");
				break;
			case 4:
				if(DietCokeCount >= 1)
				{
					DietCokeCount--;
					System.out.println("You recieved a can of Diet Coke.");
				}
				else
					System.out.println("No more Diet Coke available!");
				break;
			default: 
				System.out.println("Invalid Entry");
				break;
			}
		Thread.sleep(1250);
		selection = selectionMenu();
		}
	}
	
	public int selectionMenu()
	{
		Scanner selectionScan = new Scanner(System.in);
		int theSelection = 0;
		
		System.out.println("Select which soda you would like: ");
		System.out.println("1) Dr. Pepper ($1.25) " + DrPepperCount + " Remaining");
		System.out.println("2) Coke ($1.00)" + "       " + CokeCount + " Remaining");
		System.out.println("3) Fanta ($1.25)" + "      " +FantaCount + " Remaining");
		System.out.println("4) Diet Coke ($1.00)" + "  " + DietCokeCount + " Remaining");
		System.out.println("5) Cancel");
		
		theSelection = selectionScan.nextInt();
		
		return theSelection;
	}
}
