/*
 * mymath.c
 *
 *  Created on: Mar 22, 2017
 *      Author: Chris
 */

#include <math.h>
#include "mymath.h"

/* The displayEquation Method will determine the signs
 * to be used when displaying the equation and if the
 * full equation should be displayed or just bx + c = 0.
 */
//void displayEquation(double a, double b, double c) {
//
//	//Check to see if it's necessary to print out the first part of the equation
//	if(a == 0)
//	{
//		if(b < 0)
//			{
//				b -= 2*b;
//				if(c < 0)
//				{
//					c -= 2*c;
//					printf("-%.1lfX - %.1lf = 0\n", b, c);
//				}
//				else
//					printf("-%.1lfX + %.1lf = 0\n", b, c);
//			}
//			else
//				if(c < 0)
//				{
//					c -= 2*c;
//					printf("%.1lfX - %.1lf = 0\n", b, c);
//				}
//				else
//					printf("%.1lfX + %.1lf = 0\n", b, c);
//	}
//	//If the first part does need to be printed, do this section
//	else
//	{
//		//Several Tests to see how the signs should be displayed
//		if(b < 0)
//		{
//			b -= 2*b;
//			if(c < 0)
//			{
//				c -= 2*c;
//				printf("%.1lfX^2 - %.1lfX - %.1lf = 0\n", a, b, c);
//			}
//			else
//				printf("%.1lfX^2 - %.1lfX + %.1lf = 0\n", a, b, c);
//		}
//		else
//			if(c < 0)
//			{
//				c -= 2*c;
//				printf("%.1lfX^2 + %.1lfX - %.1lf = 0\n", a, b, c);
//			}
//			else
//				printf("%.1lfX^2 + %.1lfX + %.1lf = 0\n", a, b, c);
//	}
//}
//
///*
// * The hasRealSolutions method will take in all the
// * coefficients and test to see how many solutions
// * there are.
// */
//int hasRealSolutions(double a, double b, double c) {
//
//	double formula = pow(b, 2) - (4 * a * c);
//
//	if(a == 0 && b == 0) //No solutions because if a and b are 0, the equation is just c = 0.
//		return 0;
//	else if(a != 0 && formula < 0) //No solutions if a doesn't equal 0 and the part under the sqrt is less than 0 (sqrt of negative is imaginary)
//		return 0;
//	else if(a == 0 && b != 0) //If a is zero but b isn't, the equation would be linear bx + c = 0
//		return 1;
//	else if(formula == 0)
//		return 1;
//	else
//		return 2;
//
//}
//
///* The findSolution Method will determine how many solutions
// * there are to the equation and return that number to
// * where the function is called.
// */
//double findSolution(double a, double b, double c, int n) {
//
//	double solution = 0.0;
//
//	//If the user wants to compute the first equation
//	if(n == 1) {
//		solution = (sqrt((b * b) - (4 * a * c)) - b) / (2 * a);
//	}
//	//If the user wants to compute the second equation
//	else if(n == 2) {
//		solution = ( (b + (-2 * b)) -sqrt((b * b) - (4 * a * c)) ) / (2 * a);
//	}
//	//If a equals zero and b doesn't then its a simple linear equation, bx + c = 0, or x = -c / b
//	if(a == 0 && b != 0)
//		solution = ((c + (-2 * c)) / b);
//
//	return solution;
//
//}
//
///* The displayResults Method will display the results
// * based on the numbers of solutions, designated by
// * the number -999.99.
// */
//void displayResults(double s1, double s2) {
//
//	if(s2 == -999.99) {
//		if(s1 == -999.99) {
//			//If both are designating numbers, print that there are no real solutions
//			printf("There are no real solutions.");
//		}
//		else {
//			//If one of the solutions is designating number then print the one solution
//			printf("The one solution of the equation is: %.4lf", s1);
//		}
//	}
//	else {
//		//If they are both solutions, display them
//		printf("The two solutions of the equation are: %.4lf, %.4lf", s1, s2);
//	}
//
//}
//
//
