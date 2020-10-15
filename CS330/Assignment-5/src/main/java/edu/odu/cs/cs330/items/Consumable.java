package edu.odu.cs.cs330.items;

import java.util.Scanner;

/**
 * This class represents one Consumable Item--as found in most video games. This includes
 * food.
 *
 * Consumable Items must be stackable. All Constructors must initialize Item::stackable
 * to true.
 */
public class Consumable extends Item {
    protected String effect; ///< effect recieved by using the Item
    protected int uses;      ///< number of times this Item can be used

    /**
     * Default to a Comsumable Item with an empty name
     */
    public Consumable()
    {
        // Make the necessary call to super(...)
        super("", true);
        this.effect = "";
        this.uses   = 0;
    }

    /**
     * Copy Constructor
     */
    public Consumable(Consumable src)
    {
        super(src.name, true); // Is this a hint???

        this.effect = src.effect;
        this.uses  = src.uses;
    }

    /**
     * Retrieve effect
     */
    public String getEffect()
    {
        return this.effect;
    }

    /**
     * Set effect
     */
    public void setEffect(String effect)
    {
        this.effect = effect;
    }

    /**
     * Retrieve number of uses
     */
    public int getNumberOfUses()
    {
        return this.uses;
    }

    /**
     * Set number of uses
     */
    public void setNumberOfUses(int u)
    {
        this.uses = u;
    }

    /**
     * Read Consumable Item attributes
     */
    @Override
    public void read(Scanner s)
    {
        super.name = s.next();
	effect     = s.next();
	uses       = s.nextInt();
        // Read in the remaining values

    }

    /**
     * Clone--i.e., copy--this Consumable Item
     */
    @Override
    public Item clone()
    {
        return new Consumable(this);
    }

    /**
     * *Print* the Consumable Item
     */
    @Override
    public String toString()
    {
        return "  Nme: " + super.name + "\n"
             + "  Eft: " + effect     + "\n"
             + "  Use: " + uses       + "\n";
    }
}
