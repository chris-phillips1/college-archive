//if R0 > 0 then R1 = 1 else R1 = 0
@R0
D=M

// if R0>0, jump to the positive
@POSITIVE
D;JGT

//else: set R1 = 0
@R1
M=0
//Finish of 1 branch: end the program
@END
0;JMP

// if R0>0: positive
(POSITIVE)
@R1
M=1
// Finish of 1 branch: end the program

// the termination
(END)
@END
0;JMP
