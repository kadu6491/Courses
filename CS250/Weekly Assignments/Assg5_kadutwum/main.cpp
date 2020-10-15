#include <iostream>
#include <random>
#include <algorithm>
#include <cstdlib>
#include <vector>
#include <string>
#include<conio.h>

using namespace std;

void playGame();

int main()
{
    playGame();
    return 0;
}

///Implement this function
void playGame()
{
    ///* Get the level of difficulty of the game
        ///** Define the variables
        int dif = 0;
        int solution[dif];
        int difNum = 1;
        int input[dif];

        ///** Ask the user for the difficulties
        cout << "Welcome To Modified Mastermind!" << endl << endl;

        cout << "Enter a difficulty (1-10)" << endl;
        cin >> dif;
        cin.ignore();

        ///** Tell the user what the difficulties will be
        cout << "Difficulty = " << dif << endl << endl;

    ///* User will guess from 1 to the number of difficulties
    cout << "You will be trying to guess " << dif
         << " numbers from 1-" << dif << endl << endl;

        ///** User a for loop to create a random numbers
        for(int i = 0; i < dif; i++)
        {
            solution[i] = (rand() % dif) + 1;
            ///cout << solution[i] << " ";
        }

        //cout << endl;
        ///** Use a while loop for the 10 guess, if user guess more than 10, game over
        while (difNum <= 10)
        {
                cout << endl;
                cout << "Turn " << difNum << " of 10 " << endl;
                cout << "Please enter " << dif << " number from 1-"
                     << dif << " separated by spaces" << endl;
                cin >> input[dif];



            //cout << input << endl;
            ///*** Use a loop to determine how many are in correct places and also in the wrong place
                ///**** Declare the variables
                bool matchedInput[dif] = {false};
                bool matchSolution[dif] = {false};

                int correctPlacement = 0;
                int incorrectPlacement = 0;

                ///**** Use a for loop for the corrects places

                for(int i = 0; i < dif; i++)
                {
                    for(int j = 0; j < dif; j++)
                    {
                        if (solution[i] == input[j])
                        {
                            if (i == j)
                            {
                                if(matchedInput[j] != true && matchSolution[i] != true)
                                {
                                    correctPlacement++;
                                    incorrectPlacement++;
                                    matchedInput[j] = true;
                                    matchSolution[i] = true;

                                    cout << endl;
                                    cout << correctPlacement
                                         << " of your guesses are correct and in the correct positon" << endl;
                                    cout << incorrectPlacement
                                         << " of your guesses are correct but in the wrong position" << endl;

                                }
                            }
                        }
                    }
                }

                 ///**** Use a for loop for the wrong places

                for(int i = 0; i < dif; i++)
                {
                    for(int j = 0; j < dif; j++)
                    {
                        if (solution[i] == input[j])
                        {
                            if (i == j)
                            {
                                if(matchedInput[j] != true && matchSolution[i] != true)
                                {
                                    incorrectPlacement++;
                                    correctPlacement++;
                                    matchedInput[j] = true;
                                    matchSolution[i] = true;

                                    cout << endl;
                                    cout << correctPlacement
                                         << " of your guesses are correct and in the correct positon" << endl;
                                    cout << incorrectPlacement
                                         << " of your guesses are correct but in the wrong position" << endl;
                                }

                            }
                        }
                    }
                }


            difNum++;

            //cout << correctPlacement
                 //<< " of your guesses are correct and in the correct positon" << endl;
            //cout << incorrectPlacement
                 //<< " of your guesses are correct but in the wrong position" << endl;
        }

        /// *** If user can't guess on 10 tries, game over
        cout << endl;
        cout << "Sorry you run out of guesses" << endl << endl;
        cout << "GAME OVER!!!!!!!!!!!!!" << endl;


}
