/*
 * Statistics.c
 * CPS 210
 * Chris Phillips
 * 4/2/17
 * Homework 7: Arrays
 *
 * Description:
 * The Statistics program gets input from the
 * user, stores it an array, then calculates
 * the minimum, maximum, mean, and standard
 * deviation of all the entered numbers.
 */

#include <stdio.h>
#include "myutil.h"

//int main(void) {
//
//	int numElements = 0;
//
//	//Ask for the number of elements in the array
//	printf("How many numbers? ");
//	scanf("%d", &numElements);
//
//	//initializes a double array called arr with the number of elements specified earlier
//	double arr[numElements];
//
//	//Ask the user for that amount of numbers
//	printf("Enter %d numbers (separated by space): ", numElements);
//
//	//Scan in all the elements and store them in the array
//	for(int i = 0; i < numElements; i++)
//		scanf("%lf", &arr[i]);
//
//	printf("\n");
//
//	//Print out the results of the function calls
//	printf("Min: %.2lf\n", min(arr, numElements));
//	printf("Max: %.2lf\n", max(arr, numElements));
//	printf("Mean: %.2lf\n", mean(arr, numElements));
//	printf("Standard Deviation: %.2lf\n", stddev(arr, numElements));
//
//}
