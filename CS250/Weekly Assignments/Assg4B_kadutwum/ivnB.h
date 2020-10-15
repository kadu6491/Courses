#ifndef IVNB_H_INCLUDED
#define IVNB_H_INCLUDED

#include <string>

using namespace std;

struct Date{
	int day;
	int month;
	int year;

	Date()
	{
	    day = 0;
	    month = 0;
	    year = 0;
	}
};

struct Food{
    string name;
    int numberLeft;
    Date sellByDate;
    double cost;

    Food();
    Food(string name);
    bool compareFoodByCost(Food other);
};


struct Employee{
    string name;
    int id;
    double salary;
    Date hireDate;
    int rating;

    Employee();
    Employee(string name);
    //bool compareEmployeeBySalary(Employee other);
};





struct Condiment{
    string name;
    double ouncesLeft;
    Date sellByDate;
    double cost;

    Condiment();
    Condiment(string name);
    bool compareCondimentByCost(Condiment other);
};





struct PlasticItem{
    string name;
    int numberLeft;
    double cost;

    PlasticItem();
    PlasticItem(string name);
    bool comparePlasticItemByCost(PlasticItem other);
};





/**
These four functions are mandatory as instructed in the Assignment
**/
void getFoodData(ifstream& inFile);
void getEmployeeData(ifstream& inFile);
void getCondimentData(ifstream& inFile);
void getPlasticItemsData(ifstream& inFile);

/**
Optional Helper Functions
**/
void displayFood(Food food[], int totalFood);
void displayEmployees(Employee employee[], int totalEmployees);
void displayCondiments(Condiment condiment[], int totalCondiments);
void displayPlasticItems(PlasticItem plasticItem[], int totalPlastiicItems);

void printHorizontalLine( int width, char border_char);
void print

#endif // IVNB_H_INCLUDED
