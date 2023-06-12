// Divide R0 by R1, stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//Usage: provide values for R0 and R1

//initialize variables
@count
M = 0
@R0
D = M
@temp
M = D
@R1
D = M

//if R1 is 0, end program (can't divide by 0)
@END
D; JEQ

//Subtracts from the numerator (R0) with a counter until R0 is less then 0, counter is the result of division
(SUBTRACT_LOOP)
@R1
D = M

@temp
M = M - D
D = M

@count
M = M + 1

@SUBTRACT_LOOP
D; JGT

//Gets the answer to division
@count
D = M

//Stores division in R2
@R2
M = D

(END)
@END
0; JMP
