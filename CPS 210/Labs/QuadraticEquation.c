/*
 * QuadraticEquation.c
 * CPS 210
 * Chris Phillips
 * 3/24/17
 * Homework 6: Quadratic Equation
 *
 * Description:
 * This program will solve the quadratic
 * equation for any three numbers that are
 * entered by the user and will display it
 * correctly.
 */

#include <stdio.h>
#include "mymath.h"

//int main(void) {
//
//	double a, b, c;
//
//	printf("%s\n%s", "To solve quadratic equation aX^2 + bX + c = 0", "Please enter the three coefficients a, b, and c: ");
//	scanf("%lf%lf%lf", &a, &b, &c);
//
//	printf("\n");
//	displayEquation(a, b, c);
//	printf("\n");
//
//	int numSolutions = hasRealSolutions(a, b, c);
//
//	//If there are no real solutions send the designated number -999.99 to displayResults method
//	if(numSolutions == 0) {
//		displayResults(-999.99, -999.99);
//	}
//	//If there is only one solution, solve the first equation and send it + the designated number to the displayResults method
//	else if(numSolutions == 1) {
//		double solution = findSolution(a, b, c, 1);
//		displayResults(solution, -999.99);
//	}
//	//If there are two solutions, find them then send thme to the displayResults method
//	else if(numSolutions == 2) {
//		double solution1 = findSolution(a, b, c, 1);
//		double solution2 = findSolution(a, b, c, 2);
//		displayResults(solution1, solution2);
//	}
//
//}
