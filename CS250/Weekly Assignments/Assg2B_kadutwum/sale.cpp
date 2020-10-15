#include "sale.h"
///#include "inbound.h"

#include <string>
#include <fstream>
#include <iostream>
#include <iomanip>

///Overwrite sales file to include new sale items
void writeNewSale(std::string newlines[][5], int totallines, std::string sales[][5], int Scounter){
    std::fstream outfile;
    outfile.open("sales.txt");
    ///rewrites the number of lines in the file to include the new sale
    outfile<<Scounter+totallines<<std::endl;
    ///double for loop outputs previous sales
    for(int i=0; i<Scounter; i++)
    {
        for(int j=0; j<4; j++)
        {
            outfile<< sales[i][j] <<':';
        }
        outfile<<sales[i][4]<<std::endl;
    }
    ///adds new sale rows
    for(int i=0; i<totallines; i++)
    {
        for(int j=0; j<4; j++)
        {
            outfile<<newlines[i][j]<<':';
        }
        outfile<<newlines[i][4]<<std::endl;
    }
    outfile.close();

}

///get the number of items to add to a sale
int getSaleAmount(std::string item, std::string avail){
    int amount=0;
    ///will keep looping until valid input
    while(true)
        {
            std::cout<<avail<<" "<<item<<"s are available"<<std::endl;
            std::cout<<"Enter amount: ";
            std::cin>>amount;
            std::cin.ignore();
            std::cin.clear();
            if(amount<=atoi(avail.c_str()))
                return amount;
            std::cout<<"\nAmount is greater than availability"<<std::endl;
        }
}

///managaes a new sale and all of the associated function calls
void newSale(int salenum, std::string inventory[][4], std::string sales[][5], int Icounter, int Scounter)
{
    std::cout<<"--------------NEW SALE--------------"<<std::endl<<std::endl;
    salenum++;
    std::cout<<"Sale Num: "<<salenum<<std::endl<<std::endl;
    std::string item="";
    std::string avail="";
    int amount=0;
    std::string itemCost="";
    double Cost=0.0;
    double totalCost=0.0;
    int lines=0;
    std::string newlines[10][5];
    while(lines<10)
    {
        ///stores the new sale as a series of lines. No more than 10 lines per sale


        char ans=' ';

        item=getName(inventory, Icounter);
        avail=numAvailable(item, inventory, Icounter);
        amount=getSaleAmount(item, avail);
        itemCost=getItemCost(item, inventory, Icounter);

        Cost=convert(itemCost);///convert string itemcost to double
        totalCost=amount * Cost;

        newlines[lines][0]=convert(salenum);///convert int salenum to string
        newlines[lines][1]=item;
        newlines[lines][2]=convert(amount);///convert int amount to string
        newlines[lines][3]=itemCost;
        newlines[lines][4]=convert(totalCost);///convert double totalCost to string

        std::cout<<"\nItem Total: "<<std::fixed<<std::setprecision(2)<<totalCost<<std::endl;

        ///write new sale to file
        writeNewSale(newlines, lines+1, sales, Scounter);
        ///subtract line from inventory
        subtractItemFromInventory(item, amount, inventory, Icounter);

        ///More lines with this sale number?
        while(true){
            std::cout<<"Add another item(y/n)? ";
            std::cin>>ans;
            std::cin.ignore(33,'\n');
            ///break if answer is correct
            if((ans=='y'|| ans=='Y')||(ans=='n'||ans=='N'))
                break;
            std::cout<<"Not a valid answer"<<std::endl;
        }
        ///return if answer is no
        if(ans=='n'||ans=='N'){
            printCurrentSale(newlines, lines+1);
            return;
        }
        ///return if sale goes over 10
        if(lines==10){
            std::cout<<"Sales are limited to 10 lines"<<std::endl<<std::endl;
            printCurrentSale(newlines, lines+1);
            return;
        }
        lines++;
    }

}

void printSalesReport(std::string sales[][5], int Scounter){
    std::cout<<"-----------------------Sales Report------------------"<<std::endl<<std::endl;
    /// int Scounter = 0;
    for(int i=0; i<Scounter; i++){
         std::cout<<std::left<<std::setw(5)<<sales[i][0];
        std::cout<<std::left<<std::setw(15)<<sales[i][1];
        std::cout<<std::left<<std::setw(10)<<sales[i][2];
        std::cout<<std::right<<std::fixed<<std::setprecision(2)<<std::setw(10)<<sales[i][3];
        std::cout<<std::right<<std::fixed<<std::setprecision(2)<<std::setw(10)<<sales[i][4];
        std::cout<<std::endl;

    }
}

