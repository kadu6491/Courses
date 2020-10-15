#include <algorithm>

#include "ItemFactory.h"

using std::find_if;

const ItemFactory::ItemPair ItemFactory::_KNOWN_ITEMS[] = {
    {"Armour"    , std::unique_ptr<Item>(new Armour())},
    {"Armor"     , std::unique_ptr<Item>(new Armour())},
    // {"Tool" , std::unique_ptr<Item>(new Tool())}, // TBA in future assignment
    {"Food"      , std::unique_ptr<Item>(new Consumable())},
    {"Potion"    , std::unique_ptr<Item>(new Consumable())},
    {"Disposable", std::unique_ptr<Item>(new Consumable())}
};

//------------------------------------------------------------------------------
Item* ItemFactory::createItem(std::string type)
{
    for (const ItemPair& thePair : _KNOWN_ITEMS) {
        if (thePair.first == type) {
            return (thePair.second)->clone();
        }
    }

    // A item with the given type could not be found
    return nullptr;
}

//------------------------------------------------------------------------------
bool ItemFactory::isKnown(std::string type)
{
    const auto it = find_if(begin(_KNOWN_ITEMS), end(_KNOWN_ITEMS),
                           [type](const ItemPair& thePair) -> bool
                           {
                               return thePair.first == type;
                           });

    return it != end(_KNOWN_ITEMS);
}

//------------------------------------------------------------------------------
/**
 * Read the rest of a line and discard it.
 */
inline
void discardRestOfLine(std::istream& ins)
{
    std::string aether;
    getline(ins, aether);

}

//------------------------------------------------------------------------------
std::istream& operator>>(std::istream& ins, Item*& rd)
{
    std::string type;  // first "word" on line

    // Read the item type and retrieve a template Item from the ItemFactory
    ins >> type;
    rd = ItemFactory::createItem(type);

    if (rd == nullptr) {
        discardRestOfLine(ins);
    }
    else {
        rd->read(ins);
    }

    return ins;
}
