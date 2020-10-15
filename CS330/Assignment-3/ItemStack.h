#ifndef ITEMSTACK_H_INCLUDED
#define ITEMSTACK_H_INCLUDED

#include <iostream>

#include "Item.h"

using namespace std::rel_ops;

/**
 * A Homogeneous--i.e., uniform--stack of Items.
 */
class ItemStack {
    private:
        /**
         * Default Air item with id 0.
         */
        static const Item DEFAULT_ITEM;

        /**
         * Item out of which the stack is composed.
         */
        Item* item;

        /**
         * Number of items in the stack.
         */
        int quantity;

    public:
        /**
         * Default to an empty stack composed of Air
         */
        ItemStack();

        /**
         * Create a stack of type *item*
         *
         * @param item Item out of which the stack is composed
         * @param s size of the stack
         *
         * @pre (s > 0)
         */
        ItemStack(const Item& item, int s);

        // The Big-3
        ItemStack(const ItemStack& src);
        ~ItemStack();
        ItemStack& operator=(ItemStack rhs);
        // End the Big-3

        /**
         * Retrieve the Item out of which the stack is composed
         */
        Item getItem() const;

        /**
         * Retrieve the size of the stack
         */
        int size() const;

        /**
         * Increase the size of the stack
         *
         * @param a number of items to add
         * @pre a > 0
         */
        void addItems(int a);

        /**
         * Increase the size of the stack
         *
         * @param other ItemStack with the items to move (i.e., steal).
         *
         * @pre *this.item == other.item
         */
        void addItemsFrom(const ItemStack& other);

        /**
         * Consider two stacks to be the same if
         * they contain the same type of Item.
         */
        bool operator==(const ItemStack& rhs) const;

        /**
         * Order stacks based on Item id.
         */
        bool operator<(const ItemStack& rhs) const;

        /**
         * The usual display function.
         */
        void display(std::ostream& outs) const;

        /**
         * The usual friend-ly swap function.
         */
        friend
        void swap(ItemStack& lhs, ItemStack& rhs);
};

/**
 * Print the ItemStack using the display function.
 */
inline
std::ostream& operator<<(std::ostream& outs, const ItemStack& prt)
{
    prt.display(outs);

    return outs;
}

#endif
