/*
 * HeadCountManager.c
 *
 *  Created on: Mar 4, 2017
 *      Author: pi
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

			currentAmount++;
			system("espeak \" Welcome! \" > /dev/null");
			system("clear");

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
			printf("Current Number of People: %d\n", currentAmount);
		}
		else if (digitalRead(OUTBUTTON) == 0) {

			currentAmount--;
			system("espeak \" Goodbye! \" > /dev/null");
			system("clear");

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
			printf("Current Number of People: %d\n", currentAmount);
		}

		if (currentAmount >= 3)
			digitalWrite(GRNLED, 1);
		else
			digitalWrite(GRNLED, 0);

	}

	system("espeak \" Number of people cannot be less than 0 \" > /dev/null");
	system("clear");
	system("figlet Number of people cannot be less than \' 0 \' ");

}

