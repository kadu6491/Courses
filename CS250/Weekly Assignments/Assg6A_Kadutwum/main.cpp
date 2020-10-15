#include <iostream>
#include <stdlib.h>
#include <ctime>
#include <fstream>
#include <string>
#include <random>

using namespace std;

string randomword(int length);

int main()
{
    int num = 5000;
    int randNum[num];
    char randChar[num];
    int sortRand;
    double randSale[num];
    double Sales;
    int catNum[num];
    int cuddleScore[num];
    int youTubeFamous[num];

    ofstream outFile;

    outFile.open("kittycafes.txt");

    srand(time(0));

    for (int i =0; i < num; i++)
    {
        int randNumber = 100000000+rand() % 9000;
        randNum[i] = randNumber;
        //randChar[i] = (char)randNumber;
    }


    for (int i =0; i < num; i++)
    {
        Sales = rand() / 10.00;
        randSale[i] =  Sales;
    }


    for (int i =0; i < num; i++)
    {
        int randNumber =  rand() % 26 + 97;
        //randNum[i] = randNumber;
        randChar[i] = (char)randNumber;
    }


    for (int i =0; i < num; i++)
    {
        catNum[i] = rand() % 20;
    }


    /// Youtube Famous

    for (int i =0; i < num; i++)
    {
         cuddleScore[i]= rand() % 20 + 1;

        if(cuddleScore[i] <=3 )
        {
            youTubeFamous[i] = 1;
        }

        else {
            youTubeFamous[i] = 0;
        }
    }


    ///--------------------------------------


    ///************************************************
    char randCatName[num];
    for (int i =0; i < num; i++)
    {
        int randNumber =  (rand() % 26 +97);
        //randNum[i] = randNumber;
        randCatName[i] = (char)randNumber;

    }







    ///***************************************************


    for (int i = 0; i < num; i++)
    {
        for (int x = 0; x < num; x++)
        {
            if (randNum[i] < randNum[x])
            {
                sortRand = randNum[i];
                randNum[i] = randNum[x];
                randNum[x] = sortRand;
            }
        }
    }


    for (int i =0; i < num; i++)
    {
        cout << randNum[i] << ":" << randChar[i] << " Cafe:" << randSale[i]
             << ":" << catNum[i] << ":" << youTubeFamous[i]
             << " " << randCatName[i]
             << endl;
    }




    for (int i =0; i < num; i++)
    {
        outFile << randNum[i] << ":" << randChar[i] << " Cafe:" << randSale[i]
             << ":" << catNum[i] << ":" << youTubeFamous[i]
             << " " << randCatName[i]
             << endl;
    }


    return 0;
}

