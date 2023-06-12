//Starting at address arr, for n units, set their value to 10.
//Usage: put arr in R0, n in R1

//initialize arr (get it from R0)
@R0
D=M
@arr
M=D

//initialize n (get it from R1)
@R1
D=M
@n
M=D

//initialize iterator i
@i
M = 0

//Loop: set value one by one
(LOOP)
	//Compute i - n and store the result in D
	@i
	D=M
	@n
	D=D-M		
	
	//Jump to the end if i>=n (D;JGE)
	@END
	D;JGE

	//else
	//find the address of current RAM: arr + i
	@arr
	D=M
	@i
	D=D+M
	@current
	M=D
	
	//Set value to 10
	@10
	D=A
	@current
	A=M
	M=D
		
	//increment i and continue the loop
	@i
	M=M+1
	@LOOP
	0;JMP
//Terminate the program
(END)
@END
0;JMP
