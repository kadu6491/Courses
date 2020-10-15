#include <iostream>
#include <string>
#include <stdlib.h>
#include <sstream>
#include <iomanip>
#include <fstream>

using namespace std;

/// int Icounter=0;
/// int Scounter=0;

void writeInventoryData(std::string inventoryData[][4],int counter);
void subtractItemFromInventory(std::string name, int amount, std::string inventory[][4], int Icounter);
void writeNewSale(std::string newlines[][5], int totallines, std::string sales[][5], int Scounter);
std::string convert(double x);
std::string convert(int x);
double convert(std::string x);
void printCurrentSale(std::string newlines[][5], int lines);
std::string getItemCost(std::string name, std::string inventory[][4], int Icounter);
int getSaleAmount(std::string item, std::string avail);
std::string numAvailable(std::string name, std::string inventory[][4], int Icounter);
bool isAvailable(std::string name, std::string inventory[][4], int Icounter);
std::string getName(std::string inventory[][4], int Icounter);
void newSale(int salenum, std::string inventory[][4], std::string sales[][5], int Icounter, int Scounter);
bool itemExists(std::string name, std::string inventory[][4], int Icounter);
std::string getCost(std::string name);
void addNewItem(int i, std::string invoice[][3], std::string inventory[][4], int Icounter);
void addtoItem(std::string name,std::string amt, std::string inventory[][4], int Icounter);
void addInvoice(std::string inventory[][4], std::string invoice[][3], int sizeInvoice, int Icounter);
void getInvoice(std::string invoice[][3], int counter, std::fstream& infile);
void updateInventory(std::string inventory[][4], int Icounter);
void printInventory(std::string inventory[][4], int Icounter);
void printSalesReport(std::string sales[][5], int Scounter);
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




///writes inventory data to file
void writeInventoryData(std::string inventoryData[][4],int counter)
{
    std::fstream outfile;
    outfile.open("inventory.txt", std::fstream::out);
    outfile<<counter<<std::endl;
    ///double for loop to cycle through each array in an array of arrays
    for(int i=0; i<counter; i++)
    {
        for(int j=0; j<3; j++)
        {
            ///formats the first 3 items in the row with a ':' seperator
            outfile<<inventoryData[i][j]<<':';
        }
        ///outputs the last item with an endl
        outfile<<inventoryData[i][3]<<std::endl;
    }
    outfile.close();
}

///subtracts a sold item from the inventory
void subtractItemFromInventory(std::string name, int amount, std::string inventory[][4], int Icounter)
{

    for(int i=0; i<Icounter; i++)
    {
        if(inventory[i][0]==name)
        {
            std::stringstream buff;
            ///converts the string value stored in inventory to an integer
            int temp=atoi(inventory[i][2].c_str());
            ///subtracts the sold amount
            temp=temp-amount;
            ///pushes the new integer amount to a string stream
            buff<<temp;
            ///converts to string and stores it in the 2D array of type string
            inventory[i][2]=buff.str();
        }
    }

}


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


///converts a double to a string
std::string convert(double x){
    std::stringstream ss;
    ss<<std::fixed<<std::setprecision(2)<<x;
    return ss.str();
}

///converts an integer to a string
std::string convert(int x){
    std::stringstream ss;
    ss<<x;
    return ss.str();
}

///converts a string to a double
double convert(std::string x){
    ///FILL THIS FUNCTION: takes a string of a double value like "35.00" and converts it to a double 35.00 and returns it
    double i = atof(x.c_str());
    return 0.0;
}


void printCurrentSale(std::string newlines[][5], int lines){
    std::cout<<"-----------------------Sale Num "<<newlines[0][0]<<"------------------"<<std::endl<<std::endl;
    std::cout<<std::fixed<<std::setprecision(2);
    double total=0.0;
    for(int i=0; i<lines; i++){
         std::cout<<std::left<<std::setw(5)<<newlines[i][0];
        std::cout<<std::left<<std::setw(15)<<newlines[i][1];
        std::cout<<std::left<<std::setw(10)<<newlines[i][2];
        std::cout<<std::right<<std::setw(10)<<newlines[i][3];
        std::cout<<std::right<<std::setw(10)<<newlines[i][4];
        std::cout<<std::endl;
        total+=convert(newlines[i][4]);
    }
    std::cout<<std::left<<std::setw(40)<<std::setfill('-')<<"Total"<<std::right<<std::setw(10)<<total<<std::endl<<std::endl;
    std::cout<<std::setfill(' ');
}

///get the cost of the item
std::string getItemCost(std::string name, std::string inventory[][4], int Icounter){

    /// int Icounter = 0;

    for(int i=0; i<Icounter; i++){
        if(name==inventory[i][0]){
            return inventory[i][3];
        }
    }
    return "";
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



///returns the inventory count of an item
std::string numAvailable(std::string name, std::string inventory[][4], int Icounter)
{
    ///FILL THIS FUNCTION: this function pulls the current count for an item in inventory and returns it as a string value
    for ( int i = 0; i < Icounter; i++)
    {
        if (inventory[i][0] == name)
        {
            return inventory[i][2];
        }

    }

    return "";
}


///check availability of an item
bool isAvailable(std::string name, std::string inventory[][4], int Icounter)
{
    ///FILL THIS FUNCTION: this function looks through the inventory for an item by name, returns true if it exists in the inventory AND its amount is greater than 0
    //int Icounter = 0;

    for ( int i = 0; i < Icounter; i++)
    {
        if (inventory[i][0] == name)
        {
            if(atoi(inventory[i][2].c_str()) > 0)
            {
                return true;
            }
        }

    }

    return false;
}


///get the name of the item
std::string getName(std::string inventory[][4], int Icounter){
    // int Icounter = 0;
    std::string item="";
    ///loops until available item is entered
    while(true)
        {
            std::cout<<"Enter Name of Item: ";
            getline(std::cin,item);


            if(isAvailable(item,inventory, Icounter))
                return item;
            std::cout<<"\nItem is not available"<<std::endl;
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


///checks to see if an item is already in the inventory, returns true if it exists
bool itemExists(std::string name, std::string inventory[][4], int Icounter)
{
    /// int Icounter = 0;
    for(int i=0; i<Icounter; i++)
    {
        if(inventory[i][0]==name)
        {
            return true;
        }
    }
    return false;
}

///for a new item in inventory, prompts the user for the cost of the item
std::string getCost(std::string name)
{
    std::string cost="";
    std::cout<<"\nHow much to charge for "<<name<<"?"<<std::endl;
    std::cout<<"Enter cost(00.00): ";
    std::cin>>cost;
    std::cin.ignore();
    std::cin.clear();
    return cost;
}

///adds a new item to the inventory that was not carried before
void addNewItem(int i, std::string invoice[][3], std::string inventory[][4], int Icounter)
{
    /// int Icounter = 0;
    int tempsize=Icounter+1;
    ///creates a new inventory 2D array
    std::string temparray[tempsize][4];
    ///double for loop copies old inventory into new 2D array
    for(int j=0; j<Icounter; j++)
    {
        for(int k=0; k<4; k++)
        {
            temparray[j][k]=inventory[j][k];
        }
    }
    ///adds new items to the new 2D array
    for(int j=0; j<3; j++)
    {
        temparray[tempsize-1][j]=invoice[i][j];
    }
    temparray[tempsize-1][3]=getCost(invoice[i][0]);
    ///writes the new 2D array to the inventory file
    writeInventoryData(temparray, tempsize);
}



///for an existing item, adds to the inventory count for that item
void addtoItem(std::string name,std::string amt, std::string inventory[][4], int Icounter)
{
    // int Icounter = 0;

    int itemCount=atoi(amt.c_str());
    for(int j=0; j<Icounter; j++)
    {
        if(inventory[j][0]==name)
        {
            std::stringstream buffer;
            ///string value for amount is taken from the 2D inventory array and converted to an integer
            int temp=atoi(inventory[j][2].c_str());
            ///invoice amount is added to the inventory
            temp+=itemCount;
            ///the new amount is pushed to a string stream
            buffer<<temp;
            ///the string stream is converted to a string and replaces the amount value in the 2D array for inventory
            inventory[j][2]=buffer.str();
        }
    }
}


///adds the contents of a shipping invoice to the inventory
void addInvoice(std::string inventory[][4], std::string invoice[][3], int sizeInvoice, int Icounter)
{
    // int Icounter = 0;
    std::string temp[sizeInvoice][3];
    int newitemcount=0;
    ///cycles through the invoice 2D array
    for(int i=0; i<sizeInvoice; i++)
    {
        ///if item is already in the inventory, add to its count
        if(itemExists(invoice[i][0], inventory, Icounter))
        {
            addtoItem(invoice[i][0],invoice[i][2], inventory, Icounter);
        }
        else
        {
            ///new items are added to an array called temp, which will be updated to the inventory later
            for(int j=0; j<3; j++)
            {
                temp[newitemcount][j]=invoice[i][j];
            }
            newitemcount++;
        }
    }
    ///if there were no new items, go ahead and write the inventory data to file
    if(newitemcount==0)
    {
        writeInventoryData(inventory, Icounter);
    }
    else
    {
        ///for each new item in the temp 2D array, add it to the inventory
        for(int i=0; i<newitemcount; i++)
        {
            addNewItem(i, temp, inventory, Icounter);
        }
    }
}


///pulls invoice information from a file
void getInvoice(std::string invoice[][3], int counter, std::fstream& infile)
{
    for(int i=0; i< counter; i++)
    {
        getline(infile, invoice[i][0], ',');
        infile.ignore();///gets rid of leading white space
        getline(infile, invoice[i][1], ',');
        infile.ignore();
        getline(infile, invoice[i][2]);
    }
}


///manages the function calls for updating the inventory
void updateInventory(std::string inventory[][4], int Icounter)
{
    std::fstream infile;
    std::string filename="";
    int sizeInvoice=0;
    std::cout<<"\nEnter the file name of the invoice: ";
    std::cin>>filename;
    std::cin.ignore();
    infile.open(filename.c_str(), std::fstream::in);
    infile>>sizeInvoice;
    infile.ignore();
    std::string invoice[sizeInvoice][3];
    getInvoice(invoice, sizeInvoice, infile);
    addInvoice(inventory, invoice, sizeInvoice, Icounter);
}



///prints  a report of the inventory
void printInventory(std::string inventory[][4], int Icounter)
{
    std::cout<<"------------------Inventory----------------"<<std::endl;
    std::cout<<std::endl;
    for(int i=0; i<Icounter; i++)
    {
        std::cout<<std::left<<std::setw(15)<<inventory[i][0];
        std::cout<<std::left<<std::setw(15)<<inventory[i][1];
        std::cout<<std::left<<std::setw(10)<<inventory[i][2];
        std::cout<<std::right<<std::setfill('0')<<std::setw(5)<<inventory[i][3];
        std::cout<<std::setfill(' ')<<std::endl;
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
