#include <iostream>
#include <fstream>
#include <string>

#include "arena.h";

using namespace std;

void showTicket(ifstream& inTicket)
{
    inTicket.open("ticket.txt");

    int num = 5;
    ticket tNum[num];

    for(int i = 0; i < num; i++)
    {
        inTicket >> tNum[i].section >> tNum[i].seat >> tNum[i].price;
        inTicket >> tNum[i].isAvailable >> tNum[i].eventName;

        cout << "section Number: " << tNum[i].section << endl;
        cout << "Seat Number: " << tNum[i].seat << endl;
        cout << "Price: $" << tNum[i].price << endl;
        cout << "Available: " << tNum[i].isAvailable << endl;
        cout << "Name: " << tNum[i].eventName << endl << endl;
    }

    inTicket.close();
    mainMenu();
}

void available(ifstream& inTicket)
{
    inTicket.open("ticket.txt");

    int num = 5000;
    ticket tNum[num];

    for(int i = 0; i < num; i++)
    {
        inTicket >> tNum[i].section >> tNum[i].seat >> tNum[i].price;
        inTicket >> tNum[i].isAvailable >> tNum[i].eventName;

        cout << "section Number: " << tNum[i].section << endl;
        cout << "Seat Number: " << tNum[i].seat << endl;
        cout << "Price: $" << tNum[i].price << endl;
        cout << "Available: " << tNum[i].isAvailable << endl;
        cout << "Name: " << tNum[i].eventName << endl << endl;
    }

    inTicket.close();
    mainMenu();
}


void ticket::fund()
{
    char ans;
    int sect;
    int seat;
    int sectre;
    int seatre;

    cout << "Would you like to refund a ticket (y/n)" << endl;
    cin >> ans;

    if(ans == 'n')
    {
        cout << "Enter the section number of the ticket you want to buy" << endl;
        cin >> sect;

        cout << "Enter the seat number of the ticket you want to buy" << endl;
        cin >> seat;

        cout << endl << endl;

        mainMenu();
    }

    else if(ans == 'y')
    {
        cout << "Enter the section number you want to refund" << endl;
        cin >> sectre;

        cout << "Enter the seat number you want to refund" << endl;
        cin >> seatre;

        cout << endl << endl;

        mainMenu();
    }
}




void tSubMenu()
{
    ifstream inTicket;

    ticket myFund;

    int tChoose;

    cout << "1. Show all ticket info" << endl;
    cout << "2. Show all available tickets" << endl;
    cout << "3. Show all unavailable tickets" << endl;
    cout << "4. Sell/Refund ticket" << endl;
    cout << "5. Update ticket info" << endl;
    cout << "6. Search for ticket by date" << endl;
    cout << "7. Main Menu" << endl;

    cin >> tChoose;
    cout << endl << endl;

    if(tChoose == 1)
    {
        showTicket(inTicket);
    }

    else if(tChoose == 2)
    {
        available(inTicket);
    }

    else if(tChoose == 4)
    {
        myFund.fund();
    }

    else {
        mainMenu();
    }
}
