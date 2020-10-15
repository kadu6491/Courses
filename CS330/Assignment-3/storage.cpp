#include <algorithm>
#include <cstdlib>
#include <fstream>
#include <iostream>
#include <iterator>
#include <memory>
#include <string>
#include <vector>

#include "Inventory.h"
#include "Item.h"
#include "ItemStack.h"
#include "utilities.h"

using namespace std;

/**
 * Read file containing the list of all possible items.
 *
 * *In the next assignment this will be a generalized template function.
 *
 * @param inf input buffer
 *
 * @return collection of valid items, sorted by numeric id
 */
vector<Item> parseItemList(istream& inf);

/**
 * Read inventory file and create all Inventory instances.
 *
 * @param items collection of valid Item entries
 *
 * @pre items is non-empty
 */
vector<unique_ptr<Inventory>> parseInventoryFile(istream& inf,
                                                 const vector<Item>& items);

/**
 * Generate a summary of all valid items
 */
void printItems(const vector<Item>& items);

/**
 * Generate a summary of Inventory utilization
 */
void printInventories(const vector<unique_ptr<Inventory>>& storage);

/**
 * Assignment 1: Item Storage
 *
 * @param argv[1] items filename
 * @param argv[2] inventories filename
 */
int main(int argc, char** argv)
{
    // Check Command Line Arguments
    if (argc != 3) {
        cerr << "Usage: " << argv[0] << " list_file inventory_file" << "\n";
        return 1;
    }

    ifstream infile(argv[1]);

    if (!infile) {
        cerr << "Error: " << argv[1] << " could not be opened" << "\n";
        return 2;
    }

    // Read listing of possible (i.e., valid) items
    vector<Item> validItems = parseItemList(infile);

    // Close and reset for the next file
    infile.close();
    infile.clear();

    infile.open(argv[2]);
    if (!infile) {
        cout << "Error: " << argv[2] << " could not be opened" << "\n";
        return 3;
    }

    vector<unique_ptr<Inventory>> storage = parseInventoryFile(infile, validItems);

    infile.close();

    printItems(validItems);
    printInventories(storage);

    return 0;
}

//------------------------------------------------------------------------------
vector<Item> parseItemList(istream& inf)
{
    vector<Item> items;

    std::copy(istream_iterator<Item>(inf),
              istream_iterator<Item>(),
              back_inserter(items));

    std::sort(items.begin(), items.end());

    return items;
}

//------------------------------------------------------------------------------
vector<unique_ptr<Inventory>> parseInventoryFile(istream& inf,
                                                 const vector<Item>& items)
{
    vector<unique_ptr<Inventory>> storage;
    Inventory* inv = nullptr;

    // First two values on a line
    char leading_char;
    int num_1;

    cout << "Processing Log:" << "\n";

    while (inf >> leading_char >> num_1) {
        if (leading_char == '#') {
            if (inv != nullptr) {
                storage.emplace_back(inv);
            }
            inv = new Inventory(num_1);
        }
        else {
            int quantity;
            inf >> quantity;

            // Search for (key) id in list of known items
            const int& key = num_1;
            vector<Item>::const_iterator it;
            it = std::find_if(items.begin(),
                              items.end(),
                              [key](const Item& item) -> bool
                              {
                                  return item.getID() == key;
                              });

            // Ignore any Item id not found in items
            if (it != items.end()) {
                ItemStack stack(*it, quantity);

                const char* result = (inv->addItems(stack) ? "Stored" : "Discarded");

                cout << " " << result << " " << stack << "\n";
            }
        }
    }

    storage.emplace_back(inv);

    return storage;
}

//------------------------------------------------------------------------------
void printItems(const vector<Item>& items)
{
    cout << "\n" << "Item List:" << "\n";

    for (const Item& i : items) {
        cout << " " << i << "\n";
    }

    cout << "\n";
}

//------------------------------------------------------------------------------
void printInventories(const vector<unique_ptr<Inventory>>& storage)
{
    cout << "Storage Summary:" << "\n";

    for (const unique_ptr<Inventory>& chest : storage) {
        cout << *chest << "\n";
    }
}
