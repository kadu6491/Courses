#ifndef CONSUMABLE_H_INCLUDED
#define CONSUMABLE_H_INCLUDED

#include "Item.h"

/**
 * This class represents one Consumable Item--as found in most video games.
 * This includes food.
 *
 * Consumable Items must be stackable. All Constructors must initialize
 * Item::stackable to true.
 */
class Consumable : public Item {
    private:

    protected:
        /**
         * The effect recieved by using the Item.
         */
        std::string effect;

        /**
         * Number of time this Item can be used.
         */
        int uses;

    public:
        /**
         * Default to a Comsumable Item with an empty name
         */
        Consumable();

        // Big-3
        Consumable(const Consumable& src) = default;
        ~Consumable() = default;
        Consumable& operator=(Consumable& rhs) = default;

        /**
         * Retrieve effect
         */
        std::string getEffect() const;

        /**
         * Set effect
         */
        void setEffect(std::string effect);

        /**
         * Retrieve number of uses
         */
        int getNumberOfUses() const;

        /**
         * Set number of uses
         */
        void setNumberOfUses(int u);

        /**
         * Print the Consumable Item
         */
        virtual void display(std::ostream& outs) const;

        /**
         * Read Consumable Item attributes from an input stream
         */
        virtual void read(std::istream& ins);

        /**
         * Clone--i.e., copy--this Consumable Item
         */
        virtual Item* clone() const;
};

//------------------------------------------------------------------------------
inline
std::string Consumable::getEffect() const
{
    return this->effect;
}

//------------------------------------------------------------------------------
inline
void Consumable::setEffect(std::string effect)
{
    this->effect = effect;
}

//------------------------------------------------------------------------------
inline
int Consumable::getNumberOfUses() const
{
    return this->uses;
}

//------------------------------------------------------------------------------
inline
void Consumable::setNumberOfUses(int u)
{
    this->uses = u;
}

#endif
