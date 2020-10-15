#include <iostream>
#include <fstream>
#include <iomanip>

#include "inv.h"

using namespace std;

Food::Food()
{
    name = "";
    numberLeft = 0;
    cost = 0.0;
}

Food::Food(string Name)
{
    name = Name;
    numberLeft = 0;
    cost = 0.0;
}

void sortFoodByCost(Food * foodlist, int numFoods)
{
    Food tempFood;

    for(int i = 0; i < numFoods; i++)
    {
        for(int j = 0; j < numFoods; j++)
        {
            if(foodlist[i].cost < foodlist[j].cost)
            {
                tempFood = foodlist[i];
                foodlist[i] = foodlist[j];
                foodlist[j] = tempFood;
            }
        }
    }

}

bool Food::compareFoodByCost(Food other)
{
    if(this->cost > other.cost)
    {
        return true;
    }

    else{
        return false;
    }
}

bool compareFoodByCost(Food lefthand, Food righthand)
{
    if(lefthand.cost > righthand.cost)
    {
        return true;
    }

    else{
        return false;
    }
}

Employee::Employee()
{
    name = "";
    id = 0;
    salary = 0.0;
    rating = 0;
}

Employee::Employee(string Name)
{
    name = Name;
    id = 0;
    salary = 0.0;
    rating = 0;
}

/*
bool Employee::compareEmployeeBySalary(Employee other)
{
    if(this->salary > other.salary)
    {
        return true;
    }

    else{
        return false;
    }
}

*/

Condiment::Condiment()
{
    name = "";
    ouncesLeft = 0;
    cost = 0.0;
}

Condiment::Condiment(string Name)
{
    name = Name;
    ouncesLeft = 0;
    cost = 0.0;
}


void sortCondimentByCost(Condiment * condlist, int numConds)
{
    Condiment tempCond;

    for(int i = 0; i < numConds; i++)
    {
        for(int j = 0; j < numConds; j++)
        {
            if(condlist[i].cost < condlist[j].cost)
            {
                tempCond = condlist[i];
                condlist[i] = condlist[j];
                condlist[j] = tempCond;
            }
        }
    }

}

bool Condiment::compareCondimentByCost(Condiment other)
{
    if(this->cost > other.cost)
    {
        return true;
    }

    else{
        return false;
    }
}

bool compareCondimentByCost(Condiment lefthand, Condiment righthand)
{
    if(lefthand.cost > righthand.cost)
    {
        return true;
    }

    else{
        return false;
    }
}


PlasticItem::PlasticItem()
{
    name = "";
    numberLeft = 0;
    cost = 0.0;
}

PlasticItem::PlasticItem(string Name)
{
    name = Name;
    numberLeft = 0;
    cost = 0.0;
}


void sortPlasticItemByCost(PlasticItem * Itemlist, int numItems)
{
    PlasticItem tempItem;

    for(int i = 0; i < numItems; i++)
    {
        for(int j = 0; j < numItems; j++)
        {
            if(Itemlist[i].cost < Itemlist[j].cost)
            {
                tempItem = Itemlist[i];
                Itemlist[i] = Itemlist[j];
                Itemlist[j] = tempItem;
            }
        }
    }

}

bool PlasticItem::comparePlasticItemByCost(PlasticItem other)
{
    if(this->cost > other.cost)
    {
        return true;
    }

    else{
        return false;
    }
}

bool comparePlasticItemByCost(PlasticItem lefthand, PlasticItem righthand)
{
    if(lefthand.cost > righthand.cost)
    {
        return true;
    }

    else{
        return false;
    }
}










void printHorizontalLine( int width, char border_char){
    cout.fill( border_char );
    cout << setw( width ) << border_char << "\n";
    cout.fill(' ');
}

void printHeading( string title, int width ){
    //Declare Variables
    int magic_width = 0;

    magic_width =  (width/2) - (title.length()/2) + title.length();

    cout << "\n";
    cout << left  << setfill('=') << setw( width ) << "=" << "\n";
    cout << right << setfill(' ') << setw( magic_width ) << title << "\n";
    cout << right << setfill('=') << setw( width ) << "=" << endl;

    //reset count
    cout.clear();
    cout.fill(' ');

    //VOID functions do NOT return a value
}

/**
Using the input stream sent as parameter we are reading the content from the Food.txt and storing it in the food struct array
**/
void getFoodData(ifstream& inFile){


    inFile.open("Food.txt");

    int totalFood;
    inFile >> totalFood;

    Food food[totalFood];

    char decimal;

    for(int i = 0; i < totalFood; i++){
        inFile >> food[i].name;
        inFile >> food[i].numberLeft;
        inFile >> food[i].sellByDate.month >> decimal >> food[i].sellByDate.day >> decimal >> food[i].sellByDate.year;
        inFile >> food[i].cost;
    }


    sortFoodByCost(food, totalFood);

    inFile.close();

    printHeading("Food", 60);

    displayFood(food,totalFood);
}

/**
Using the input stream sent as parameter we are reading the content from the condiments.txt and storing it in the condiments struct array
**/
void getCondimentData(ifstream& inFile){

    inFile.open("Condiment.txt");

    int totalCondiments;
    inFile >> totalCondiments;

    Condiment condiments[totalCondiments];

    char decimal;

    for(int i = 0; i < totalCondiments; i++){
        inFile >> condiments[i].name;
        inFile >> condiments[i].ouncesLeft;
        inFile >> condiments[i].sellByDate.month >> decimal >> condiments[i].sellByDate.day >> decimal >> condiments[i].sellByDate.year;
        inFile >> condiments[i].cost;
    }


    sortCondimentByCost(condiments, totalCondiments);

    inFile.close();

    printHeading("Condiments", 60);

    displayCondiments(condiments,totalCondiments);
}


/**
Using the input stream sent as parameter we are reading the content from the Employee.txt and storing it in the employees struct array
**/
void getEmployeeData(ifstream& inFile){

    inFile.open("Employee.txt");

    int totalEmployees;
    inFile >> totalEmployees;

    Employee employees[totalEmployees];

    char decimal;

    for(int i = 0; i < totalEmployees; i++){
        inFile >> employees[i].name;
        inFile >> employees[i].id;
        inFile >> employees[i].salary;
        inFile >> employees[i].hireDate.month >> decimal >> employees[i].hireDate.day >> decimal >> employees[i].hireDate.year;
        inFile >> employees[i].rating;
    }


    inFile.close();

    printHeading("Employees", 60);

    displayEmployees(employees,totalEmployees);
}

/**
Using the input stream sent as parameter we are reading the content from the Personnel.txt and storing it in the plasticItem struct array
**/
void getPlasticItemsData(ifstream& inFile){

    inFile.open("PlasticItem.txt");

    int totalPlasticItems;
    inFile >> totalPlasticItems;

    PlasticItem plasticItem[totalPlasticItems];

    for(int i = 0; i < totalPlasticItems; i++){
        inFile >> plasticItem[i].name;
        inFile >> plasticItem[i].numberLeft;
        inFile >> plasticItem[i].cost;
    }


    sortPlasticItemByCost(plasticItem, totalPlasticItems);

    inFile.close();

    printHeading("Plastic Items", 60);

    displayPlasticItems(plasticItem,totalPlasticItems);
}
