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
#include "ItemFactory.h"

#include "bodgeUnitTest.h"
#include "utilities.h"


//-----------------------------------------------------------------------------
// Unit Tests - Support Data
//-----------------------------------------------------------------------------


//-----------------------------------------------------------------------------
// Unit Tests - Test Functions
//-----------------------------------------------------------------------------
bool testDefaultArmourConstructor()
{
    Armour genericArmour;
    Item&  genericRef = genericArmour;

    bodgeAssert(!genericArmour.isStackable());
    bodgeAssert(!genericRef.isStackable());

    // I should really complete this unit test with calls to each of the
    // accessors. However, I will forgo the remaining checks for this test

    // I should really check display() and/or operator<< here. However, I will
    // do that in a separate `testDisplay` function

    return true;
}

//-----------------------------------------------------------------------------
bool testArmourCopyConstructor()
{
    Armour fancyArmour;

    fancyArmour.setName("Fancy");
    fancyArmour.setDurability(9001);
    fancyArmour.setDefense(62);
    fancyArmour.setMaterial("Vibranium");
    fancyArmour.setModifier("ProcrastinationReduction");
    fancyArmour.setModifierLevel(999999);
    fancyArmour.setElement("H20");

    Armour copy(fancyArmour);

    // Checks
    bodgeAssert(copy.getName() == "Fancy");
    bodgeAssert(!copy.isStackable());
    bodgeAssert(copy.getDurability() == 9001);
    bodgeAssert(copy.getDefense() == 62);
    bodgeAssert(copy.getMaterial() == "Vibranium");
    bodgeAssert(copy.getModifier() == "ProcrastinationReduction");
    bodgeAssert(copy.getModifierLevel() == 999999);
    bodgeAssert(copy.getElement() == "H20");

    // I should really check display() and/or operator<< here. However, I will
    // do that in a separate `testDisplay` function

    return true;
}

//-----------------------------------------------------------------------------
bool testArmourClone()
{
    // Setup
    Armour fancyArmour;

    fancyArmour.setName("Fancy");
    fancyArmour.setDurability(9001);
    fancyArmour.setDefense(62);
    fancyArmour.setMaterial("Vibranium");
    fancyArmour.setModifier("ProcrastinationReduction");
    fancyArmour.setModifierLevel(999999);
    fancyArmour.setElement("H20");

    Armour& copy = *((Armour*) fancyArmour.clone());

    // Checks
    bodgeAssert(copy.getName() == "Fancy");
    bodgeAssert(!copy.isStackable());
    bodgeAssert(copy.getDurability() == 9001);
    bodgeAssert(copy.getDefense() == 62);
    bodgeAssert(copy.getMaterial() == "Vibranium");
    bodgeAssert(copy.getModifier() == "ProcrastinationReduction");
    bodgeAssert(copy.getModifierLevel() == 999999);
    bodgeAssert(copy.getElement() == "H20");

    // I should really check display() and/or operator<< here. However, I will
    // do that in a separate `testDisplay` function

    return true;
}

//-----------------------------------------------------------------------------
bool testArmourDisplay()
{
    Armour fancyArmour;

    fancyArmour.setName("Fancy");
    fancyArmour.setDurability(9001);
    fancyArmour.setDefense(62);
    fancyArmour.setMaterial("Vibranium");
    fancyArmour.setModifier("ProcrastinationReduction");
    fancyArmour.setModifierLevel(999999);
    fancyArmour.setElement("H20");

    //-------------- Raw Literal String --------------
    const std::string expected = R"(  Nme: Fancy
  Dur: 9001
  Def: 62
  Mtl: Vibranium
  Mdr: ProcrastinationReduction (Lvl 999999)
  Emt: H20
)";
    //------------ End Raw Literal String ------------
    const std::string actual = toStr(fancyArmour);
    bodgeAssert(actual == expected);

    return true;
}

bool testArmourRead()
{
    Armour fancyArmour;

    const std::string inputStr = "Fancy Vibranium 9001 62 ProcrastinationReduction 999999 H20";
    std::istringstream ins(inputStr);

    fancyArmour.read(ins);

    // Checks
    bodgeAssert(fancyArmour.getName() == "Fancy");
    bodgeAssert(!fancyArmour.isStackable());
    bodgeAssert(fancyArmour.getDurability() == 9001);
    bodgeAssert(fancyArmour.getDefense() == 62);
    bodgeAssert(fancyArmour.getMaterial() == "Vibranium");
    bodgeAssert(fancyArmour.getModifier() == "ProcrastinationReduction");
    bodgeAssert(fancyArmour.getModifierLevel() == 999999);
    bodgeAssert(fancyArmour.getElement() == "H20");

    return true;
}

//------------------------------------------------------------------------------
bool testDefaultConsumableConstructor()
{
    Consumable imagination;

    Item& genericRef = imagination;

    bodgeAssert(imagination.isStackable());
    bodgeAssert(genericRef.isStackable());

    // I should really complete this unit test with calls to each of the
    // accessors. However, I will forgo the remaining checks for this test

    // I should really check display() and/or operator<< here. However, I will
    // do that in a separate `testDisplay` function

    return true;
}

//------------------------------------------------------------------------------
bool testConsumableCopyConstructor()
{
    Consumable tea;
    tea.setName("Green Tea");
    tea.setEffect("Wake Up");
    tea.setNumberOfUses(10);

    Consumable moreTea(tea);

    bodgeAssert(moreTea.isStackable());
    bodgeAssert(moreTea.getName() == "Green Tea");
    bodgeAssert(moreTea.getEffect() == "Wake Up");
    bodgeAssert(moreTea.getNumberOfUses() == 10);

    // I should really complete this unit test with calls to each of the
    // accessors. However, I will forgo the remaining checks for this test

    // I should really check display() and/or operator<< here. However, I will
    // do that in a separate `testDisplay` function

    return true;
}

//------------------------------------------------------------------------------
bool testConsumableClone()
{
    Consumable tea;
    tea.setName("Green Tea");
    tea.setEffect("Wake Up");
    tea.setNumberOfUses(10);

    Consumable& moreTea = *((Consumable*) tea.clone());

    bodgeAssert(moreTea.isStackable());
    bodgeAssert(moreTea.getName() == "Green Tea");
    bodgeAssert(moreTea.getEffect() == "Wake Up");
    bodgeAssert(moreTea.getNumberOfUses() == 10);

    // I should really complete this unit test with calls to each of the
    // accessors. However, I will forgo the remaining checks for this test

    // I should really check display() and/or operator<< here. However, I will
    // do that in a separate `testDisplay` function

    return true;
}

//------------------------------------------------------------------------------
bool testConsumableDisplay()
{
    Consumable tea;
    tea.setName("Green Tea");
    tea.setEffect("Wake Up");
    tea.setNumberOfUses(10);

    //-------------- Raw Literal String --------------
    const std::string expected = R"(  Nme: Green Tea
  Eft: Wake Up
  Use: 10
)";
    //------------ End Raw Literal String ------------

    const std::string actual = toStr(tea);
    bodgeAssert(actual == expected);

    return true;
}

//------------------------------------------------------------------------------
bool testConsumableRead()
{
    Consumable tea;

    const std::string inputStr = "Green-Tea Wake-Up 5";
    std::istringstream ins(inputStr);

    tea.read(ins);

    bodgeAssert(tea.isStackable());
    bodgeAssert(tea.getName() == "Green-Tea");
    bodgeAssert(tea.getEffect() == "Wake-Up");
    bodgeAssert(tea.getNumberOfUses() == 5);

    return true;
}

//------------------------------------------------------------------------------
int main(int argc, char** argv)
{
    UnitTestPair armourTests[] = {
        {testDefaultArmourConstructor, "testDefaultArmourConstructor"},
        {testArmourCopyConstructor, "testArmourCopyConstructor"},
        {testArmourClone, "testArmourClone"},
        {testArmourDisplay, "testArmourDisplay"},
        {testArmourRead, "testArmourRead"},
    };

    UnitTestPair consumableTests[] = {
        {testDefaultConsumableConstructor, "testDefaultConsumableConstructor"},
        {testConsumableCopyConstructor, "testConsumableCopyConstructor"},
        {testConsumableClone, "testConsumableClone"},
        {testConsumableDisplay, "testConsumableDisplay"},
        {testConsumableRead, "testConsumableRead"}
    };

    std::cout << "Armour:" << "\n";
    for (const UnitTestPair& testPair : armourTests) {
        runTest(testPair.first, testPair.second);
    }

    std::cout << "Consumable:" << "\n";
    for (const UnitTestPair& testPair : consumableTests) {
        runTest(testPair.first, testPair.second);
    }

    return 0;
}

