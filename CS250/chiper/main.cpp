#include <iostream>
#include <string>
#include <stdlib.h>
#include <sstream>
#include <iomanip>
#include <fstream>

#include "inbound.h"
#include "cipher.h"

using namespace std;

/// int Icounter=0;
/// int Scounter=0;


int loadSalesData(std::string salesData[][5], std::fstream& infile, int Scounter);
void loadInventoryData(std::string inventoryData[][4],std::fstream& infile, int Icounter);
char menu();
void manager(int Icounter, int Scounter);


int main()
{
    int Icounter=0;
    int Scounter=0;

    manager(Icounter, Scounter);
    return 0;
}


///+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
///EVERYTHING BELOW THIS LINE STAYS IN MAIN
///+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

int loadSalesData(std::string salesData[][5], std::fstream& infile, int Scounter)
{
    /// int Scounter = 0;

    int recentSALEnum=0;
    for(int i=0; i< Scounter; i++)
    {
        getline(infile, salesData[i][0], ':');
        recentSALEnum=atoi(salesData[i][0].c_str());
        getline(infile, salesData[i][1], ':');
        getline(infile, salesData[i][2], ':');
        getline(infile, salesData[i][3], ':');
        getline(infile, salesData[i][4]);
    }
    return recentSALEnum;
}

void loadInventoryData(std::string inventoryData[][4],std::fstream& infile, int Icounter)
{
    /// int Icounter = 0;
    for(int i=0; i< Icounter; i++)
    {
        getline(infile, inventoryData[i][0], ':');
        getline(infile, inventoryData[i][1], ':');
        getline(infile, inventoryData[i][2], ':');
        getline(infile, inventoryData[i][3]);
    }

}

char menu()
{
    char selection=' ';
    while(true)
    {
        std::cout<<std::endl;
        std::cout<<" Main Menu"<<std::endl;
        std::cout<<"1 New Sales"<<std::endl;
        std::cout<<"2 Print Sales Report"<<std::endl;
        std::cout<<"3 Print Inventory Report"<<std::endl;
        std::cout<<"4 Inbound Inventory"<<std::endl;
        std::cout<<"5 Exit"<<std::endl;
        std::cout<<"\nEnter Selection: ";
        std::cin>>selection;
        std::cin.ignore();
        std::cin.clear();
        if(selection>'0' && selection<'6')
            return selection;
        std::cout<<"PLEASE ENTER A VALID SELECTION\n\n"<<std::endl;
    }
}

void manager(int Icounter, int Scounter)
{
    std::fstream infile;



    while(true)
    {
        infile.open("inventory.txt", std::fstream::in);
        infile>>Icounter;
        // cout << Icounter << endl;
        infile.ignore();
        ///inventory is reloaded for every selection. this allows for updates to the inventory file
        std::string inventory[Icounter][4];
        loadInventoryData(inventory, infile, Icounter);
        infile.close();
        infile.open("sales.txt", std::fstream::in);
        infile>>Scounter;
        infile.ignore();
        ///sales data is reloaded after every loop. this allows for updates to the sales file
        std::string sales[Scounter][5];
        int salesnum=loadSalesData(sales, infile, Scounter);
        infile.close();
        char select=menu();

        switch(select)
        {
        case '5':
            return;

        case '1':
            newSale(salesnum, inventory, sales, Icounter, Scounter);
            ///after a sale, write the updated inventory to file
            writeInventoryData(inventory, Icounter );
            break;
        case '2':
            printSalesReport(sales, Scounter);
            break;
        case '3':
            printInventory(inventory, Icounter);
            break;
        case '4':
            updateInventory(inventory, Icounter);
            break;
        }


    }
}
