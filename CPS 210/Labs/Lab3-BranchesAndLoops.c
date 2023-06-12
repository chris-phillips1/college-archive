/*
 * Lab3-BranchesAndLoops.c
 *
 *  Created on: Mar 2, 2017
 *      Author: Chris
 */

#include <stdio.h>

//int main(void) {

//	char input;
//	printf("Enter your letter grade: ");
//	scanf("%c", &input);

	//Example 1 - If Statements
//	if(input == 65 || input == 97)
//		printf("Excellent\n");
//	else if(input == 66 || input == 98)
//		printf("Good");
//	else if(input == 67 || input == 99)
//		printf("Average");
//	else if(input == 69 || input == 101)
//		printf("Poor");
//	else
//		printf("Error");

	//Example 2 - Switch Statements
//	switch(input) {
//	case 'a':
//	case 'A':
//		printf("Excellent\n");
//		break;
//	case 'b':
//	case 'B':
//		printf("Good\n");
//		break;
//	case 'c':
//	case 'C':
//		printf("Average\n");
//		break;
//	case 'e':
//	case 'E':
//		printf("Poor\n");
//		break;
//	default:
//		printf("Error\n");
//	}

	//Example 3 - While Loops
//	int input;
//
//	printf("Enter an integer (must be between 0 and 99): ");
//	scanf("%d", &input);
//
//	while(input < 0 || input > 99)
//	{
//		printf("Enter an integer (must be between 0 and 99): ");
//		scanf("%d", &input);
//	}
//
//	printf("Great! You entered a valid number: %d\n", input);


	//Example 4 - Do While Loops
//	int input;
//
//	do {
//		printf("Enter and integer (must be between 0 and 99): ");
//		scanf("%d",&input);
//	} while(input < 0 || input > 99);
//
//	printf("Great! You entered a valid number: %d\n", input);

	//Example 5 - For loops
//	int numRows;
//
//	printf("Enter a number of rows: ");
//	scanf("%d", &numRows);
//
//	printf("\nPATTERN A");
//	for(int i = 0; i <= numRows; i++)
//	{
//		for(int j = 0; j < i; j++)
//		{
//			printf("%d", j + 1);
//		}
//		printf("\n");
//	}
//
//	printf("\nPATTERN B\n");
//	for(int i = numRows; i >= 0; i--)
//	{
//		for(int j = 0; j < i; j++)
//		{
//			printf("%d", j + 1);
//		}
//		printf("\n");
//	}
//
//	printf("PATTERN C\n");
//	for(int i = numRows; i >= 0; i--)
//	{
//		for(int j = i; j >= 1; j--)
//		{
//			printf("%d", j);
//		}
//		printf("\n");
//	}
//
//}
