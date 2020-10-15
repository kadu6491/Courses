#include <iomanip>

#include "ItemStack.h"

//------------------------------------------------------------------------------
ItemStack::ItemStack()
    :item(nullptr),
     quantity(0)
{
}

//------------------------------------------------------------------------------
ItemStack::ItemStack(const Item& inputItem, int s)
    :quantity(s)
{
    this->item = inputItem.clone();
}

//------------------------------------------------------------------------------
ItemStack::ItemStack(const ItemStack& src)
{
    this->item = src.item->clone();
    this->quantity = src.quantity;
}

//------------------------------------------------------------------------------
ItemStack::~ItemStack()
{
    delete this->item;
}

//------------------------------------------------------------------------------
ItemStack& ItemStack::operator=(ItemStack rhs)
{
    swap(*this, rhs);

    return *this;
}

//------------------------------------------------------------------------------
Item& ItemStack::getItem() const
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
    // Add *a* items if stacking is permitted
    // otherwise, silently discard *a* items
    if (item->isStackable()) {
        this->quantity += a;
    }
}
//------------------------------------------------------------------------------
void ItemStack::addItemsFrom(const ItemStack& other)
{
    if (this->permitsStacking()) {
        this->quantity += other.quantity;
    }
}

//------------------------------------------------------------------------------
bool ItemStack::operator==(const ItemStack& rhs) const
{
    return *(this->item) == *(rhs.item);
}

//------------------------------------------------------------------------------
bool ItemStack::operator<(const ItemStack& rhs) const
{
    return *(this->item) < *(rhs.item);
}

//------------------------------------------------------------------------------
void ItemStack::display(std::ostream& outs) const
{
    getItem().display(outs);

    if (this->permitsStacking()) {
        outs << "  Qty: " << size() << "\n";
    }
}

//------------------------------------------------------------------------------
void swap(ItemStack& lhs, ItemStack& rhs)
{
    std::swap(lhs.item, rhs.item);
    std::swap(lhs.quantity, rhs.quantity);
}
