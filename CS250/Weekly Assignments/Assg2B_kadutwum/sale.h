#ifndef SALE_H_INCLUDED
#define SALE_H_INCLUDED

#include <string>



void writeNewSale(std::string newlines[][5], int totallines, std::string sales[][5], int Scounter);
int getSaleAmount(std::string item, std::string avail);int getSaleAmount(std::string item, std::string avail);
void newSale(int salenum, std::string inventory[][4], std::string sales[][5], int Icounter, int Scounter);
void printSalesReport(std::string sales[][5], int Scounter);

#endif // SALE_H_INCLUDED
