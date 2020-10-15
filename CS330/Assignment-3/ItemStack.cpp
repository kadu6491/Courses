#include <iomanip>

#include "ItemStack.h"


const Item ItemStack::DEFAULT_ITEM(0, "Air");


//------------------------------------------------------------------------------
ItemStack::ItemStack()
    :ItemStack(DEFAULT_ITEM, 0)
{
}

//------------------------------------------------------------------------------
ItemStack::ItemStack(const Item& inputItem, int s)
    :quantity(s)
{
    // Create a copy (clone) of inputItem and reference it with the this->item
    // pointer
    this->item = inputItem.clone();
}

//------------------------------------------------------------------------------
ItemStack::ItemStack(const ItemStack& src)
{
    // 1 . Create a copy (clone) of src.item and reference it with the
    //     this->item pointer.
    //
    // 2. Do not forget to copy src.quantity.
    //
    this->item    = src.item->clone();
    this->quantity = src.quantity;
}

//------------------------------------------------------------------------------
ItemStack::~ItemStack()
{
    // Every pointer must be deleted to prevent memory leaks (item is a pointer).
    delete this->item;
}

//------------------------------------------------------------------------------
ItemStack& ItemStack::operator=(ItemStack rhs)
{
    swap(*this, rhs);

    return *this;
}

//------------------------------------------------------------------------------
Item ItemStack::getItem() const
{
    return *(this->item);
}

//------------------------------------------------------------------------------
int ItemStack::size() const
{
    return this->quantity;
}

//------------------------------------------------------------------------------
void ItemStack::addItems(int a)
{
    this->quantity += a;
}

//------------------------------------------------------------------------------
void ItemStack::addItemsFrom(const ItemStack& other)
{
    this->quantity += other.quantity;
}

//------------------------------------------------------------------------------
bool ItemStack::operator==(const ItemStack& rhs) const
{
    // Compare this and rhs for equivalence based on the ids of this->item and
    // rhs.item.
    if(rhs.item != this->item)
    {    
        return true;//(rhs.item == this->item);
    }
    return false;
    //return false; // replace this line
}

//------------------------------------------------------------------------------
bool ItemStack::operator<(const ItemStack& rhs) const
{
    // Order (sort) this and rhs based on the ids of this->item and rhs.item.
  
   return (this->item->getID() < rhs.item->getID());
   // return false; // replace this line
}

//------------------------------------------------------------------------------
void ItemStack::display(std::ostream& outs) const
{
    outs << std::right << "(" << std::setw(2) << this->size() << ") "
         << (this->getItem()).getName();
}

//------------------------------------------------------------------------------
void swap(ItemStack& lhs, ItemStack& rhs)
{
    // Swap the item data members and quantity data members for lhs and rhs.
    using std::swap;
    swap(lhs.item, rhs.item);
    swap(lhs.quantity, rhs.quantity);
}
