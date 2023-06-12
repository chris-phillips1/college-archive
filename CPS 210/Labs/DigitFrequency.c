/*
 * DigitFrequency.c
 * CPS 210
 * Chris Phillips
 * 3/16/17
 * Homework 5: Digit Frequency
 *
 * Description:
 * This program creates 200 random numbers and
 * splits them into 3 digits then finds the
 * frequency of each digit and prints that out.
 */

#include <stdlib.h>
#include <time.h>

//int count(int i);

//int main(void) {
//
//	//Variables to keep track of the frequency of each digit
//	int freqOf0 = 0;
//	int freqOf1 = 0;
//	int freqOf2 = 0;
//	int freqOf3 = 0;
//	int freqOf4 = 0;
//	int freqOf5 = 0;
//	int freqOf6 = 0;
//	int freqOf7 = 0;
//	int freqOf8 = 0;
//	int freqOf9 = 0;
//	int digit1, digit2, digit3; //The three digits each number will be split into
//
//	time_t sec;
//	time(&sec);
//
//	srand(sec);
//
//	for (int i = 0; i < 200; i++) {
//
//		int value = rand() % 1000; //Create a random number [0,999]
//		int totalCount = count(value); //Find the number of digits in random number
//
//		switch (totalCount) {
//
//		case 1: //if number of digits is 1, there are two leading zeros
//			digit3 = value;
//			digit2 = 0;
//			digit1 = 0;
//			break;
//		case 2: //if number of digits is 2, there is one leading zero (mod then divide until less than 10)
//			digit3 = value % 10;
//			value /= 10;
//			digit2 = value % 10;
//			value /= 10;
//			digit1 = 0;
//			break;
//		case 3: //if number of digits is 3, there are no leading zeros (mod then divide until less than 10)
//			digit3 = value % 10;
//			value /= 10;
//			digit2 = value % 10;
//			value /= 10;
//			digit1 = value % 10;
//			value /= 10;
//			break;
//		default:
//			printf("Error.");
//			digit3 = -1;
//			digit2 = -1;
//			digit1 = -1;
//			break;
//		}
//
//		switch (digit3) { //Look at the 3rd digit and increment the appropriate frequency
//		case 0:
//			freqOf0++;
//			break;
//		case 1:
//			freqOf1++;
//			break;
//		case 2:
//			freqOf2++;
//			break;
//		case 3:
//			freqOf3++;
//			break;
//		case 4:
//			freqOf4++;
//			break;
//		case 5:
//			freqOf5++;
//			break;
//		case 6:
//			freqOf6++;
//			break;
//		case 7:
//			freqOf7++;
//			break;
//		case 8:
//			freqOf8++;
//			break;
//		case 9:
//			freqOf9++;
//			break;
//		}
//
//		switch (digit2) { //Look at the 2nd digit and increment the appropriate frequency
//		case 0:
//			freqOf0++;
//			break;
//		case 1:
//			freqOf1++;
//			break;
//		case 2:
//			freqOf2++;
//			break;
//		case 3:
//			freqOf3++;
//			break;
//		case 4:
//			freqOf4++;
//			break;
//		case 5:
//			freqOf5++;
//			break;
//		case 6:
//			freqOf6++;
//			break;
//		case 7:
//			freqOf7++;
//			break;
//		case 8:
//			freqOf8++;
//			break;
//		case 9:
//			freqOf9++;
//			break;
//		}
//
//		switch (digit1) { //Look at the 1st digit and increment the appropriate frequency
//		case 0:
//			freqOf0++;
//			break;
//		case 1:
//			freqOf1++;
//			break;
//		case 2:
//			freqOf2++;
//			break;
//		case 3:
//			freqOf3++;
//			break;
//		case 4:
//			freqOf4++;
//			break;
//		case 5:
//			freqOf5++;
//			break;
//		case 6:
//			freqOf6++;
//			break;
//		case 7:
//			freqOf7++;
//			break;
//		case 8:
//			freqOf8++;
//			break;
//		case 9:
//			freqOf9++;
//			break;
//		}
//	}
//
//	//Print out the frequency of each digit, with appropriate amount of stars
//	printf("0(%d): ", freqOf0);
//	for (int i = 0; i < freqOf0; i++) {
//		printf("*");
//	}
//	printf("\n");
//
//	printf("1(%d): ", freqOf1);
//	for (int i = 0; i < freqOf1; i++) {
//		printf("*");
//	}
//	printf("\n");
//
//	printf("2(%d): ", freqOf2);
//	for (int i = 0; i < freqOf2; i++) {
//		printf("*");
//	}
//	printf("\n");
//
//	printf("3(%d): ", freqOf3);
//	for (int i = 0; i < freqOf3; i++) {
//		printf("*");
//	}
//	printf("\n");
//
//	printf("4(%d): ", freqOf4);
//	for (int i = 0; i < freqOf4; i++) {
//		printf("*");
//	}
//	printf("\n");
//
//	printf("5(%d): ", freqOf5);
//	for (int i = 0; i < freqOf5; i++) {
//		printf("*");
//	}
//	printf("\n");
//
//	printf("6(%d): ", freqOf6);
//	for (int i = 0; i < freqOf6; i++) {
//		printf("*");
//	}
//	printf("\n");
//
//	printf("7(%d): ", freqOf7);
//	for (int i = 0; i < freqOf7; i++) {
//		printf("*");
//	}
//	printf("\n");
//
//	printf("8(%d): ", freqOf8);
//	for (int i = 0; i < freqOf8; i++) {
//		printf("*");
//	}
//	printf("\n");
//
//	printf("9(%d): ", freqOf9);
//	for (int i = 0; i < freqOf9; i++) {
//		printf("*");
//	}
//	printf("\n");
//}
//
//int count(int i) {
//	int retval = 1;
//	while (i /= 10)
//		retval++;
//	return retval;
//}
