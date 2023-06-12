/*
 * LabArrayPtr.c
 *
 *  Created on: Apr 11, 2017
 *      Author: Chris
 */

#include <stdio.h>

//void print(int num[], int size);
//void fun1(int num[3], int size);
//void fun2(int *num, int size);

//int main(void) {
//	int num[] = { 1, 2, 3 };
//	fun1(num, 3);
//	print(num, 3);
//	fun2(num, 3);
//	print(num, 3);
//	return 0;
//}
//
//// Parameter: unsized array.  Access: array notation
//void print(int num[], int size) {
//	for (int i = 0; i < size; i++)
//		printf("%d, ", *(num + i));
//	printf("\n");
//}
//
//// Parameter: fixed-size array.  Access: array notation
//void fun1(int num[3], int size) {
//	for (int i = 0; i < size; i++)
//		*(num + i) *= 2;
//}
//
//// Parameter: pointer.  Access: array notation
//void fun2(int *num, int size) {
//	for (int i = 0; i < size; i++)
//		*(num + i) *= 2;
//}
