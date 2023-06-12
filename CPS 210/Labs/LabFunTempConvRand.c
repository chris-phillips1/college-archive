/*
 * LabFunTempConvRand.c
 *
 *  Created on: Mar 16, 2017
 *      Author: Chris
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

double conversion(double C);
void displayResult(double C, double F);

//int main(void) {
//
//	double randomC, calculatedF;
//
//	srand((unsigned) time(NULL) * getpid());
//
//	randomC = rand() / (double) RAND_MAX * 200 - 100;
//
//	calculatedF = conversion(randomC);
//
//	displayResult(randomC, calculatedF);
//
//}

double conversion(double C) {

	double f = (9/5.0) *C + 32;
	return f;

}

void displayResult(double C, double F) {

	printf("%.2lf C = %.2lf F\n", C, F);

}

