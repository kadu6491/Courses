package edu.odu.cs.cs330.items;

/**
 * A Homogeneous--i.e., uniform--stack of Items.
 */
public class ItemStack {
    private Item item;   ///< Item out of which the stack is composed
    private int quantity; ///< Number of items in the stack

    /**
     * Default to an empty stack composed of Air
     */
    public ItemStack()
    {
        this.item     = null;
        this.quantity = 0;
    }

    /**
     * Create a stack of type *item*
     *
     * @param item Item out of which the stack is composed
     */
    public ItemStack(Item item)
    {
        this.item     = item.clone();
        this.quantity = 1;
    }

    /**
     * Copy Constructor - Why is this necessary?
     */
    public ItemStack(ItemStack src)
    {
        this.item     = src.item.clone();
        this.quantity = src.quantity;
    }

    /**
     * Retrieve the Item out of which the stack is composed
     */
    public Item getItem()
    {
        return this.item;
    }

    /**
     * Retrieve the size of the stack
     */
    public int size()
    {
        return this.quantity;
    }

    /**
     * Increase the size of the stack
     *
     * @param a number of items to add
     */
    public void addItems(int a)
    {
        // Add *a* items if stacking is permitted
        // otherwise, silently discard *a* items
        if (item.isStackable()) {
            this.quantity += a;
        }
    }

    /**
     * Does that Item contained in this stack permit stacking?
     *
     * This can be less formally phrased, is this a stackable ItemStack?
     */
    public boolean permitsStacking()
    {
        return item.isStackable();
    }

    /**
     * Consider two stacks to be the same if
     * they contain the same type of Item
     */
    @Override
    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof ItemStack)) {
            return false;
        }

        ItemStack r = (ItemStack)rhs;

        return this.item.equals(r.item);
    }

    /**
     * Generate a hash code based on item
     */
    @Override
    public int hashCode()
    {
        return this.item.hashCode();
    }

    /**
     * Order stacks based on Item name
     */
    //boolean operator<(ItemStack rhs);

    /**
     * Print the ItemStack directly
     */
    @Override
    public String toString()
    {
        String outs = this.item.toString();

        if (this.permitsStacking()) {
            outs += "  Qty: " + this.quantity + "\n";
        }

        return outs;
    }
}
