
#include "stdafx.h"
#include "Farmer.h"
#include <stdlib.h> 
#include "time.h"

/**************************************************/
Farmer::Farmer() {
	srand(time(NULL));
	state = 0;
	currentState = 0;
	for (int x = 0; x< MAX_STATES; x++) {
		states[x] = -1;
		validStates[x] = -1;
	}
	states[0] = 0;
	totalValidStates = 0;
}
/**************************************************/
void Farmer::Go() {
	GenerateValidStates();
	state = 0;

	for (int stateLength = 3; stateLength < MAX_STATES; stateLength++) {
		printf("Searching Length %d\n", stateLength);
		for (int x = 0; x< 1000000; x++) {
			states[0] = 0;
			states[stateLength - 1] = 15;
			for (int y = 1; y< stateLength - 1; y++) {
				bool dup = true;
				while (dup) {
					dup = false;
					int nextState = rand() % totalValidStates;
					for (int z = 0; z< y; z++) {
						if (states[z] == nextState) {
							dup = true;
						}
					}
					if (!dup) {
						states[y] = nextState;
					}
				}
			}
			ValidatePath(stateLength, states);
		}
	}
}
/**************************************************/
void Farmer::ValidatePath(int _length, unsigned int *_states) {
	for (int x = 0; x< _length - 1; x++) {
		if (!IsValidMove(_states[x], _states[x + 1])) {
			return;
		}
	}
	PrintStates(_length, _states);
	exit(0);
}
/**************************************************/
void Farmer::PrintStates(int _length, unsigned int * _states) {
	for (int x = 0; x< _length; x++) {
		printf("%d ", _states[x]);
	}
	printf("\n");
}

/**************************************************/
bool Farmer::IsValidPath() {
	return true;
}
/**************************************************/
bool Farmer::IsValidMove(unsigned int _start, unsigned int _finish) {
	//Make sure that only one item has switched sides

	int totalSwitched = 0;

	//Make sure the farmer has switched sides
	if (FarmerIsRight(_start) && FarmerIsRight(_finish)) {
		return false;
	}
	if (!FarmerIsRight(_start) && !FarmerIsRight(_finish)) {
		return false;
	}

	if ((FoxIsRight(_start) && !FoxIsRight(_finish)) ||
		(!FoxIsRight(_start) && FoxIsRight(_finish)))
	{
		totalSwitched++;

	}

	if ((GrainIsRight(_start) && !GrainIsRight(_finish)) ||
		(!GrainIsRight(_start) && GrainIsRight(_finish)))
	{
		totalSwitched++;
	}

	if ((ChickenIsRight(_start) && !ChickenIsRight(_finish)) ||
		(!ChickenIsRight(_start) && ChickenIsRight(_finish)))
	{
		totalSwitched++;
	}
	if (totalSwitched <= 1) {
		return true;
	}

	return false;
}
/**************************************************/
void Farmer::GenerateValidStates() {
	totalValidStates = 0;
	for (state = 0; state <= 15; state++) {
		if (IsValidState(state)) {
			validStates[totalValidStates] = state;
			totalValidStates++;
		}
	}
}
/**************************************************/
bool Farmer::IsFinalState(unsigned int _state) {
	if (ChickenIsRight(_state) && FoxIsRight(_state) && GrainIsRight(_state) && FarmerIsRight(_state)) {
		return true;
	}
	return false;
}
/**************************************************/
bool Farmer::ChickenIsRight(unsigned int _state) {
	if ((_state & (1 << CHICKEN))>0) {
		return true;
	}
	return false;
}
/**************************************************/
bool Farmer::FoxIsRight(unsigned int _state) {
	if ((_state & (1 << FOX))>0) {
		return true;
	}
	return false;
}
/**************************************************/
bool Farmer::GrainIsRight(unsigned int _state) {
	if ((_state & (1 << FOX))>0) {
		return true;
	}
	return false;
}
/**************************************************/
bool Farmer::FarmerIsRight(unsigned int _state) {
	if ((_state & (1 << FARMER))>0) {
		return true;
	}
	return false;
}
/**************************************************/
bool Farmer::IsValidState(unsigned int _state) {
	if (FoxIsRight(_state) && ChickenIsRight(_state) && !FarmerIsRight(_state))
		return false;
	if (GrainIsRight(_state) && ChickenIsRight(_state) && !FarmerIsRight(_state))
		return false;

	if (!FoxIsRight(_state) && !ChickenIsRight(_state) && FarmerIsRight(_state))
		return false;
	if (!GrainIsRight(_state) && !ChickenIsRight(_state) && FarmerIsRight(_state))
		return false;
	return true;
}
/**************************************************/