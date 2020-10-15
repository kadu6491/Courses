#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <vector>
#include <algorithm>
#include <iterator>

#include "Inventory.h"
#include "ItemStack.h"
#include "Item.h"

using namespace std;

/**
 * Trim leading and trailing whitespace from a string.
 *
 * @param str string to prune
 *
 * @pre str is nonempty
 */
void trim(std::string& str);

/**
 * Read file containing the list
 * of all possible items
 */
vector<Item> parseItemList(istream& inf);

/**
 * Read inventory file and create all Inventory
 * instances.
 *
 * @param items collection of valid Item entries
 *
 * @pre items is non-empty
 */
vector<Inventory> parseInventoryFile(istream& inf, const vector<Item>& items);

/**
 * Generate a summary of all valid items
 */
void printItems(const vector<Item>& items);

/**
 * Generate a summary of Inventory utilization
 */
void printInventories(const vector<Inventory>& storage);

/**
 * Assignment 1: Item Storage
 *
 * @param argv[1] items filename
 * @param argv[2] inventories filename
 */
int main(int argc, char** argv)
{
    vector<Item> validItems;    // Listing of Valid Items
    vector<Inventory> storage;  // Collection of Inventories

    // Check Command Line Arguments
    if (argc != 3) {
        cerr << "Usage: " << argv[0] << " list_file inventory_file" << "\n";
        return 1;
    }

    // Open list_file
    ifstream infile(argv[1]);

    if (!infile) {
        cerr << "Error: " << argv[1] << " could not be opened" << "\n";
        return 2;
    }

    // Read listing of possible items
    validItems = parseItemList(infile);

    // Close and reset for the next file
    infile.close();
    infile.clear();

    std::sort(validItems.begin(), validItems.end());

    // Open inventory file
    infile.open(argv[2]);
    if (!infile) {
        cout << "Error: " << argv[2] << " could not be opened" << "\n";
        return 3;
    }

    storage = parseInventoryFile(infile, validItems);
    infile.close();

    printItems(validItems);
    printInventories(storage);

    return 0;
}

//------------------------------------------------------------------------------
void trim(std::string& str)
{
    if (str.empty()) {
        return;
    }

    int first_nonspace = str.find_first_not_of(" \t");
    int last_non_space = str.find_last_not_of(" \t");

    str = str.substr(first_nonspace, last_non_space + 1);
}

//------------------------------------------------------------------------------
std::istream& operator>>(std::istream& ins, Item& toRead)
{
    int    i;
    string n;

    ins >> i;

    getline(ins, n);
    trim(n);

    toRead.setID(i);
    toRead.setName(n);

    return ins;
}

//------------------------------------------------------------------------------
vector<Item> parseItemList(istream& inf)
{
    vector<Item> items;

    std::copy(istream_iterator<Item>(inf),
              istream_iterator<Item>(),
              back_inserter(items));

    return items;
}

//------------------------------------------------------------------------------
vector<Inventory> parseInventoryFile(istream& inf, const vector<Item>& items)
{
    vector<Inventory> storage;  // Collection of Inventory instances
    Inventory* inv = nullptr;   // Temporary Inventory pointer

    // First two values on a line
    char leading_char;
    int num_1;

    cout << "Processing Log:" << "\n";

    while (inf >> leading_char >> num_1) {
        if (leading_char == '#') {
            if (inv != nullptr) {
                storage.push_back(*inv);
                delete inv;
            }

            inv = new Inventory(num_1);
        }
        else {
            int key = num_1;

            // Read third value
            int num_2;
            inf >> num_2;

            vector<Item>::const_iterator it;
            it = std::find_if(items.begin(),
                              items.end(),
                              [key](const Item& item) -> bool
                              {
                                  return item.getID() == key;
                              });

            // Ignore any Item id not found in items
            if (it != items.end()) {
                ItemStack stack(*it, num_2);

                if (!(inv->addItems(stack))) {
                   cout << " Discarded " << stack << "\n";
                }
                else {
                    cout << " Stored " << stack << "\n";
                }
            }
        }
    }

    storage.push_back(*inv);
    delete inv;

    return storage;
}

//------------------------------------------------------------------------------
void printItems(const vector<Item>& items)
{
    cout << "\n" <<"Item List:"  << "\n";

    for (const Item& i : items) {
        cout << " " << i << "\n";
    }

    cout << "\n";
}

//------------------------------------------------------------------------------
void printInventories(const vector<Inventory>& storage)
{
    cout << "Storage Summary:" << "\n";

    for (const Inventory& chest : storage) {
        cout << chest << "\n";
    }
}
