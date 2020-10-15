#include "Armour.h"

//------------------------------------------------------------------------------
Armour::Armour()
    :Item("Armour", false), // There should be something simliar in Consumable.cpp
     material(),
     modifier()
{
    this->durability    = 0;
    this->defense       = 0;
    this->modifierLevel = 1;
}

//------------------------------------------------------------------------------
void Armour::display(std::ostream& outs) const
{
    // Implement this function
    outs << "  Nme: " << Item::name << "\n"
         << "  Dur: " << durability << "\n"
         << "  Def: " << defense    << "\n"
         << "  Mtl: " << material   << "\n"
         << "  Mdr: " << modifier   << " (Lvl " << modifierLevel << ")" << "\n"
         << "  Emt: " << element    << "\n";
}

//------------------------------------------------------------------------------
void Armour::read(std::istream& ins)
{
    // Implement this function
    ins >> Item::name
        >> material
        >> durability
        >> defense
        >> modifier
        >> modifierLevel
        >> element;
}

//------------------------------------------------------------------------------
Item* Armour::clone() const
{
    // Implement this function
    return new Armour(*this); // remove this line
}

