#ifndef __CIPHER_H_INCLUDED__
#define __CIPHER_H_INCLUDED__

#include <string>

/// std::string cipher(std::string Input);

void writeNewSale(std::string newlines[][5], int totallines, std::string sales[][5], int Scounter);
int getSaleAmount(std::string item, std::string avail);int getSaleAmount(std::string item, std::string avail);
void newSale(int salenum, std::string inventory[][4], std::string sales[][5], int Icounter, int Scounter);
void printSalesReport(std::string sales[][5], int Scounter);



#endif // __CIPHER_H__






