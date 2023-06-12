/*
 * myutil.c
 *
 *  Created on: Apr 1, 2017
 *      Author: Chris
 */

#include "myutil.h"
#include <math.h>

/* The min Method will determine the
 * minimum of all the elements of the array
 */
double min(double arr[], int size) {

	double theMin = arr[0]; //Sets the minimum to the first element of the array

	for(int i = 0; i < size; i++)
	{
		//For all the elements in the array, check the current element to see if its less than the current minimum
		if(arr[i] < theMin)
			theMin = arr[i];
	}

	return theMin;
}

/* The max Method will determine the
 * maximum of all the elements of the array
 */
double max(double arr[], int size) {

	double theMax = arr[0]; //Sets the maximum to the first element of the array

	for(int i = 0; i < size; i++)
	{
		//For all the elements in the array, check the current element to see if its more than the current maximum
		if(arr[i] > theMax)
			theMax = arr[i];
	}

	return theMax;
}

/* The sum Method will determine the
 * sum of all the elements of the array
 */
double sum(double arr[], int size) {

	double theSum = 0; //Start the sum at 0

	for(int i = 0; i < size; i++)
	{
		//For every element in the array, add it to the sum
		theSum += arr[i];
	}

	return theSum;
}

/* The mean Method will determine the
 * mean of all the elements of the array
 */
double mean(double arr[], int size) {

	double theSum = sum(arr, size); //Use the sum method to determine the sum of the elements in the array
	double theMean;

	//Calculate the mean by by dividing the sum by the size
	theMean = theSum / size;

	return theMean;

}

/* The stddev Method will determine the standard
 * deviation of all the elements of the array
 */
double stddev(double arr[], int size) {

	double top, equation, final;
	double theMean = mean(arr, size); //Uses the mean method to determine the mean of the elements in the array

	for(int i = 0; i < size; i++)
	{
		//Calculate the top part of the standard deviation
		top += (pow(arr[i] - theMean, 2));
	}

	equation = (top / size); //Calculate the full equation, without the square root
	final = sqrt(equation); //Take the square root for the final equation

	return final;
}
