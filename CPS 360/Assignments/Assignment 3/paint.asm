// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program paints the area
// within (160, 160) to (239, 399)
// i.e. writes "black" for every pixel in that area;
// You can make your own magic pattern to paint, but
// the painted area should remain fully black as long as the key is pressed.
// When no key is pressed, the program clears the screen,
// i.e. writes "white" ;
// the screen should remain fully clear as long as no key is pressed.

@i 			// i: screen offset
M=0

(LOOP)
@KBD
D=M
@PAINT
D;JNE 		// if read something, jump to PAINT
@CLEAR
0;JMP 		// otherwise, jump to CLEAR
@LOOP
0;JMP 		// loops

(PAINT)
@i 			// if offset >= 8192, skip PAINT (whole screen is already painted)
D=M
@8192
D=D-A
@LOOP
D;JGE

@i 			// SCREEN[i] = -1
D=M
@SCREEN
A=A+D
M=-1
@i 			// i++
M=M+1

@LOOP 		// return
0;JMP

(CLEAR)
@SCREEN 	// SCREEN[0] = 0
M=0
@i 			// if offset == 0, skip clear (whole screen is cleared)
D=M
@LOOP
D;JEQ

@i 			// SCREEN[i] = 0
D=M
@SCREEN
A=A+D
M=0
@i 			// i--
M=M-1

@LOOP 		// return
0;JMP
