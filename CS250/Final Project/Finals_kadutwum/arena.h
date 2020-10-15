#ifndef ARENA_H_INCLUDED
#define ARENA_H_INCLUDED

#include <string>

using namespace std;

/// ADT
struct Date
{
    int month;
    int day;
    int year;
};

struct Schedule
{
    int hour;
    int minute;
    Date date;
    string eventName;

    /// inFile Declarations

    //int num = 5;
    string ename[5];
    int eHour[5];
    int eMin[5];
    int eMon[5];
    int eDay[5];
    int eYear[5];

    char bb = '/';
    char col = ':';

    ///Member Functions
    void printEvent();
};


struct ticket {
    int section;
    int seat;
    int price;
    int isAvailable;
    string eventName;
    Date date;
    int minute;
    bool sold;
    int id;

    ///Member Functions
    void fund();
};

struct parking
{
    int sect;
    int space;
    int price;
    bool avail;

    ///Member Functions
    void mark();
};

struct staff{
    int ID;
    string fname;
    string lname;
    string job;
    int salary;

    ///Member Functions
    void printStuff(ifstream& inStuff);
};

struct bank{
    int totalSales;
    int cashOnHand;
    int debt;

    ///Member Functions
    void estimate(ifstream& inBank, ofstream& outBank);
};

struct concession
{
    int NumHotDogs;
    int HotDogPrice = 0;
    int NumPeanuts;
    int PeanutsPrice = 0;
    int NumBurger;
    int BurgerPrice = 0;
    int NumDrinks;
    double DrinkPrice = 0;
    int NumBeer;
    int BeerPrice = 0;
    int NumJersey;
    int JerseyPrice = 0;
    double TotalSales = 0;

    ///Member Functions
    void print(ifstream& inCon);
};

///Main Menu Function Call
void mainMenu();

///Sub Menu Function Call
void tSubMenu();
void sSubMenu();
void pSubMenu();
void stSubMenu();
void bSubMenu();
void cSubMain();


/// Manage Tickets
void showTicket(ifstream& inTicket);
///void fund();


/// Manage Schedule
void createEvent(ofstream& outFile, Schedule eName);
//void printEvent(ifstream& inFile);
void available(ifstream& inTicket);

/// Manage Parking
void seat(ifstream& inPark);
void printBySect(ifstream& inPark);

/// Manage Staff
void createStuff(ofstream& outStuff);
//void printStuff(ifstream& inStuff);
void printById(ifstream& inStuff);
void printByJob(ifstream& inStuff);
void fire();


/// Manage Bank Account
void printAccount(ifstream& inBank);
void adjustBank(ifstream& inBank, ofstream& outBank);
void debtPay(ifstream& inBank, ofstream& outBank);
void calculate(ifstream& inBank, ofstream& outBank);
//void estimate(ifstream& inBank, ofstream& outBank);

/// Manage Concession Stand
void standInfo(ifstream& inCon);
void sell(ifstream& inCon, ofstream& outCon);
//void print(ifstream& inCon);
void order(ifstream& inCon, ofstream& outCon);
#endif // ARENA_H_INCLUDED
