#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>

using namespace std;

/// Date, Food, Employee, Condiments and Plastic Items Structs
struct Date {
    int month;
    int day;
    int year;
};

struct Food {
    string name;
    int remNum;
    Date date;
    double cost;
};

struct Employee {
    string name;
    int Id;
    Date date;
    double salary;
    int rate;
} ;

struct Condiment {
    string name;
    int ounce;
    Date date;
    double cost;
} ;

struct PlasticItem {
    string name;
    int remNum;
    double cost;
} ;


void option();
void food(ifstream& inFile);
void employee(ifstream& inFile);
void condiment(ifstream& inFile);
void plasticItem(ifstream& inFile);
void choice(int option);

int main()
{
    ///Declare Variables
    ifstream inFile;
    int input;

    /// Main Page Function Call
    option();
    cout << endl << endl;

    /// Print out the input File on User request
    choice(input);

    //food(inFile);

    //cout << endl << endl;
    //employee(inFile);

    //cout << endl << endl;
    //condiment(inFile);

    //cout << endl << endl;
    //plasticItem(inFile);

    return 0;
}

void option()
{
    cout << "This is Assignment 1 <Structs> - Restaurant" << endl;
    cout << endl << endl << endl;

    /// Giving User the options to out files
    cout << "Select which option you would like to see" << endl;
    cout << "1. Print all Food" << endl;
    cout << "2. Print all Employee" << endl;
    cout << "3. Print all Condiments" << endl;
    cout << "4. Print all Plastic Items" << endl;
    cout << "5. Exit" << endl;
}


/// Function for to read Food file
void food(ifstream& inFile)
{
    int counter = 0;
    char c1 = ':';

    inFile.open("Food.txt");
    inFile >> counter;
    Food food1[counter];
    for(int x = 0; x < counter; x++)
    {
        inFile >> food1[x].name >> food1[x].remNum;
        inFile >> food1[x].date.month >> c1 >> food1[x].date.day;
        inFile >> c1 >> food1[x].date.year >> food1[x].cost;
    }

    //Food * foodlist;
    //int numFoods;
    Food tempFood;

    for(int i = 0; i < counter; i++)
    {
        for(int j = 0; j < counter; j++)
        {
            if(food1[i].cost < food1[j].cost)
            {
                tempFood = food1[i];
                food1[i] = food1[j];
                food1[j] = tempFood;
            }
        }
    }

    cout <<"================================================================" << endl;
    cout <<"\t\t\t      Food" << endl;
    cout <<"================================================================" << endl;
    cout <<"    Name   : Number left : Sell By Date :  Cost " << endl;
    cout <<"----------------------------------------------------------------" << endl;

    for(int x = 0; x < counter; x++)
    {
        cout <<setw(10) << left << food1[x].name << setw(2) << right << ":"
             << setw(8) << right << food1[x].remNum;

             if(food1[x].date.month < 10)
                {
                   cout << setw(6) << right << ":" << setw(3) << "0" << food1[x].date.month;
                }
                else {
                    cout << setw(6) << right << ":" << setw(4) << food1[x].date.month;
                }

                if ( food1[x].date.day < 10 )
                {
                    cout << c1 << "0" << food1[x].date.day;
                }
                else {
                    cout << c1 << food1[x].date.day;
                }


             cout << c1 << food1[x].date.year
                  << setw(3) << right << ":";

                  if(food1[x].cost > 10)
                  {
                      cout << setw(7) << right << setprecision(1) << fixed << food1[x].cost << endl;
                  }

                  else{
                      cout << setw(7) << right << setprecision(2) << fixed << food1[x].cost << endl;
                  }


    }

    inFile.close();
}

/// Function for to read Employee file
void employee(ifstream& inFile)
{
    int counter = 0;
    char c1 = ':';

    inFile.open("Employee.txt");
    inFile >> counter;
    Employee employee1[counter];
    for(int x = 0; x < counter; x++)
    {
        inFile >> employee1[x].name >> employee1[x].Id;
        inFile >> employee1[x].salary >> employee1[x].date.month;
        inFile >> c1 >> employee1[x].date.day;
        inFile >> c1 >> employee1[x].date.year >> employee1[x].rate;
    }

    cout <<"================================================================" << endl;
    cout <<"\t\t\t      Employee" << endl;
    cout <<"================================================================" << endl;
    cout <<"    Name    :  ID :   Salary   :  Hire Date : Rating (1-10) " << endl;
    cout <<"----------------------------------------------------------------" << endl;

    for(int x = 0; x < counter; x++)
    {

        cout << setw(10) << left << employee1[x].name
             << setw(3) << right << ":" << setw(4) << employee1[x].Id
             << setw(2) << right << ":" << setw(10) << right << setprecision(2) << fixed << employee1[x].salary;

                if(employee1[x].date.month < 10)
                {
                   cout << setw(3) << right << ":" << setw(2) << "0" << employee1[x].date.month;
                }
                else {
                    cout << setw(3) << right << ":" << setw(2) << employee1[x].date.month;
                }

                if ( employee1[x].date.day < 10 )
                {
                    cout << c1 << "0" << employee1[x].date.day;
                }
                else {
                    cout << c1 << employee1[x].date.day;
                }

             cout << c1 << employee1[x].date.year
                  << setw(2) << right << ":" << setw(6) << right << employee1[x].rate << endl;
    }

    inFile.close();
}

/// Function for to read Condiment file
void condiment(ifstream& inFile)
{
    int counter = 0;
    char c1 = ':';

    inFile.open("Condiment.txt");
    inFile >> counter;
    Condiment condiment1[counter];
    for(int x = 0; x < counter; x++)
    {
        inFile >> condiment1[x].name >> condiment1[x].ounce;
        inFile >> condiment1[x].date.month;
        inFile >> c1 >> condiment1[x].date.day;
        inFile >> c1 >> condiment1[x].date.year >> condiment1[x].cost;
    }

        cout <<"================================================================" << endl;
        cout <<"\t\t\t  Condiment " << endl;
        cout <<"================================================================" << endl;
        cout <<"     Name    : Number Remaining : Expiration Date :  Cost " << endl;
        cout <<"----------------------------------------------------------------" << endl;


    for(int x = 0; x < counter; x++)
    {
        cout <<showpoint << setprecision(2) << fixed;

         cout << setw(12) << left << condiment1[x].name
              << setw(2) << right << ":" << setw(10) << right << condiment1[x].ounce;


        if ( condiment1[x].date.month < 10)
        {
             cout << setw(9) << right << ":"  << setw(4.5) << "0" << condiment1[x].date.month;
        }

        else {
            cout << setw(9) << right << ":" << setw(5.5) << condiment1[x].date.month;
        }

        if ( condiment1[x].date.day < 10)
        {
            cout << c1 << "0" << condiment1[x].date.day;
        }

        else {
            cout << c1 << condiment1[x].date.day;
        }




         cout << c1 << condiment1[x].date.year
              << setw(5) << right << ":" << setw(6) << condiment1[x].cost << endl;
    }

    inFile.close();
}

/// Function for to read Plastic Item file
void plasticItem(ifstream& inFile)
{
    int counter = 0;
    ///char c1 = ':';

    inFile.open("PlasticItem.txt");
    inFile >> counter;
    PlasticItem plasticItem1[counter];
    for(int x = 0; x < counter; x++)
    {
        inFile >> plasticItem1[x].name >> plasticItem1[x].remNum;
        inFile >> plasticItem1[x].cost;
    }
    cout <<"================================================================" << endl;
    cout <<"\t\t\t Plastic Items" << endl;
    cout <<"================================================================" << endl;
    cout <<"    Name    : Number Remaining :  Cost " << endl;
    cout <<"----------------------------------------------------------------" << endl << endl;

    for(int x = 0; x < counter; x++)
    {
        cout <<showpoint << setprecision(2) << fixed;

        cout << setw(10) << left << plasticItem1[x].name
             << setw(3) << right << ":" << setw(10) << right << plasticItem1[x].remNum
             << setw(9) << right << ":" << setw(6) << plasticItem1[x].cost << endl;
    }

    inFile.close();
}

/// Function for user input what files user want to view
/// To also print out the files
void choice(int option)
{
    ifstream inFile;

    cout << "Enter Option <1-5>: ";
    cin >> option;

    /// Tell user try again if user entered a number greater than 5
    /// It repeat till user enter a number betwwen 1 - 5
    /*
    cout << "Enter Option <1-5>: ";
    cin >> option;
    while(option > 5)
        {
            cout << "You Entered Invalid Number" << endl << endl;
            cout << "Enter Option <1-5>: ";
            cin >> option;
        }

    */

    cout << endl << endl;

    if(option == 1)
    {
        food(inFile);
    }

    else if(option == 2)
    {
        employee(inFile);
    }

    else if (option == 3)
    {
        condiment(inFile);
    }

    else if (option == 4)
    {
        plasticItem(inFile);
    }


    else{
       cout << "EXITING........." << endl;
    }


}
