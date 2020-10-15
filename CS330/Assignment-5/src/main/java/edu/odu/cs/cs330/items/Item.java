package edu.odu.cs.cs330.items;

import java.util.Scanner;

/**
 * Item represents an individual Item in an inventory.
 * This includes items such as potions, building materials, and food.
 *
 * Only one of each item can exist--i.e., no two items share the
 * same numeric id.
 */
public abstract class Item implements Cloneable {
    /**
     * Indicates whether this Item can placed in an ItemStack larger than 1.
     */
    private boolean stackable;

    /**
     * Short title--e.g., HP Potion
        */
    protected String name;

    /**
     * Default to name = Air and stackable = true
     */
    public Item()
    {
        this.name      = "Air";
        this.stackable = true;
    }

    /**
     * Create an Item with a specified and name
     *
     * @param name desired name
     *
     * @pre
     *  - all items that share a name are of the same type
     *  - differences in capitalization denote different items
     */
    public Item(String name)
    {
        this.name      = name;
        this.stackable = true;
    }

    /**
     * Create an Item with a specified id and name
     *
     * @param name desired name
     * @param stackable flag that indicates whether duplicates
     *      of this item can be stacked
     *
     * @pre
     *  - all items that share a name are of the same type
     *  - differences in capitalization denote different items
     */
    public Item(String name, boolean stackable)
    {
        this.name      = name;
        this.stackable = stackable;
    }

    /**
     * Retrieve name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Update name
     */
    public void setName(String n)
    {
        this.name = n;
    }

    /**
     * Check for logical equivalence--based on name
     */
    @Override
    public boolean equals(Object rhs)
    {
        Item r = (Item) rhs;

        return this.name.equals(r.name);
    }

    /**
     * Generate a hash code based on name
     */
    @Override
    public int hashCode()
    {
        return this.name.hashCode();
    }

    /**
     * Can this item be placed in a stack?
     */
    public boolean isStackable()
    {
        return this.stackable;
    }

    /**
     * Read the item from an input array
     */
    public abstract void read(Scanner s);

    /**
     * Duplicate this item
     */
    @Override
    public abstract Item clone();

    /**
     * *Print* an Item
     */
    @Override
    public String toString()
    {
        return " " + this.name;
    }
}


