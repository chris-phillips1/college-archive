//Starting at address arr, for n units, set their value to 10.
//Usage: put arr in R0, n in R1

//initialize arr (get it from R0)


//initialize n (get it from R1)


//initialize iterator i


//Loop: set value one by one


	//Compute i - n and store the result in D
	
		
	
	//Jump to the end if i>=n (D;JGE)
	

	//else
	//find the address of current RAM: arr + i
	
	
	//Set value to 10
	
		
	//increment i and continue the loop
	

//Terminate the program




//After you sucessfully run the program, think about this question:
//Can we set arr to any value?
//If arr is an array, can we store the array any where?


