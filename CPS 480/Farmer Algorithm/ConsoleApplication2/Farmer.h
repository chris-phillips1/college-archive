#pragma once
#ifndef FARMER__H__
#define FARMER__H__

#include <stdio.h>


//solution 0 9 1 13 4 14 6 15

#define CHICKEN 0   //1
#define FOX 1       //2
#define GRAIN 2     //4
#define FARMER 3    //8

#define MAX_STATES 16

#define NUMREELS 5
#define MAXSTOPS 22

class Farmer {
public:
	Farmer();
	bool IsFinalState(unsigned int _state);
	bool ChickenIsRight(unsigned int _state);
	bool FoxIsRight(unsigned int _state);
	bool GrainIsRight(unsigned int _state);
	bool FarmerIsRight(unsigned int _state);
	bool IsValidState(unsigned int _state);
	void Go();
	void GenerateValidStates();
	void FindRandomSolution();
	bool IsValidPath();
	bool IsValidMove(unsigned int _start, unsigned int _finish);
	void PrintStates(int _length, unsigned int * _states);
	void ValidatePath(int _length, unsigned int * _states);

	unsigned int state;

	unsigned int states[MAX_STATES];
	unsigned int validStates[MAX_STATES];
	int currentState;
	int totalValidStates;
	int ReelStrips[NUMREELS][MAXSTOPS];

};

#endif