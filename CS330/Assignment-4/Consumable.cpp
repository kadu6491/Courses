#include "Consumable.h"

//------------------------------------------------------------------------------
Consumable::Consumable()
    :Item(std::string(), true)
{
   this->uses = 0;
}

//------------------------------------------------------------------------------
void Consumable::display(std::ostream& outs) const
{
      // Add the definition for this function
    outs << "  Nme: " << Item::name << "\n"
         << "  Eft: " << effect     << "\n"
         << "  Use: " << uses       << "\n";
}
//------------------------------------------------------------------------------
void Consumable::read(std::istream& ins)
{
    // Add the definition for this function
    ins >> Item::name
        >> effect
        >> uses;
}
//------------------------------------------------------------------------------
Item* Consumable::clone() const
{
     // Add the definition for this function
     return new Consumable(*this);
}
