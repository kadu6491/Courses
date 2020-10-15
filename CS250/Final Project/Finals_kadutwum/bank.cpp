#include <iostream>
#include <fstream>
#include <string>

#include "arena.h";

using namespace std;

void printAccount(ifstream& inBank)
{
    inBank.open("bank.txt");

    bank account;

    inBank >> account.totalSales >> account.cashOnHand;
    inBank >> account.debt;

    cout << "Total Sales: $" << account.totalSales << endl;
    cout << "Cash on hand: $" << account.cashOnHand << endl;
    cout << "Debt: $" << account.debt << endl << endl;

    inBank.close();
    mainMenu();
}

void adjustBank(ifstream& inBank, ofstream& outBank)
{
    //outBank.open("bank.txt");
    inBank.open("bank.txt");

    bank account;

    inBank >> account.totalSales >> account.cashOnHand;
    inBank >> account.debt;

    int ans;
    ///int cash;
    cout << "1. Total Sales" << endl;
    cout << "2. Cash on hands" << endl;
    cout << "3. Debt" << endl;
    cin >> ans;
    cout << endl;


    if(ans == 1)
    {
        cout << "Enter Total Sales: " << endl;
        cin >> account.totalSales;
        cout << endl << endl;

        mainMenu();
    }

    else if(ans == 2)
    {
        cout << "Enter cash on hands: " << endl;
        cin >> account.cashOnHand;
        cout << endl << endl;

        mainMenu();
    }

    else if(ans == 3)
    {
        cout << "Enter debt: " << endl;
        cin >> account.debt;
        cout << endl << endl;

        mainMenu();
    }

    else {
        cout << "Wrong input" << endl << endl;
        mainMenu();
    }

    inBank.close();

    outBank.open("bank.txt");

    outBank << account.totalSales << " " << account.cashOnHand
            << " " << account.debt << endl;

    outBank.close();
}

void calculate(ifstream& inBank, ofstream& outBank)
{
    mainMenu();
}

void debtPay(ifstream& inBank, ofstream& outBank)
{
    //outBank.open("bank.txt");
    inBank.open("bank.txt");

    bank account;
    int debt;

    inBank >> account.totalSales >> account.cashOnHand;
    inBank >> account.debt;

    cout << "Enter the amount you want to pay" << endl;
    cin >> debt;
    cout << endl << endl;

    account.debt = account.debt - debt;

    inBank.close();

    outBank.open("bank.txt");

    outBank << account.totalSales << " " << account.cashOnHand
            << " " << account.debt << endl;

    outBank.close();

    mainMenu();

}

void bank::estimate(ifstream& inBank, ofstream& outBank)
{
    //outBank.open("bank.txt");
    inBank.open("bank.txt");
    int debt;
    int mon;
    double est;

    cout << "Enter total debt: $";
    cin >> debt;

    cout << "Enter monthly payment: $";
    cin >> mon;

    est = (debt / mon) / 12;

    cout << "Debt will be paid in " << est << " years" << endl << endl;
    mainMenu();
}



void bSubMenu()
{
    ifstream inBank;
    ofstream outBank;

    bank myBank;

    int bChoose;

    cout << "1. Print bank account information" << endl;
    cout << "2. Adjust bank account information" << endl;
    cout << "3. Calculate a given month's ticket sales" << endl;
    cout << "4. Pay off debt" << endl;
    cout << "5. Estimate time until debt is payed of" << endl;
    cout << "6. Main Menu" << endl;

    cin >> bChoose;
    cout << endl << endl;

    if(bChoose == 1)
    {
        printAccount(inBank);
    }

    else if(bChoose == 2)
    {
        adjustBank(inBank, outBank);
    }

    else if(bChoose == 3)
    {
        calculate(inBank, outBank);
    }

    else if(bChoose == 4)
    {
        debtPay(inBank, outBank);
    }

    else if(bChoose == 5)
    {
        myBank.estimate(inBank, outBank);
    }

    else {
        mainMenu();
    }

}

