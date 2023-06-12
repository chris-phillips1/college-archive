// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program paints the area
// within (160, 160) to (239,399)
// i.e. writes "black" for every pixel in that area;
// You can make your own magic pattern to paint, but
// the painted area should remain fully black as long as the key is pressed.
// When no key is pressed, the program clears the screen,
// i.e. writes "white" ;
// the screen should remain fully clear as long as no key is pressed.


//Calculate the word address for the upperleft corner of the area
//(160,160): address = SCREEN + 160*32 + 160/16
@SCREEN //the pointer holding the based address of screen memory map
D=A
@5130
D=D+A		//SCREEN + 5130
@upperleft //the variable holding the upperleft word address
M=D


//Calculate the word address for the lowerleft corner
@SCREEN
D=A
@7658 //(239,160): 239*32 + 160/16
D=D+A
@lowerleft
M=D

//There are 14 words from column 160 to 399: (399-160)/16 = 14
@14
D=A
@colTotal 	//coTotal = 14
M=D

//The infinite loop
//Keep painting the area. For each word in screen memory map, use the current color which is determined by whether a key is pressed or not
(LOOP)
	//start from the upperleft corner
	@upperleft
	D=M
	@row
	M=D
	(ROW)	//paint each row
	//check if row > lowerleft
	@row
	D=M
	@lowerleft
	D=D-M		//row - lowerleft
	@LOOP
	D;JGT		//if painted all rows, start from upperleft

	//paint the current row, word by word (i.e., column by column)
	//initialize the col
	@row
	D=M
	@col
	M=D

	//initialize the iterator for each word (column)
	@i
	M=0

	(COL)
		//check if finished painting the whole row or not
		@i
		D=M
		@colTotal
		D=D-M		//i-colTotal
		//if finish the whole row, go to next row
		@COL_END
		D;JGT

		//Get current keyboard scan code
		//and paint the current word by the desired color
		//The address of that word is stored in variable col
		//Your job is narrowed down to: get scan code, set value of the RAM unit with address in variable col
		//Your code here


		(KBD_LOOP)

		@KBD
		D = M

		@col
		M = M + D

		@LOOP
		D; JGE


		@KBD_LOOP
		0; JMP


		(PAINTED)
		//increase col and i
		@col
		M=M+1
		@i
		M=M+1
		@COL
		0;JMP

	(COL_END)
	//increase row: +32
	@32
	D=A
	@row
	M = M+D
	@ROW
	0;JMP
