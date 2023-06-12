// Put the scan code of pressed key into RAM[0]
// Stop when the Esc key is pressed (scan code = 140)
//Initialize a variable to hold KBD
@kbd
M = 0
(LOOP)
	//read KBD
	@KBD
	D=M
	//store it in variable kbd
	@kbd 
	M = D

//check if KBD == 140
@140
D=D-A	//KBD – 140
// goto END if ==140
@END
D;JEQ 

	//else: RAM[0] = KBD
	@kbd
	D=M
	@R0
	M=D
	@LOOP
	0;JMP
(END)
	@END
	0;JMP
	
	
