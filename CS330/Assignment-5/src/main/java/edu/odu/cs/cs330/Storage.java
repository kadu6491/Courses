package edu.odu.cs.cs330;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import edu.odu.cs.cs330.items.*;

public class Storage {
    /**
     * This is the first Java Assignment, Item Storage in Java.
     *
     * @param argv[1] items filename
     * @param argv[2] inventories filename
     */
    public static void main(String[] argv)
    {
        BufferedReader infile = null;
        Inventory inv = null;
        int invSize = 10;

        if (argv.length < 1) {
            System.err.println("Usage: java -jar Storage.jar items-file");
            System.exit(1);
        }

        // If an inventory size was specified, parse it.
        try {
            invSize = Integer.parseInt(argv[1]);
        }
        catch (IndexOutOfBoundsException e) {
            // pass
            invSize = 10; //default to 10
        }
        catch (NumberFormatException e) {
            System.err.println("Inventory size must be an integer");
            System.exit(2);
        }

        // Default to 10 if invSize is invalid--i.e., <= 0
        if (invSize < 1) {
            invSize = 10;
        }

        try {
            infile = new BufferedReader(new FileReader(argv[0]));
            inv = createInventory(infile, invSize);
            infile.close();

        }
        catch (IOException e) {
            System.err.println("Error: " + argv[0] + " could not be opened or read");
            System.exit(3);
        }

        // Print the Inventory
        printInventorySummary(inv);
    }

    /**
     * Read an input stream and generate an Inventory.
     *
     * @param ins source from which to read Items
     * @param size desired number of Inventory slots
     *
     * @return initialized Inventory
     *
     * @throws IOException if an input error occurs
     */
    public static Inventory createInventory(BufferedReader ins, int size)
        throws IOException
    {
        Scanner   scanner   = new Scanner(ins);
        Inventory inventory = new Inventory(size);

        System.out.println("Processing Log:");

        while (scanner.hasNext()) {
            Item item = ItemFactory.parseItemLine(scanner);

            if (item != null) {
                boolean success = inventory.addItems(new ItemStack(item));

                if (success) {
                    System.out.println(" (S) " + item.getName());
                }
                else {
                   System.out.println(" (D) " + item.getName());
                }
            }
        }
        System.out.println();

        return inventory;
    }

    /**
     * Print the final Inventory summary.
     *
     * @param inv Inventory to print
     */
    public static void printInventorySummary(Inventory inv)
    {
        System.out.println("Player Storage Summary:");
        System.out.println(inv);
    }
}
