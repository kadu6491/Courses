#include <iomanip>

#include "Item.h"
#include "utilities.h"

//------------------------------------------------------------------------------
Item::Item()
    :Item(0, "Air")
{
}

//------------------------------------------------------------------------------
Item::Item(int i, std::string n)
    :id(i),
     name(n)
{
}

//------------------------------------------------------------------------------
bool Item::operator==(const Item &rhs) const
{
    return this->id == rhs.id;
}

//------------------------------------------------------------------------------
bool Item::operator<(const Item &rhs) const
{
    return this->id < rhs.id;
}

//------------------------------------------------------------------------------
void Item::display(std::ostream &outs) const
{
    outs << std::right << std::setw(3) << id << " " << name;
}

//------------------------------------------------------------------------------
void Item::read(std::istream& ins)
{
    // Use temp vars in case something goes wrong...
    int i = 0;
    std::string n("Air");

    ins >> i;
    getline(ins, n);
    trim(n);

    this->setID(i);
    this->setName(n);
}

