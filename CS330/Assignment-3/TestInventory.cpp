#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <vector>
#include <sstream>
#include <algorithm>
#include <functional>

#include "Item.h"
#include "ItemStack.h"
#include "Inventory.h"
#include "utilities.h"

#include "bodgeUnitTest.h"

//-----------------------------------------------------------------------------
// Inventory Unit Tests - Support Data
//-----------------------------------------------------------------------------
const Inventory EMPTY_INVENTORY;

const std::vector<Item> TEST_ITEMS = {
    {0, "Air"},
    {1, "Dirt"},
    {2, "Potato"},
    {3, "Makefile"},
    {4, "Procrastination"}
};

//-----------------------------------------------------------------------------
//  Inventory - Unit Tests - Test Functions
//-----------------------------------------------------------------------------
bool testDefaultConstructor()
{
    bodgeAssert(EMPTY_INVENTORY.utilizedSlots() == 0);
    bodgeAssert(EMPTY_INVENTORY.emptySlots() == 10);
    bodgeAssert(EMPTY_INVENTORY.totalSlots() == 10);
    bodgeAssert(!EMPTY_INVENTORY.isFull());

    // I should really check display() and/or operator<< here. However, I will
    // do that in a seperate `testDisplay` function

    return true;
}

//-----------------------------------------------------------------------------
bool testConstructorSizeN()
{
    Inventory invWith8Slots(8);

    bodgeAssert(invWith8Slots.utilizedSlots() == 0);
    bodgeAssert(invWith8Slots.emptySlots() == 8);
    bodgeAssert(invWith8Slots.totalSlots() == 8);
    bodgeAssert(!invWith8Slots.isFull());

    // I should really check display() and/or operator<< here. However, I will
    // do that in a seperate `testDisplay` function

    return true;
}

//-----------------------------------------------------------------------------
/**
 * Add ItemStacks to an Inventory without filling the Inventory or attempting
 * to add duplicate Items
 */
bool testAddItemStackNoCheck()
{
    std::vector<ItemStack> stacksToAdd = {
        {TEST_ITEMS[0], 1},
        {TEST_ITEMS[1], 4},
        {TEST_ITEMS[2], 8}
    };

    Inventory aBag(4);

    aBag.addItems(stacksToAdd[0]);
    aBag.addItems(stacksToAdd[1]);
    aBag.addItems(stacksToAdd[2]);

    bodgeAssert(!aBag.isFull());
    bodgeAssert(aBag.utilizedSlots() == 3);
    bodgeAssert(aBag.emptySlots() == 1);
    bodgeAssert(aBag.totalSlots() == 4);

    // Retrieve each of the items and check that they were added
    Inventory::const_iterator it = aBag.begin();

    bodgeAssert(*(it++) == stacksToAdd[0]);
    bodgeAssert(*(it++) == stacksToAdd[1]);
    bodgeAssert(*(it++) == stacksToAdd[2]);

    // Check that there are no more ItemStacks to retrieve
    bodgeAssert(it == aBag.end());

    return true;
}

//-----------------------------------------------------------------------------
/**
 * Add ItemStacks to an Inventory without filling the Inventory or attempting
 * to add duplicate Items
 */
bool testAddItemWithDuplicateItems()
{
    std::vector<ItemStack> stacksToAdd = {
        {TEST_ITEMS[0], 1},
        {TEST_ITEMS[1], 4},
        {TEST_ITEMS[1], 5}
    };

    Inventory aBag(4);

    aBag.addItems(stacksToAdd[0]);
    aBag.addItems(stacksToAdd[1]);
    aBag.addItems(stacksToAdd[2]);

    bodgeAssert(!aBag.isFull());
    bodgeAssert(aBag.utilizedSlots() == 2);
    bodgeAssert(aBag.emptySlots() == 2);
    bodgeAssert(aBag.totalSlots() == 4);

    // Retrieve each of the items and check that they were added
    Inventory::const_iterator it = aBag.begin();

    bodgeAssert(*(it++) == stacksToAdd[0]);

    // Expect the merged stack to be returned.
    ItemStack mergedStack(TEST_ITEMS[1], 9);
    const ItemStack& retrieved = *it;

    bodgeAssert(retrieved == mergedStack);
    bodgeAssert(retrieved.size() == 9);
    it++;

    // Check that there are no more ItemStacks to retrieve
    bodgeAssert(it == aBag.end());

    return true;
}

//-----------------------------------------------------------------------------
/**
 * Add ItemStacks to an Inventory and fill it.
 * Then try to add one more ItemStack.
 */
bool testAddItemAfterFull()
{
    std::vector<ItemStack> stacksToAdd = {
        {TEST_ITEMS[0], 1},
        {TEST_ITEMS[1], 4},
        {TEST_ITEMS[4], 8}
    };

    Inventory aBag(2);

    aBag.addItems(stacksToAdd[0]);
    aBag.addItems(stacksToAdd[1]);

    bodgeAssert(aBag.isFull());
    bodgeAssert(aBag.utilizedSlots() == 2);
    bodgeAssert(aBag.emptySlots() == 0);
    bodgeAssert(aBag.totalSlots() == 2);

    // This add should fail
    // Procrastination is bad
    bodgeAssert(!(aBag.addItems(stacksToAdd[2])));

    // Retrieve each of the items and check that they were added
    Inventory::const_iterator it = aBag.begin();

    bodgeAssert(*(it++) == stacksToAdd[0]);
    bodgeAssert(*(it++) == stacksToAdd[1]);

    // Check that there are no more ItemStacks to retrieve
    bodgeAssert(it == aBag.end());

    return true;
}

//-----------------------------------------------------------------------------
bool testDisplay()
{
    const std::vector<ItemStack> stacksToAdd = {
        {TEST_ITEMS[0], 1},
        {TEST_ITEMS[1], 4}
    };

    // Set up the expected strings for each ItemStack
    std::vector<std::string> stacksAsStrings(stacksToAdd.size());

    for (unsigned int i = 0; i < stacksToAdd.size(); ++i) {
        stacksAsStrings[i] = toStr(stacksToAdd[i]);
    }

    // Set up the test Inventory
    Inventory aBag(2);
    aBag.addItems(stacksToAdd[0]);
    aBag.addItems(stacksToAdd[1]);

    // Check for the expected ItemStack lines within the larger output
    const std::string bagString = toStr(aBag);

    bodgeAssert(bagString.find(stacksAsStrings[0]) != std::string::npos);
    bodgeAssert(bagString.find(stacksAsStrings[1]) != std::string::npos);

    // Check for the Summary line
    const std::string expected = "-Used " + std::to_string(aBag.utilizedSlots())
                               + " of " + std::to_string(aBag.totalSlots())
                               + " slots";

    bodgeAssert(toStr(aBag).find(expected) != std::string::npos);

    //--------------------------------------------------------------------------
    // Check the entire output string. This should really be a seperate test
    //--------------------------------------------------------------------------
    const std::string expectedOverall = " " + expected + "\n"
                                      + "  " + stacksAsStrings[0] + "\n"
                                      + "  " + stacksAsStrings[1] + "\n";

    bodgeAssert(bagString == expectedOverall);

    return true;
}

//-----------------------------------------------------------------------------
bool testCopyConstructorForEmpty()
{
    Inventory aCopy(EMPTY_INVENTORY);

    bodgeAssert(aCopy.utilizedSlots() == 0);
    bodgeAssert(aCopy.emptySlots() == 10);
    bodgeAssert(aCopy.totalSlots() == 10);
    bodgeAssert(!aCopy.isFull());

    // Check that both have the same data and are distinct copies
    bodgeAssert(aCopy == EMPTY_INVENTORY);
    bodgeAssert(&aCopy != &EMPTY_INVENTORY);

    // bodgeAssert(aCopy.begin() != EMPTY_INVENTORY.begin());

    return true;
}

//-----------------------------------------------------------------------------
bool testCopyConstructor()
{
    const std::vector<ItemStack> stacksToAdd = {
        {TEST_ITEMS[0], 1},
        {TEST_ITEMS[1], 4},
        {TEST_ITEMS[4], 8}
    };

    Inventory source(4);

    for (const ItemStack& next : stacksToAdd) {
        source.addItems(next);
    }

    Inventory aCopy(source);

    bodgeAssert(aCopy.utilizedSlots() == 3);
    bodgeAssert(aCopy.emptySlots() == 1);
    bodgeAssert(aCopy.totalSlots() == 4);
    bodgeAssert(!aCopy.isFull());

    // Check that both have the same data and are distinct copies
    bodgeAssert(aCopy == source);
    bodgeAssert(&aCopy != &source);

    using ConstIterator = Inventory::const_iterator;
    ConstIterator it = source.begin();

    for (const ItemStack& nextToCheck : aCopy) {
        bodgeAssert(nextToCheck == *it);          // Compare ItemStacks
        bodgeAssert(&nextToCheck != &(*(it++)));  // Compare Memory Addresses
    }

    return true;
}

//-----------------------------------------------------------------------------
bool testAssignmentOperator()
{
    const std::vector<ItemStack> stacksToAdd = {
        {TEST_ITEMS[0], 1},
        {TEST_ITEMS[1], 4},
        {TEST_ITEMS[4], 8}
    };

    Inventory source(4);

    for (const ItemStack& next : stacksToAdd) {
        source.addItems(next);
    }

    Inventory aCopy = source;

    bodgeAssert(aCopy.utilizedSlots() == 3);
    bodgeAssert(aCopy.emptySlots() == 1);
    bodgeAssert(aCopy.totalSlots() == 4);
    bodgeAssert(!aCopy.isFull());

    // Check that both have the same data and are distinct copies
    bodgeAssert(aCopy == source);
    bodgeAssert(&aCopy != &source);

    using ConstIterator = Inventory::const_iterator;
    ConstIterator it = source.begin();

    for (const ItemStack& nextToCheck : aCopy) {
        bodgeAssert(nextToCheck == *it);          // Compare ItemStacks
        bodgeAssert(&nextToCheck != &(*(it++)));  // Compare Memory Addresses
    }

    return true;
}

//-----------------------------------------------------------------------------
int main(int argc, char** argv)
{
    UnitTestPair inventoryTests[] = {
        {testDefaultConstructor, "testDefaultConstructor"},
        {testConstructorSizeN, "testConstructorSizeN"},
        {testAddItemStackNoCheck, "testAddItemStackNoCheck"},
        {testAddItemWithDuplicateItems, "testAddItemWithDuplicateItems"},
        {testAddItemAfterFull, "testAddItemAfterFull"},
        {testCopyConstructorForEmpty, "testCopyConstructorForEmpty"},
        {testCopyConstructor, "testCopyConstructor"},
        {testAssignmentOperator, "testAssignmentOperator"},
        {testDisplay, "testDisplay"},
    };

    std::cout << "Inventory:" << "\n";
    for (const UnitTestPair& testPair : inventoryTests) {
        runTest(testPair.first, testPair.second);
    }

    return 0;
}

