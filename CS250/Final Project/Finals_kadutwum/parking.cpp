#include <iostream>
#include <fstream>
#include <string>

#include "arena.h";

using namespace std;

void seat(ifstream& inPark)
{
    inPark.open("parking.txt");

    int num = 600;

    parking park[num];

    for(int i = 0; i < num; i++)
    {
        inPark >> park[i].sect >> park[i].space >> park[i].price;
        inPark >> park[i].avail;

        cout << "Section: " << park[i].sect << endl;
        cout << "Space: " << park[i].space << endl;
        cout << "Price: $" << park[i].price << endl;
        cout << "Available: " << park[i].avail << endl;

        cout << endl;


    }

        inPark.close();

        mainMenu();
}


void printBySect(ifstream& inPark)
{
    inPark.open("parking.txt");

    int num = 30;
    int section;

    parking park[num];

    cout << "Enter Section number you want to print" << endl;
    cin >> section;

    for(int i = 0; i < num; i++)
    {
        inPark >> park[i].sect >> park[i].space >> park[i].price;
        inPark >> park[i].avail;

        if(section == park[i].sect)
            {
                cout << "Section: " << park[i].sect << endl;
                cout << "Space: " << park[i].space << endl;
                cout << "Price: $" << park[i].price << endl;
                cout << "Available: " << park[i].avail << endl;
            }

            cout << endl;
    }

        inPark.close();
        mainMenu();

}


void parking::mark()
{
    int Sans, Spans, Vans;

    cout << "Section: ";
    cin >> Sans;

    cout << "Space: ";
    cin >> Spans;

    cout << "Available: ";
    cin >> Vans;

    cout << endl << endl;


    mainMenu();

}

void pSubMenu()
{
    ifstream inPark;

    parking myMark;

    int pChoose;
    cout << "1. Show all parking info" << endl;
    cout << "2. Print parking info by Section" << endl;
    cout << "3. Print parking sections with a certain amount of empty spaces" << endl;
    cout << "4. Mark parking space empty/taken" << endl;
    cout << "5. Main Menu" << endl << endl;

    cin >> pChoose;
    cout << endl << endl;

    if(pChoose == 1)
    {
        seat(inPark);
    }

    else if(pChoose == 2)
    {
        printBySect(inPark);
    }

    else if(pChoose == 3)
    {
        mainMenu();
    }

    else if(pChoose == 4)
    {
        myMark.mark();
    }

    else {
        mainMenu();
    }
}
