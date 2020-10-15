package edu.odu.cs.cs330.items;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.core.IsNull;

import java.io.StringReader;
import java.util.Scanner;


/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 *
 * This is technically an Integration Test.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestItemFactory
{
    Consumable foodItem;

    @Before
    public void setUp()
    {
        foodItem = new Consumable();
        foodItem.setName("Green-Tea");
        foodItem.setEffect("Wake-Up");
        foodItem.setNumberOfUses(5);
    }

    @Test
    public void testCreateItem()
    {
        // Test a known valid type keyword
        Item item = ItemFactory.createItem("Food");

        assertThat(item, not(nullValue()));
        assertThat(item, instanceOf(Consumable.class));

        // Test an known invalid Item type keyword
        item = ItemFactory.createItem("This Is Not a valid Item Type");
        assertThat(item, is(nullValue()));

    }

    @Test
    public void testIsKnown()
    {
        assertTrue(ItemFactory.isKnown("Food"));
        assertTrue(ItemFactory.isKnown("Armor"));
        assertFalse(ItemFactory.isKnown("PHP is an okay language. FALSE!"));
    }

    @Test
    public void testParseItemLineSuccess()
    {
        String inputStr = "Food Green-Tea Wake-Up 5";
        Scanner ins = new Scanner(new StringReader(inputStr));

        Item item = ItemFactory.parseItemLine(ins);

        assertThat(item, equalTo(foodItem));
        assertThat(item.toString(), equalTo(foodItem.toString()));
    }

    @Test
    public void testParseItemLineFailure()
    {
        String inputStr = "NOTACTUALLY-Food Green-Tea Wake-Up 5";
        Scanner ins = new Scanner(new StringReader(inputStr));

        Item item = ItemFactory.parseItemLine(ins);
        assertThat(item, is(nullValue()));
    }
}

