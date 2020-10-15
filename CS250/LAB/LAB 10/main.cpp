#include <iostream>
#include <stdlib.h>
#include <ctime>

using namespace std;


int main()
{
    int randNum[20];
    char randChar[20];

    srand(time(0));

    for (int i =0; i < 20; i++)
    {
        int randNumber = rand() % 26 + 97;
        randNum[i] = randNumber;
        randChar[i] = (char)randNumber;
    }

    for (int i =0; i < 20; i++)
    {
        cout << randNum[i] << "\t" << randChar[i]<< endl;
    }


    return 0;
}
