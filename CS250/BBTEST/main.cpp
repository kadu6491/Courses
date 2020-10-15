#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <cstdlib>

using namespace std;


void illegalInput(){
	cout << "\nILLEGAL INPUT" << endl;
  	exit(1);
}

void computerMove (int piles[], const int nPiles){
	int pilesXOR = 0;
	int numLargePiles = 0;
	int numSingletonPiles = 0;

	for (int i = 0; i < nPiles; i++){
	  	if (piles[i] > 1){
	  		++numLargePiles;
	  	}
	  	if (piles[i] == 1){
			++numSingletonPiles;
	  	}

	  	pilesXOR ^= piles[i];
	}

	int movePile;
	int moveAmount = 0;

	if (pilesXOR == 0){
	 	// Human can force a win with perfect play. Stall for time
	  	// by removing one stone from the largest pile.
	  	int max = 0;
	 	for (int i = 0; i < nPiles; ++i){
			if (piles[i] > max){
	    		max = piles[i];
	    		movePile = i;
	  		}
	  	}
	  	moveAmount = 1;
	}
	else{
	  	// Computer can force a win.
	  	moveAmount = 0;

	  	for (int i = 0; i < nPiles && moveAmount == 0; ++i){
	  		int nToLeave = pilesXOR ^ piles[i];

	  		if (nToLeave < piles[i]){
	      		movePile = i;
	      		moveAmount = piles[i] - nToLeave;
	    	}
		}
	}

	if (piles[movePile] > 1 && piles[movePile] - moveAmount < 2 && numLargePiles == 1){
	  	// Selected pile is large, but move would leave no large piles
	  	// Leave an odd number of singleton piles
	  	if (numSingletonPiles % 2 == 1){
	    	moveAmount = piles[movePile];
	  	}
	  	else {
	    	moveAmount = piles[movePile] -1;
	  	}
	}

	cout << "Computer takes " << moveAmount
   		 << " stones from pile " << movePile+1 << endl;

	piles[movePile] -= moveAmount;
}

int readInteger (istream& in){

  	string word;
  	in >> word;

  	if (!in){
  		illegalInput();
  	}

  	istringstream win (word);

  	int k = 0;

  	win >> k;
  	if (!win){
    	illegalInput();
  	}

  	return k;
}

void humanMove(istream& in, int piles[], int nPiles){

  	string line;
  	int pileNum = -1;

  	while (pileNum < 1 || pileNum > nPiles || piles[pileNum-1] <= 0){
      	cout << "\nWhich pile would you like to pick from? " << flush;
      	pileNum = readInteger(in);
    }

  	int nToRemove = -1;
  	while (nToRemove < 1 || nToRemove > piles[pileNum-1]) {
      cout << "How many stones would you like to remove? " << flush;
      nToRemove = readInteger(in);
    }

  	piles[pileNum-1] -= nToRemove;
}


void printGameState(int piles[], int nPiles) {

  	cout << "\nPile #  ";
  	for (int i = 0; i < nPiles; ++i){
    	cout << "\t" << i+1;
    }

  	cout << "\n# Stones";

  	for (int i = 0; i < nPiles; ++i){
    	cout << "\t" << piles[i];
  	}
  	cout << endl;
}

// Play a single game
void playGame (istream& in){

  	string line;
  	int nPiles = 8;

  	while (nPiles < 1 || nPiles > 7){
      	cout << "How many piles? (1-7) " << flush;
      	nPiles = readInteger(in);
    }

  	cout << "Enter " << nPiles
    	 << " integers (0..100) indicating the starting number of stones in each pile: "
       	 << flush;

  	const int nPilesC = nPiles;

    int pile[nPilesC];
    int sum = 0;

    for (int i = 0; i < nPiles; ++i){
		pile[i] = readInteger(in);

		if (pile[i] < 0){
	  		pile[i] = 0;
		}
		if (pile[i] > 100){
	  		pile[i] = 100;
		}

		sum += pile[i];
    }

    int player = 0;

    while (sum > 0){
		printGameState(pile, nPiles);

		if (player == 0){
	  		humanMove (in, pile, nPiles);
		}
		else{
	  		computerMove (pile, nPiles);
		}

		player = 1 - player;
		sum = 0;

		for (int i = 0; i < nPiles; ++i){
	    	sum += pile[i];
	  	}
    }

    if (player != 0){
      cout << "You took the last stone. I win!" << endl;
    }
    else{
      cout << "I took the last stone. Congratulations, you win!" << endl;
    }
}

void playGames (istream& in){

  	bool keepPlaying = true;
  	while (keepPlaying){

  	    playGame (in);

    	cout << "Play again? (y/n) " << flush;

      	char c;
      	string line;
      	in >> c;
      	if (!in){
			illegalInput();
      	}

      	getline (in, line);

      	if (!in){
			illegalInput();
      	}

      	keepPlaying = (c == 'y') || (c == 'Y');
    }
}

int main(int argc, char** argv){
  	if (argc > 1){
      	// For ease of testing, take input from a file if named on
      	// the command line.

      	ifstream in (argv[1]);
      	playGames (in);
    }
  	else{
      	// Otherwise take input from the standard input
      	playGames (cin);
    }

  	return 0;
}
