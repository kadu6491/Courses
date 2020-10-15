#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>

using namespace std;

struct Cats{
    int cafeID = 0;
    char cafeName;
    string cafeName2;
    double monthlySale = 0;
    int avgCuddles = 0;
    int numCats = 0;
};

int main()
{
    ifstream inFile;
    ofstream outFile;
    int num = 5000;
    Cats kitties[num];
    char col = ':';
    string space = " ";

    inFile.open("kittycafes.txt");
    outFile.open("results.txt");

    for(int i = 0; i < num; i++)
    {
        inFile >> kitties[i].cafeID >> col  >> kitties[i].cafeName >> kitties[i].cafeName2;
        inFile >> col >> kitties[i].monthlySale;
        inFile >> col >> kitties[i].avgCuddles;
        inFile >> col >> kitties[i].numCats;
    }

    for(int i = 0; i < num; i++)
    {
        cout << kitties[i].cafeID <<"\t\t" << kitties[i].cafeName << kitties[i].cafeName2
             << "\t\t" << kitties[i].monthlySale
             << "\t\t" << kitties[i].avgCuddles
             << "\t\t" << kitties[i].numCats

             << endl;
    }

    for(int i = 0; i < num; i++)
    {
        outFile << kitties[i].cafeID <<"\t\t" << kitties[i].cafeName << kitties[i].cafeName2
                << "\t\t" << kitties[i].monthlySale
                << "\t\t" << kitties[i].avgCuddles
                << "\t\t" << kitties[i].numCats

                << endl;
    }


    return 0;
}
