#include <utility>
#include <algorithm>

#include "Inventory.h"

// Allow the compiler to define the remaining
// comparison operators
using namespace std::rel_ops;

//------------------------------------------------------------------------------
Inventory::Inventory()
    :Inventory(10)
{
}

//------------------------------------------------------------------------------
Inventory::Inventory(int n)
    :slots(n)
{
}

//------------------------------------------------------------------------------
Inventory::Inventory(const Inventory& src)
{
    this->slots = src.slots;

    this->allItemStacks.reserve(this->slots);
    std::copy(src.begin(), src.end(), std::back_inserter(this->allItemStacks));
}

//------------------------------------------------------------------------------
int Inventory::utilizedSlots() const
{
    return allItemStacks.size();
}

//------------------------------------------------------------------------------
int Inventory::emptySlots() const
{
    return totalSlots() - utilizedSlots();
}

//------------------------------------------------------------------------------
int Inventory::totalSlots() const
{
    return slots;
}

//------------------------------------------------------------------------------
bool Inventory::isFull() const
{
    return emptySlots() == 0;
}


bool Inventory::addItems(ItemStack itemStack)
{
    const int& targetId = itemStack.getItem().getID();
    auto id_equal_function = [targetId](const ItemStack& aStack)
                             {
                                 return aStack.getItem().getID() == targetId;
                             };

    auto matchingIterator = std::find_if(this->begin(),
                                         this->end(),
                                         id_equal_function);

    // A match was found
    if (matchingIterator != this->end()){
        ItemStack& stackToUpdate = *matchingIterator;

        if (stackToUpdate.permitsStacking()) {
            stackToUpdate.addItemsFrom(itemStack);
            return true;
        }
    }

    // There is no space for a new type of `ItemStack`
    if (this->isFull()) {
        return false;
    }

    // This is a new type of item and there is plenty of room
    allItemStacks.push_back(itemStack);
    return true;
}

//------------------------------------------------------------------------------
double Inventory::percentUtilized() const
{
    return 100.0 * utilizedSlots() / totalSlots();
}

//------------------------------------------------------------------------------
Inventory::iterator Inventory::begin()
{
    return allItemStacks.begin();
}

//------------------------------------------------------------------------------
Inventory::iterator Inventory::end()
{
    return allItemStacks.end();
}

//------------------------------------------------------------------------------
Inventory::const_iterator Inventory::begin() const
{
    return allItemStacks.begin();
}

//------------------------------------------------------------------------------
Inventory::const_iterator Inventory::end() const
{
    return allItemStacks.end();
}

//------------------------------------------------------------------------------
void Inventory::display(std::ostream &outs) const
{
    outs << " -Used " << percentUtilized() << "% of " << slots << " slots" << "\n";

    for (const ItemStack& it : *this) {
        outs << it << "\n";
    }
}

//------------------------------------------------------------------------------
Inventory& Inventory::operator=(Inventory rhs)
{
    std::swap(*this, rhs);
    return *this;
}

//------------------------------------------------------------------------------
void Inventory::swap(Inventory& other)
{
    using std::swap;

    Inventory& lhs = *this;
    Inventory& rhs = other;

    swap(lhs.allItemStacks, rhs.allItemStacks);
    swap(lhs.slots, rhs.slots);
}

//------------------------------------------------------------------------------
bool operator==(const Inventory& lhs, const Inventory& rhs)
{
    if (lhs.utilizedSlots() != rhs.utilizedSlots()) {
        return false;
    }

    if (lhs.totalSlots() != rhs.totalSlots()) {
        return false;
    }

    return std::equal(lhs.begin(), lhs.end(), rhs.begin(), rhs.end());
}

//------------------------------------------------------------------------------
// This function is missing. While you do not have to write it... you need to
// be able to explain:
//
//   1. Why the program still works without operator<
//   2. Why not including operator< *can* lead to issues
//
// bool operator<(const Inventory& lhs, const Inventory& rhs)
