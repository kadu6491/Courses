#ifndef ITEMFACTORY_H_INCLUDED
#define ITEMFACTORY_H_INCLUDED

#include <iostream>
#include <utility>
#include <memory>
#include <string>

#include "Item.h"
#include "Armour.h"
#include "Consumable.h"

/**
 * The Item Creating Wizard
 */
class ItemFactory{
    private:
        using ItemPair = std::pair<std::string, std::unique_ptr<Item>>;

        static const ItemPair _KNOWN_ITEMS[];  ///< Listing of known items

    public:
        /**
         * Create a Item
         *
         * @param type the item to be created
         *
         * @return A item with the specified type
         *     or nullptr if no matching item is found
         */
        static Item* createItem(std::string type);

        /**
         * Determine whether a given item is known
         *
         * @param type the item for which to query
         */
        static bool isKnown(std::string type);
};

/**
 * Create the appropriate Item class--e.g., Tool, Armour or Consumable.
 *
 * How is **inheritance** used?
 */
std::istream& operator>>(std::istream& ins, Item*& rd);

#endif
