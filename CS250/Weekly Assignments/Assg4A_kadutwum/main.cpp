#include <iostream>
#include <fstream>
#include <iomanip>

#include "inv.h"

using namespace std;






int main()
{
    cout << "This is Assignment 1 (Structs) - Restaurant" << endl;

    /**
    Declaring the input streams for each file
    **/
    ifstream inFileFood;
    ifstream inFileEmployee;
    ifstream inFileCondiment;
    ifstream inFilePlasticItem;

    int num = 0;
    Food foodlist[num];

    inFileFood.open("Food.txt");
    inFileEmployee.open("Employee.txt");
    inFileCondiment.open("Condiment.txt");
    inFilePlasticItem.open("PlasticItem.txt");



    /**
    If the any of the file cannot be opened then the program terminates displaying
    the error message
    **/
    if (!inFileFood)
    {
        cout << "The Food input file does not exist. Program terminates!" << endl;
        return 1;
    }
    if (!inFileEmployee)
    {
        cout << "The Employee input file does not exist. Program terminates!" << endl;
        return 1;
    }
    if (!inFileCondiment)
    {
        cout << "The Condiment input file does not exist. Program terminates!" << endl;
        return 1;
    }
    if (!inFilePlasticItem)
    {
        cout << "The PlasticItem input file does not exist. Program terminates!" << endl;
        return 1;
    }

    inFileFood.close();
    inFileEmployee.close();
    inFileCondiment.close();
    inFilePlasticItem.close();

    /**
    Display the prompt and do the requested action. Keep repeating the prompt until exit.
    If the number entered is not an option, just repeat the prompt.
    **/
    int chosenOption;

    do{
        cout << "\n\n\nSelect which option you would like to see \n"
         << "1. Print all Food \n"
         << "2. Print all Employees \n"
         << "3. Print all Condiments \n"
         << "4. Print all Plastic Items \n"
         << "5. Exit \n"
         << "\n"
         << "Enter Option (1-5): ";
        cin >> chosenOption;

        /**
        Do the correct action according to the chosenOption
        **/
        switch(chosenOption)
        {
        case 1:
            /**
            Food
            Function call to read data from input file. That function then calls a print Function
            **/

            getFoodData(inFileFood);
            break;
        case 2:
            /**
            Employees
            Function call to read data from input file. That function then calls a print Function
            **/
            getEmployeeData(inFileEmployee);

            break;
        case 3:
            /**
            Condiments
            Function call to read data from input file. That function then calls a print Function
            **/
            getCondimentData(inFileCondiment);

            break;
        case 4:
            /**
            Plastic Items
            Function call to read data from input file. That function then calls a print Function
            **/
            getPlasticItemsData(inFilePlasticItem);
            break;
        default:
            break;
        }

    }while(chosenOption != 5);


    return 0;
}



/**
Displaying the content from the food struct array on the monitor
**/
void displayFood(Food food[], int totalFood){

   ofstream outFile;

   outFile.open("SequencedOrders.txt");


   cout << setw(10) << "Name" << "  | "
        << "Number left" << "  | "
        << "Sell By Date" << "  | "
        << "Cost" << endl;

    outFile << setw(10) << "Name" << "  | "
        << "Number left" << "  | "
        << "Sell By Date" << "  | "
        << "Cost" << endl << endl;

    printHorizontalLine(80,'-');

    for(int i = 0; i < totalFood; i++){
        cout << left << setw(9) << food[i].name << "   | "
             << right << setw(6) << setfill(' ') << food[i].numberLeft << "     | "
             << right <<setw(2) << setfill('0') << food[i].sellByDate.month <<":" << setw(2) << setfill('0') << food[i].sellByDate.day <<":" << setw(2) << setfill('0') << food[i].sellByDate.year << " | "
             << right <<setw(9) << setfill(' ') << food[i].cost << " "
             << setfill(' ') << endl;
    }

    for(int i = 0; i < totalFood; i++){
     outFile << left << setw(9) << food[i].name << "   | "
             << right << setw(6) << setfill(' ') << food[i].numberLeft << "     | "
             << right <<setw(2) << setfill('0') << food[i].sellByDate.month <<":" << setw(2) << setfill('0') << food[i].sellByDate.day <<":" << setw(2) << setfill('0') << food[i].sellByDate.year << " | "
             << right <<setw(9) << setfill(' ') << food[i].cost << " "
             << setfill(' ') << endl;
    }

    outFile << endl << endl;

}

/**
Displaying the content from the Condiments struct array on the monitor
**/
void displayCondiments(Condiment condiment[], int totalCondiments){

   ofstream outFile;

   outFile.open("SequencedOrders.txt", ofstream::app);


   cout << setw(8) << "Name" << " | "
        << "Ounces Left" << " | "
        << "Date of Birth" << " | "
        << "Sell By Date" << " | "
        << "Cost" <<  endl;

    outFile << setw(8) << "Name" << " | "
        << "Ounces Left" << " | "
        << "Date of Birth" << " | "
        << "Sell By Date" << " | "
        << "Cost" <<  endl << endl;

    printHorizontalLine(80,'-');

    for(int i = 0; i < totalCondiments; i++){
        cout << left << setw(15) << setfill(' ') << condiment[i].name << " | "
             << right << setw(5) << setfill(' ') << condiment[i].ouncesLeft << " |  "
             << right <<setw(2) << setfill('0') << condiment[i].sellByDate.month <<":" << setw(2) << setfill('0') << condiment[i].sellByDate.day <<":" << setw(2) << setfill('0') << condiment[i].sellByDate.year << "   |    "
             << right << setw(3) << setfill(' ') << condiment[i].cost << " "
             << setfill(' ') << endl;
    }

    for(int i = 0; i < totalCondiments; i++){
        outFile << left << setw(15) << setfill(' ') << condiment[i].name << " | "
             << right << setw(5) << setfill(' ') << condiment[i].ouncesLeft << " |  "
             << right <<setw(2) << setfill('0') << condiment[i].sellByDate.month <<":" << setw(2) << setfill('0') << condiment[i].sellByDate.day <<":" << setw(2) << setfill('0') << condiment[i].sellByDate.year << "   |    "
             << right << setw(3) << setfill(' ') << condiment[i].cost << " "
             << setfill(' ') << endl;
    }

    outFile << endl << endl;
}


/**
Displaying the content from the Employees struct array on the monitor
**/
void displayEmployees(Employee employees[], int totalEmployees){

   ofstream outFile;

   outFile.open("SequencedOrders.txt", ofstream::app);


   cout << setw(10) << "Name" << "  | "
        << "Id" << "  | "
        << "Salary" << "  | "
        << "Rating" << " | "
        << "Hire Date" <<  endl;

    outFile << setw(10) << "Name" << "  | "
        << "Id" << "  | "
        << "Salary" << "  | "
        << "Rating" << " | "
        << "Hire Date" <<  endl << endl;

    printHorizontalLine(80,'-');

    for(int i = 0; i < totalEmployees; i++){
        cout << left  << setw(9) << employees[i].name << "   |   "
             << right << setw(2) << employees[i].id << "    |  "
             << right << setw(4) << setfill(' ') << employees[i].salary << "    |  "
             << right << setw(6) << employees[i].rating << "    |  "
             << right << setw(2) << setfill('0') << employees[i].hireDate.month <<":" << setw(2) << setfill('0') << employees[i].hireDate.day <<":" << setw(2) << setfill('0') << employees[i].hireDate.year << "   |  "
             << setfill(' ') << endl;
    }

    for(int i = 0; i < totalEmployees; i++){
        outFile << left  << setw(9) << employees[i].name << "   |   "
             << right << setw(2) << employees[i].id << "    |  "
             << right << setw(4) << setfill(' ') << employees[i].salary << "    |  "
             << right << setw(6) << employees[i].rating << "    |  "
             << right << setw(2) << setfill('0') << employees[i].hireDate.month <<":" << setw(2) << setfill('0') << employees[i].hireDate.day <<":" << setw(2) << setfill('0') << employees[i].hireDate.year << "   |  "
             << setfill(' ') << endl;
    }


    outFile << endl << endl;
}

/**
Displaying the content from the Personnel struct array on the monitor
**/
void displayPlasticItems(PlasticItem plasticItem[], int totalPlasticItems){

   ofstream outFile;

   outFile.open("SequencedOrders.txt", ofstream::app);

   cout << setw(10) << "Name" << " | "
        << "Number Left" << " | "
        << "Cost" <<  endl;

   outFile << setw(10) << "Name" << " | "
        << "Number Left" << " | "
        << "Cost" <<  endl << endl;

    printHorizontalLine(80,'-');

    for(int i = 0; i < totalPlasticItems; i++){
        cout << left  << setw(9) << plasticItem[i].name << "   |  "
             << right << setw(7) << setfill(' ') << plasticItem[i].numberLeft << "  | "
             << right << setw(9) << setfill(' ') << plasticItem[i].cost << " "
             << setfill(' ') << endl;
    }

    for(int i = 0; i < totalPlasticItems; i++){
        outFile << left  << setw(9) << plasticItem[i].name << "   |  "
             << right << setw(7) << setfill(' ') << plasticItem[i].numberLeft << "  | "
             << right << setw(9) << setfill(' ') << plasticItem[i].cost << " "
             << setfill(' ') << endl;
    }
}
