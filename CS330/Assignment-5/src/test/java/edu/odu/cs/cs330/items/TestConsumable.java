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
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConsumable
{
    @Test
    public void testDefaultConstructor()
    {
        Consumable imagination = new Consumable();

        Item  genericRef = (Item) imagination;

        assertTrue(imagination.isStackable());
        assertTrue(genericRef.isStackable());

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test

        // I should really check display() and/or operator<< here. However, I will
        // do that in a separate `testDisplay` function
    }

    @Test
    public void testCopyConstructor()
    {
        Consumable tea = new Consumable();
        tea.setName("Green Tea");
        tea.setEffect("Wake Up");
        tea.setNumberOfUses(10);

        Consumable moreTea = new Consumable(tea);

        assertThat(moreTea.isStackable(), is(true));
        assertThat(moreTea.getName(), equalTo("Green Tea"));
        assertThat(moreTea.getEffect(), equalTo("Wake Up"));
        assertThat(moreTea.getNumberOfUses(), is(10));

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test

        // I should really check display() and/or operator<< here. However, I
        // will do that in a separate `testDisplay` function
    }

    @Test
    public void testClone()
    {
        Consumable tea = new Consumable();
        tea.setName("Green Tea");
        tea.setEffect("Wake Up");
        tea.setNumberOfUses(10);

        Consumable moreTea = (Consumable) tea.clone();

        assertThat(moreTea.isStackable(), is(true));
        assertThat(moreTea.getName(), equalTo("Green Tea"));
        assertThat(moreTea.getEffect(), equalTo("Wake Up"));
        assertThat(moreTea.getNumberOfUses(), is(10));

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test

        // I should really check display() and/or operator<< here. However, I
        // will do that in a separate `testDisplay` function
    }

    @Test
    public void testToString()
    {
        Consumable tea = new Consumable();
        tea.setName("Green Tea");
        tea.setEffect("Wake Up");
        tea.setNumberOfUses(10);

        String expected = "  Nme: Green Tea\n"
                        + "  Eft: Wake Up\n"
                        + "  Use: 10\n";

        assertThat(tea.toString(), equalTo(expected));
    }

    @Test
    public void testRead()
    {
        Consumable tea = new Consumable();

        String inputStr = "Green-Tea Wake-Up 5";
        Scanner ins = new Scanner(new StringReader(inputStr));

        tea.read(ins);

        assertTrue(tea.isStackable());
        assertThat(tea.getName(), equalTo("Green-Tea"));
        assertThat(tea.getEffect(), equalTo("Wake-Up"));
        assertThat(tea.getNumberOfUses(), is(5));
    }
}
