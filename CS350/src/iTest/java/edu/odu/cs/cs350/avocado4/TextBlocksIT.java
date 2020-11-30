package edu.odu.cs.cs350.avocado4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextBlocksIT {

	Writer w;
	String startingStringNERName;
	String expectedStringNERName;
	
	String startingStringNERNoName;
	
	String startingStringNoNERName;
	String expectedStringNoNERName;
	
	String startingStringNoNERNoName;
	
	String startingStringMultiple;
	String expectedStringMultiple;
	
	@Before
	public void setUp() {
		w = new Writer();
		startingStringNERName = "<NER>Hello, my name is Joe</NER>";
		expectedStringNERName = "<NER>Hello, my name is <PER>Joe</PER></NER>";
		
		startingStringNERNoName = "<NER>Hello, I have no name</NER>";
		
		startingStringNoNERName = "Hello, my name is Joe";
		expectedStringNoNERName = "Hello, my name is <PER>Joe</PER>";
		
		startingStringNoNERNoName = "Hello, I have no name";
		
		startingStringMultiple = "<NER>Hello</NER>" + "\n" + "<NER>My name is Joe</NER>";
		expectedStringMultiple = "<NER>Hello</NER>" + "\n" + "<NER>My name is <PER>Joe</PER></NER>";
	}

	@Test
	public void testMarkPersonalNamesNER() {
		TextBlocks.markPersonalNames(startingStringNERName, w);
		assertTrue(w.read().equals(expectedStringNERName));
		
		String original = startingStringNERNoName;
		TextBlocks.markPersonalNames(startingStringNERNoName, w);
		assertTrue(w.read().equals(original));
	}
	
	@Test
	public void testMarkPersonalNamesNoNER() {
		TextBlocks.markPersonalNames(startingStringNoNERName, w);
		assertTrue(w.read().equals(expectedStringNoNERName));
		
		String original = startingStringNoNERNoName;
		TextBlocks.markPersonalNames(startingStringNoNERNoName, w);
		assertTrue(w.read().equals(original));
	}
	
	@Test
	public void testMarkPersonalNamesMultiple() {
		TextBlocks.markPersonalNames(startingStringMultiple, w);
		assertTrue(w.read().equals(expectedStringMultiple));
	}

}
