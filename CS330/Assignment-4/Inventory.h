#ifndef INVENTORY_H_INCLUDED
#define INVENTORY_H_INCLUDED

#include <iostream>
#include <vector>

#include "ItemStack.h"

/**
 * An Inventory is composed of n slots. Each slot may store only
 * one type of item--specified by *slots*.
 * <p>
 * Once all slots are filled, no additional Item types may be stored.
 * Individual slots may contain any number of the same
 * Item.
 */
class Inventory {
    public:
        /**
         * Alias for a container of ItemStacks.
         */
        using ItemStackCollection = std::vector<ItemStack>;

        using iterator       = ItemStackCollection::iterator;
        using const_iterator = ItemStackCollection::const_iterator;

    private:
        /**
         * All `ItemStack`s in *this* `Inventory`
         */
        ItemStackCollection allItemStacks;

        /**
         * Total capacity of *this* `Inventory`.
         */
        int slots;

    public:
        /**
         * Default to 10 slots
         */
        Inventory();

        /**
         * Create an inventory.
         *
         * @param n total slots (capacity)
         *
         * @pre n > 0
         */
        Inventory(int n);

        /**
         * Duplicate an existing Inventory
         *
         * @param src existing Inventory.
         */
        Inventory(const Inventory& src);

        /**
         * Empty all Inventory slots.
         *
         * Let the compiler do all the work;
         */
        ~Inventory() = default;

        /**
         * Check the number of used/utilized (i.e., non-empty).
         */
        int utilizedSlots() const;

        /**
         * Check the number of unused (i.e., empty) slots.
         */
        int emptySlots() const;

        /**
         * Retrieve the total size (number of slots in total).
         */
        int totalSlots() const;

        /**
         * Check if this inventory is full
         *
         * @return true if all slots are filled and false otherwise
         */
        bool isFull() const;

        /**
         * Add one or more items to the inventory list
         *
         * @return true if *stack* was added and false otherwise
         */
        bool addItems(ItemStack itemStack);

        /**
         * Retrieve the utlization as a percent (used / total)
         */
        double percentUtilized() const;

        /**
         * Print a Summary of the Inventory and all Items contained within
         */
        void display(std::ostream& outs) const;

        // Begin Iterator Support (begin/end)
        iterator begin();
        iterator end();

        const_iterator begin() const;
        const_iterator end() const;
        // End Iterator Support

        /**
         * Assignment operator (implemented using the copy-and-swap trick).
         */
        Inventory& operator=(Inventory rhs);

        /**
         * Swap the contents of *this* and another inventory.
         */
        void swap(Inventory& other);
};

/**
 * Print the Inventory through use of the display member function
 */
inline
std::ostream& operator<<(std::ostream& outs, const Inventory& prt)
{
    prt.display(outs);
    return outs;
}

/**
 * Compare two Inventory objects, without direct access
 */
bool operator==(const Inventory& lhs, const Inventory& rhs);

/**
 * Swap the contents of two inventories. This is a wrapper around the swap
 * member function.
 *
 * @param lhs first inventory (left hand side)
 * @param rhs second inventory (right hand side)
 */
inline
void swap(Inventory& lhs, Inventory& rhs)
{
    lhs.swap(rhs);
}

#endif
