 ; add.asm
 ; CPS 210
 ; Chris Phillips
 ; 4/27/17
 ; Homework 9: Assembly
 ;
 ; Description:
 ; The add program will prompt the user for
 ; a number, add 1 to that number, then return
 ; that number to the user.

SECTION .data
    msg1    db  'Enter a number:', 0xa
    LEN1 equ $- msg1

    msg2    db  'Your number + 1:', 0xa
    LEN2 equ $- msg2

    ;Defining constants
    SYS_WRITE equ 4
    SYS_READ equ 3
    STDOUT equ 1
    STDIN equ 0
    SYS_EXIT equ 1

SECTION .bss
    input resb 4

SECTION .text
    global _start

_start:

    ;Prompts the user to enter a number
    mov eax, SYS_WRITE
    mov ebx, STDOUT
    mov ecx, msg1
    mov edx, LEN1
    int 0x80

    ;Reads the user's number
    mov eax, SYS_READ
    mov ebx, STDIN
    mov ecx, input
    mov edx, 4
    int 0x80

    ;Increments the number
    mov eax, [input]
    inc eax
    mov [input], eax

    ;Prints "Your number + 1: "
    mov eax, SYS_WRITE
    mov ebx, STDOUT
    mov ecx, msg2
    mov edx, LEN2
    int 0x80

    ;Prints out the new number
    mov eax, SYS_WRITE
    mov ebx, STDOUT
    mov ecx, input
    mov edx, 4
    int 0x80

    ;Exits the program
    mov eax, SYS_EXIT
    mov ebx, 0
    int 0x80
