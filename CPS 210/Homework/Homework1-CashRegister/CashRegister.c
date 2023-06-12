/*
 * CashRegister.c
 * CPS 210
 * Chris Phillips
 * 1/20/17
 * Homework 1: Cash Register
 *
 * Description:
 * This program provides the user with an interactive experience with a grocery store
 * that allows the user to buy items within that store. The program does the
 * calculations necessary for the transaction to take place. Finally the program
 * prints the receipt as it's output.
 */

#include <stdio.h>

double calculateTax(double numShirts);
double calculateShirtDiscount(int numShirts);
double calculateDiscount(int numShirts, int numChips);
double calculateTotal(int numShirts, int numChips, int numCoke);

//int main(void) {
//
//	char name[] = "";
//	double preTaxDiscountCost = 0;
//	double totalDiscount = 0;
//	double totalTax = 0;
//	double totalCost = 0;
//	double userPayment = 0;
//	const double TSHIRT_PRICE = 18.95;
//	const double CHIPS_PRICE = 1.79;
//	const double COKE_PRICE = 2.99;
//	int tShirts = 0;
//	int chips = 0;
//	int coke = 0;
//
//
//	//Introduction of the market to user
//	printf("What's your name? ");
//	scanf("%12s", name);
//	printf("Welcome to Denny's Market, %s! We have the following items for sale:\n\n", name);
//	printf("%-s%4s%.2lf%9s\n", "T-shirt", "$", TSHIRT_PRICE, "15% off");
//	printf("%-s%7s%.2lf%9s\n", "Chips", "$", CHIPS_PRICE, "15% off");
//	printf("%-s%8s%.2lf\n", "Coke", "$", COKE_PRICE);
//
//
//	//Ask user how many they want of each product
//	printf("\n%s", "How many T-shirts do you want? ");
//	scanf("%d",&tShirts);
//	printf("%s", "How many bags of potato chips? ");
//	scanf("%d", &chips);
//	printf("%s", "What about 12-pack Coke? ");
//	scanf("%d", &coke);
//
//
//	//All calculations
//	preTaxDiscountCost = calculateTotal(tShirts, chips, coke); //Subtotal calculation
//	totalDiscount = calculateDiscount(tShirts, chips); //Discount calculation
//	totalTax = calculateTax((tShirts * TSHIRT_PRICE) - calculateShirtDiscount(tShirts)); //Tax calculation
//	totalCost = preTaxDiscountCost - totalDiscount + totalTax; //Final cost calculation
//
//
//	//Request payment from user
//	printf("%s%.2lf%s", "Your total is $", totalCost, ".");
//	printf("\n%s", "Please enter your payment: ");
//	scanf("%lf", &userPayment);
//	printf("\n\n%s, here is your receipt: \n\n", name);
//
//
//	//Print receipt for user
//	//Lines 72-82 display items purchased, their price, how many of them, and total cost per item
//	printf("\t%4s\t%13s\t%10s\t%6s","item", "unit price", "how many", "cost");
//	printf("\n-----------------------------------------------------------\n");
//	if(tShirts > 0)
//		printf("%14s\t%10.2f\t%7d%16.2f\n", "T-shirt", TSHIRT_PRICE, tShirts, (tShirts * TSHIRT_PRICE));
//	if(chips > 0)
//		printf("%12s\t%10.2f\t%7d%16.2f\n", "Chips", CHIPS_PRICE, chips, (chips * CHIPS_PRICE));
//	if(coke > 0)
//	{
//		printf("%11s\t%10.2f\t%7d%16.2f\n", "Coke", COKE_PRICE, coke, (coke * COKE_PRICE));
//		printf("%14s%41.2f\n", "Deposit", (1.20 * coke));
//	}
//
//
//	//Lines 86-90 display all the calculations made earlier including subtotal, discount, tax, and total
//	printf("\n%15s%40.2f\n", "Subtotal", preTaxDiscountCost);
//	printf("%15s%40.2f\n", "Discount", -totalDiscount);
//	printf("%10s%45.2f\n", "Tax", totalTax);
//	printf("%58s\n", "------------");
//	printf("%12s%43.2f\n\n", "Total", totalCost);
//
//	printf("%14s%41.2f\n", "Payment", userPayment);
//	printf("%18s%37.2f\n", "Your change", userPayment - totalCost);
//
//	printf("\nThank you. Come Again!\n");
//
//}

double calculateTax(double cost)
{
	double tax;
	tax = (cost * .06);
	return tax;
}

double calculateShirtDiscount(int numShirts)
{
	double discount;
	discount = ((numShirts * 18.95) * .15);
	return discount;
}

double calculateDiscount(int numShirts, int numChips)
{
	double discount;
	discount = ((numShirts * 18.95) * .15) + ((numChips * 1.79) * .15);
	return discount;
}

double calculateTotal(int numShirts, int numChips, int numCoke)
{
	double totalCost;
	totalCost = (numShirts * 18.95) + (numChips * 1.79) + (numCoke * 2.99) + (numCoke * 1.20);
	return totalCost;
}


