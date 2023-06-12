/*
 * HeadCountManager.c
 * CPS 210
 * Chris Phillips
 * 3/4/17
 * Final Project
 *
 * Description:
 * The HeadCountManger system allows multiple
 * users to check in and out of a dorm room and
 * helps dorm members know if there are other
 * people in the room. This project also uses
 * sounds and large, readable text to be useful
 * in real world situations.
 */

#include <wiringPi.h>
#include <stdio.h>

const int INBUTTON = 22;
const int OUTBUTTON = 4;
const int GRNLED = 6;

int main(void) {

	wiringPiSetupGpio();
	pinMode(INBUTTON, INPUT);
	pinMode(OUTBUTTON, INPUT);
	pinMode(GRNLED, OUTPUT);
	pullUpDnControl(INBUTTON, PUD_UP);
	pullUpDnControl(OUTBUTTON, PUD_UP);

	int currentAmount = 0;

	system("espeak \" Welcome to the Head Count Manager System! \" > /dev/null");

	while (currentAmount >= 0) {

		if (digitalRead(INBUTTON) == 0) {

			//If the in button is pressed increment currentAmount, say welcome and clear the console
			currentAmount++;
			system("espeak \" Welcome! \" > /dev/null");
			system("clear");

			//Based on new currentAmount, have the computer say something
			//clear the console, then display what the computer said in big letters
			switch (currentAmount) {
			case -1:
				system("espeak \" Not enough people in the room \" > /dev/null");
				system("clear");
				system("figlet Not enough people in the room");
				break;
			case 0:
				system("espeak \" 0 people in the room \" > /dev/null");
				system("clear");
				system("figlet 0 people in the room");
				break;
			case 1:
				system("espeak \" 1 person in the room \" > /dev/null");
				system("clear");
				system("figlet 1 person in the room");
				break;
			case 2:
				system("espeak \" 2 people in the room \" > /dev/null");
				system("clear");
				system("figlet 2 people in the room");
				break;
			case 3:
				system("espeak \" 3 people in the room \" > /dev/null");
				system("clear");
				system("figlet 3 people in the room");
				break;
			case 4:
				system("espeak \" 4 people in the room \" > /dev/null");
				system("clear");
				system("figlet 4 people in the room");
				break;
			case 5:
				system("espeak \" 5 people in the room \" > /dev/null");
				system("clear");
				system("figlet 5 people in the room");
				break;
			case 6:
				system("espeak \" 6 people in the room \" > /dev/null");
				system("clear");
				system("figlet 6 people in the room");
				break;
			case 7:
				system("espeak \" 7 people in the room \" > /dev/null");
				system("clear");
				system("figlet 7 people in the room");
				break;
			case 8:
				system("espeak \" 8 people in the room \" > /dev/null");
				system("clear");
				system("figlet 8 people in the room");
				break;
			case 9:
				system("espeak \" 9 people in the room \" > /dev/null");
				system("clear");
				system("figlet 9 people in the room");
				break;
			case 10:
				system("espeak \" 10 people in the room \" > /dev/null");
				system("clear");
				system("figlet 10 people in the room");
				break;
			default:
				system("espeak \" Too many people in the room \" > /dev/null");
				system("clear");
				system("figlet Too many people in the room");
				break;
			}
			//Print the current number of people in a more polished and readable (but smaller) manner, below big letters
			printf("Current Number of People: %d\n", currentAmount);
		}
		else if (digitalRead(OUTBUTTON) == 0) {

			//If the out button is pressed decrement currentAmount, say goodbye and clear the console
			currentAmount--;
			system("espeak \" Goodbye! \" > /dev/null");
			system("clear");

			//Based on new currentAmount, have the computer say something
			//clear the console, then display what the computer said in big letters
			switch (currentAmount) {
			case -1:
				system("espeak \" Not enough people in the room \" > /dev/null");
				system("clear");
				system("figlet Not enough people in the room");
				break;
			case 0:
				system("espeak \" 0 people in the room \" > /dev/null");
				system("clear");
				system("figlet 0 people in the room");
				break;
			case 1:
				system("espeak \" 1 person in the room \" > /dev/null");
				system("clear");
				system("figlet 1 person in the room");
				break;
			case 2:
				system("espeak \" 2 people in the room \" > /dev/null");
				system("clear");
				system("figlet 2 people in the room");
				break;
			case 3:
				system("espeak \" 3 people in the room \" > /dev/null");
				system("clear");
				system("figlet 3 people in the room");
				break;
			case 4:
				system("espeak \" 4 people in the room \" > /dev/null");
				system("clear");
				system("figlet 4 people in the room");
				break;
			case 5:
				system("espeak \" 5 people in the room \" > /dev/null");
				system("clear");
				system("figlet 5 people in the room");
				break;
			case 6:
				system("espeak \" 6 people in the room \" > /dev/null");
				system("clear");
				system("figlet 6 people in the room");
				break;
			case 7:
				system("espeak \" 7 people in the room \" > /dev/null");
				system("clear");
				system("figlet 7 people in the room");
				break;
			case 8:
				system("espeak \" 8 people in the room \" > /dev/null");
				system("clear");
				system("figlet 8 people in the room");
				break;
			case 9:
				system("espeak \" 9 people in the room \" > /dev/null");
				system("clear");
				system("figlet 9 people in the room");
				break;
			case 10:
				system("espeak \" 10 people in the room \" > /dev/null");
				system("clear");
				system("figlet 10 people in the room");
				break;
			default:
				system("espeak \" Too many people in the room \" > /dev/null");
				system("clear");
				system("figlet Too many people in the room");
				break;
			}
			//Print the current number of people in a more polished and readable (but smaller) manner, below big letters
			printf("Current Number of People: %d\n", currentAmount);
		}

		//If the currentAmount is 2 or greater, light up the Green LED
		if (currentAmount >= 3)
			digitalWrite(GRNLED, 1);
		else
			digitalWrite(GRNLED, 0);

	}

	//Print out the following statements when the currentAmount variable is less than zero
	system("espeak \" Number of people cannot be less than 0 \" > /dev/null");
	system("clear");
	system("figlet Number of people cannot be less than \' 0 \' ");

}

