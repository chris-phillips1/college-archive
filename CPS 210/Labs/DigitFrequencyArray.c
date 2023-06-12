/*
 * DigitFrequencyArray.c
 * CPS 210
 * Chris Phillips
 * 4/2/17
 * Homework 7: Arrays
 *
 * Description:
 * This program reworks the DigitFrequency program
 * to be a lot shorter with the use of an array
 * instead of 10 individual variables. The program
 * creates 200 numbers and calculates the frequency
 * of each digit.
 */

#include <stdlib.h>
#include <time.h>

//int count(int i);
//
//int main(void) {
//
//	static int frequency[10]; //Variables to keep track of the frequency of each digit
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
//		frequency[digit3] = frequency[digit3] + 1;
//		frequency[digit2] = frequency[digit2] + 1;
//		frequency[digit1] = frequency[digit1] + 1;
//	}
//
//	//Print out the frequency of each digit, with appropriate amount of stars
//	for(int i = 0; i <= 9; i++)
//	{
//		printf("%d(%d): ", i, frequency[i]);
//
//		for(int j = 0; j < frequency[i]; j++)
//			printf("*");
//
//		printf("\n");
//	}
//}
//
//int count(int i) {
//	int retval = 1;
//	while (i /= 10)
//		retval++;
//	return retval;
//}
