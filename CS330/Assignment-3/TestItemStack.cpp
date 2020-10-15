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
// ItemStack Unit Tests - Support Data
//-----------------------------------------------------------------------------

//-----------------------------------------------------------------------------
//  ItemStack - Unit Tests - Test Functions
//-----------------------------------------------------------------------------

bool testDefaultConstructor()
{
    ItemStack generic;

    const Item& theItem = generic.getItem();

    bodgeAssert(theItem.getID() == 0);
    bodgeAssert(theItem.getName() == "Air");

    bodgeAssert(generic.size() == 0);

    return true;
}

bool testSecondConstructor()
{
    Item aCoolItem(32, "Ice");
    ItemStack aStack(aCoolItem, 9001);

    const Item& theRetrievedItem = aStack.getItem();

    bodgeAssert(theRetrievedItem.getID() == 32);
    bodgeAssert(theRetrievedItem.getName() == "Ice");

    bodgeAssert(aStack.size() == 9001);

    return true;
}

bool testCopyConstructor()
{
    Item aCoolItem(32, "More Ice");
    ItemStack originalStack(aCoolItem, 9002);

    Item theRetrievedItem = originalStack.getItem();

    ItemStack aCopy(originalStack);

    bodgeAssert(theRetrievedItem.getID() == 32);
    bodgeAssert(theRetrievedItem.getName() == "More Ice");
    bodgeAssert(originalStack.size() == 9002);

    theRetrievedItem = aCopy.getItem();
    bodgeAssert(theRetrievedItem.getID() == 32);
    bodgeAssert(theRetrievedItem.getName() == "More Ice");
    bodgeAssert(aCopy.size() == 9002);

    return true;
}

bool testAssignment()
{
    Item aCoolItem(32, "Even More Ice");
    ItemStack originalStack(aCoolItem, 9002);

    Item theRetrievedItem = originalStack.getItem();

    ItemStack aCopy = originalStack;

    bodgeAssert(theRetrievedItem.getID() == 32);
    bodgeAssert(theRetrievedItem.getName() == "Even More Ice");
    bodgeAssert(originalStack.size() == 9002);

    theRetrievedItem = aCopy.getItem();
    bodgeAssert(theRetrievedItem.getID() == 32);
    bodgeAssert(theRetrievedItem.getName() == "Even More Ice");
    bodgeAssert(aCopy.size() == 9002);

    return true;
}

bool testAddItems()
{
    Item aWarmItem(120, "Hot Tea");
    ItemStack aStack(aWarmItem, 12);

    aStack.addItems(42);

    const Item& theRetrievedItem = aStack.getItem();
    bodgeAssert(theRetrievedItem.getID() == 120);
    bodgeAssert(theRetrievedItem.getName() == "Hot Tea");

    bodgeAssert(aStack.size() == 54);

    return true;
}

bool testAddItemsFrom()
{
    Item aHotItem(200, "Mix Tape");
    ItemStack aStack(aHotItem, 12);
    ItemStack aSecondStack(aHotItem, 24);

    aStack.addItemsFrom(aSecondStack);

    const Item& theRetrievedItem = aStack.getItem();
    bodgeAssert(theRetrievedItem.getID() == 200);
    bodgeAssert(theRetrievedItem.getName() == "Mix Tape");

    bodgeAssert(aStack.size() == 36);

    // aSecondStack does not need to be checked due to const correctness

    return true;
}

bool testLogicalEquivalence()
{
    Item aJoke(42, "Emacs & PHP code");
    Item anIdenticalJoke(42, "Emacs & PHP code");
    Item anImposterJoke(42, "Code::Blocks");

    ItemStack stack1(aJoke, 9000);
    ItemStack stack2(anIdenticalJoke, 337);
    ItemStack stack3(anImposterJoke, 12);

    // Only the Item IDs should be considered.
    bodgeAssert(stack1 == stack2);
    bodgeAssert(stack1 == stack3);
    bodgeAssert(stack2 == stack3);

    return true;
}

bool testLessThan()
{
    Item aJoke(42, "Emacs & PHP code");
    Item anIdenticalJoke(42, "Emacs & PHP code");
    Item funToys(1, "Vim & Python or Rust code");

    ItemStack stack1(aJoke, 9000);
    ItemStack stack2(anIdenticalJoke, 337);
    ItemStack stack3(funToys, 12);

    // Only the Item IDs should be considered.
    bodgeAssert(!(stack1 < stack2));
    bodgeAssert(!(stack2 < stack1));

    // Vim should always be #1, especially if Rust & Python are involved
    bodgeAssert(stack3 < stack1);
    bodgeAssert(stack3 < stack2);

    return true;
}

bool testDisplay()
{
    Item aWarmItem(120, "Hot Tea");
    ItemStack stack1(aWarmItem, 7);

    const std::string expected1 = "( 7) " + aWarmItem.getName();
    bodgeAssert(expected1 == toStr(stack1));

    //--------------------------------------------------------------------------
    Item healthyItem(99, "Celery");
    ItemStack stack2(healthyItem, 42);

    const std::string expected2 = "(42) " + healthyItem.getName();
    bodgeAssert(expected2 == toStr(stack2));

    return true;
}

bool testSwap()
{
    Item aWarmItem(120, "Hot Tea");
    ItemStack stack1(aWarmItem, 12);

    Item aCoolItem(32, "Ice");
    ItemStack stack2(aCoolItem, 9001);

    swap(stack1, stack2);

    bodgeAssert(stack1.getItem().getName() == "Ice");
    bodgeAssert(stack1.getItem().getID() == 32);
    bodgeAssert(stack1.size() == 9001);

    bodgeAssert(stack2.getItem().getName() == "Hot Tea");
    bodgeAssert(stack2.getItem().getID() == 120);
    bodgeAssert(stack2.size() == 12);

    return true;
}

//-----------------------------------------------------------------------------
int main(int argc, char** argv)
{
    UnitTestPair itemStackTests[] = {
        {testDefaultConstructor, "testDefaultConstructor"},
        {testSecondConstructor, "testSecondConstructor"},
        {testCopyConstructor, "testCopyConstructor"},
        {testAssignment, "testAssignment"},
        {testAddItems, "testAddItems"},
        {testAddItemsFrom, "testAddItemsFrom"},
        {testLogicalEquivalence, "testLogicalEquivalence"},
        {testLessThan, "testLessThan"},
        {testDisplay, "testDisplay"},
        {testSwap, "testSwap"}
    };

    std::cout << "ItemStack:" << "\n";
    for (const UnitTestPair& testPair : itemStackTests) {
        runTest(testPair.first, testPair.second);
    }

    return 0;
}

