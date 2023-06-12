/*
 * SelectionSort.c
 * CPS 210
 * Chris Phillips
 * 4/14/17
 * Homework 8: Pointers
 *
 * Description:
 * The SelectionSort Program will prompt
 * the user for the number of numbers they
 * would like to be sorted. When the user has
 * entered those numbers, they will be sorted
 * using a selection sort algorithm, in either
 * ascending or descending order.
 */

#include <stdio.h>

int compare(const int num1, const int num2, const int order);
void swap(int *const num1, int *const num2);
void selectionSort(int nums[], const int size, const int order, int (*functionPtr)(int, int, int));
void print(int nums[], const int size);

static int order;

//int main(void) {
//
//	int numElements;
//
//	printf("How many numbers? \n");
//	scanf("%d", &numElements);
//	printf("Enter %d integers (separated by space): ", numElements);
//
//	int arr[numElements];
//
//	for(int i = 0; i < numElements; i++)
//		scanf("%d", &arr[i]);
//
//	printf("%s\n", "Sort Choice: ");
//	printf("0 --> ascending\n");
//	printf("1 --> descending\n");
//	scanf("%d", &order);
//
//	selectionSort(arr, numElements, order, &compare);
//	print(arr, numElements);
//}

/* The compare function takes in 3 numbers,
 * it compares the first two based on what
 * order the user wants them sorted (the
 * third number).
 *
 */
int compare(const int num1, const int num2, const int order) {

	//if the user chooses descending
	if(order) {
		if(num2 > num1)
			return 1;
		else
			return -1;
	}
	//if the user chooses ascending
	else {

		if(num2 < num1)
			return 1;
		else
			return -1;

	}

}

/* The swap method will take 2 pointers that
 * point to the memory address of the numbers
 * that will be swapped. The program then swaps
 * the content at those memory addresses.
 */
void swap(int *const num1, int *const num2) {

	int temp = *num1;
	*num1 = *num2;
	*num2 = temp;

}

/* The selectionSort function takes in an int
 * array, its size, the order, and a function
 * pointer. The method then sorts the elements
 * in the provided array using the order provided.
 */
void selectionSort(int nums[], const int size, const int order, int (*comparePtr)(int, int, int)) {

	//Sets the pointer to a specific function
	comparePtr = &compare;

	for(int i = 0; i < size - 1; i++)
	{
		int minIndex = i;
		for(int j = i + 1; j < size; j++)
		{
			// If the number at the j index is "less" (or greater, it's based on the order)
			// than the number at the minIndex, set the minIndex to j
			if((*comparePtr)(nums[j], nums[minIndex], order) < 0)
				minIndex = j;
		}
		//If the minimum number isn't the current number, swap them.
		if(minIndex != i)
			swap(&nums[i], &nums[minIndex]);
	}

}
/* The print method, given an array and it's
 * size, will iterate through the array and
 * prints out its contents, using pointer
 * notation.
 */
void print(int nums[], const int size) {

	if(order)
	{
		printf("Numbers in descending order: \n");
		for(int i = 0; i < size; i++)
			printf("%d ", *(nums + i));
	}
	else
	{
		printf("Numbers in ascending order: \n");
		for(int i = 0; i < size; i++)
			printf("%d ", *(nums + i));
	}

}
