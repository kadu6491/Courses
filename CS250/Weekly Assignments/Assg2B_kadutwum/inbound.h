#ifndef INBOUND_H_INCLUDED
#define INBOUND_H_INCLUDED

#include <string>

void writeInventoryData(std::string inventoryData[][4],int counter);
void subtractItemFromInventory(std::string name, int amount, std::string inventory[][4], int Icounter);
///void writeNewSale(std::string newlines[][5], int totallines, std::string sales[][5], int Scounter);
std::string convert(double x);
std::string convert(int x);
double convert(std::string x);
void printCurrentSale(std::string newlines[][5], int lines);
std::string getItemCost(std::string name, std::string inventory[][4], int Icounter);
///int getSaleAmount(std::string item, std::string avail);
std::string numAvailable(std::string name, std::string inventory[][4], int Icounter);
bool isAvailable(std::string name, std::string inventory[][4], int Icounter);
std::string getName(std::string inventory[][4], int Icounter);
///void newSale(int salenum, std::string inventory[][4], std::string sales[][5], int Icounter, int Scounter);
bool itemExists(std::string name, std::string inventory[][4], int Icounter);
std::string getCost(std::string name);
void addNewItem(int i, std::string invoice[][3], std::string inventory[][4], int Icounter);
void addtoItem(std::string name,std::string amt, std::string inventory[][4], int Icounter);
void addInvoice(std::string inventory[][4], std::string invoice[][3], int sizeInvoice, int Icounter);
void getInvoice(std::string invoice[][3], int counter, std::fstream& infile);
void updateInventory(std::string inventory[][4], int Icounter);
void printInventory(std::string inventory[][4], int Icounter);

#endif // INBOUND_H_INCLUDED
