/*
 * StringCopy.c
 * CPS 210
 * Chris Phillips
 * 4/14/17
 * Homework 8: Pointers
 *
 * Description:
 * The StringCopy Program has two functions
 * that copy the contents of the second string
 * to the first string. They work the same, but
 * use different notation (array vs. pointer).
 */

void strcpy1(char *str1[], char *const str2[]);
void strcpy2(char *str1[], char *const str2[]);

//int main(void) {
//
//	//Declare character array and a pointer to "hello"
//	char s1[8];
//	char *s2 = "hello";
//
//	//Copy "hello" to the character array
//	strcpy1(s1, s2);
//	printf("%s\n", s1);
//
//	//Change the pointer to point to "goodbye"
//	s2 = "goodbye";
//
//	//Copy "goodbye" to the character array
//	strcpy2(s1, s2);
//	printf("%s\n", s1);
//
//}

/* The strcpy1 method copies the content of the
 * second string to the first string, using array
 * notation.
 */
void strcpy1(char *str1[], char *const str2[]) {

	 int size = sizeof(str2) / sizeof(char);

	 for(int i = 0; i < size; i++)
		 str1[i] = str2[i];

}

/* The strcpy2 method copies the content of the
 * second string to the first string, using pointer
 * notation.
 */
void strcpy2(char *str1[], char *const str2[]) {

	*str1 = *str2;

}
