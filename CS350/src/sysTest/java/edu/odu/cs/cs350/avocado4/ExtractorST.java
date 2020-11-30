package edu.odu.cs.cs350.avocado4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExtractorST {

	String startingStringNERName;
	String expectedStringNERName;
	
	String startingStringNERNoName;
	
	String startingStringNoNERName;
	String expectedStringNoNERName;
	
	String startingStringNoNERNoName;
	
	String startingStringMultiple;
	String expectedStringMultiple;
	
	String startingStringLongName;
	String expectedStringLongName;
	
	@Before
	public void setUp() {
		startingStringNERName = "<NER>Hello, my name is Joe</NER>";
		expectedStringNERName = "<NER>Hello, my name is <PER>Joe</PER></NER>";
		
		startingStringNERNoName = "<NER>Hello, I have no name</NER>";
		
		startingStringNoNERName = "Hello, my name is Joe";
		expectedStringNoNERName = "Hello, my name is <PER>Joe</PER>";
		
		startingStringNoNERNoName = "Hello, I have no name";
		
		startingStringMultiple = "<NER>Hello</NER>" + "\n" + "<NER>My name is Joe</NER>";
		expectedStringMultiple = "<NER>Hello</NER>" + "\n" + "<NER>My name is <PER>Joe</PER></NER>";
		
		startingStringLongName = "<NER>Hello, my name is John Jacob Smith</NER>";
		expectedStringLongName = "<NER>Hello, my name is <PER>John Jacob Smith</PER></NER>";
	}
	
	@Test
	/**
	 * Tests the ability to exclude strings longer than 4000 characters from being processed
	 */
	public void testMarkPersonalNamesTooLong() {
		String strTooLong = "";
		for(int i = 0; i < 4001; i++) strTooLong += "t";
		
		try {
			Extractor.markPersonalNames(strTooLong);
			fail("parseTextBlocks method should have failed.");
		} catch(IllegalArgumentException ex) {
			// Error was thrown successfully.
		}
	}

	@Test
	/**
	 * Tests the ability to locate names in strings surrounded with NER tags
	 */
	public void testMarkPersonalNamesNER() {
		String result = Extractor.markPersonalNames(startingStringNERName);
		assertTrue(result.equals(expectedStringNERName));
		
		String original = startingStringNERNoName;
		result = Extractor.markPersonalNames(startingStringNERNoName);
		assertTrue(result.equals(original));
	}
	
	@Test
	/**
	 * Tests the ability to locate names in strings that do not have NER tags
	 */
	public void testMarkPersonalNamesNoNER() {
		String result = Extractor.markPersonalNames(startingStringNoNERName);
		assertTrue(result.equals(expectedStringNoNERName));
		
		String original = startingStringNoNERNoName;
		result = Extractor.markPersonalNames(startingStringNoNERNoName);
		assertTrue(result.equals(original));
	}
	
	@Test
	/**
	 * Tests the ability to find multiple separate names in a string
	 */
	public void testMarkPersonalNamesMultiple() {
		String result = Extractor.markPersonalNames(startingStringMultiple);
		assertTrue(result.equals(expectedStringMultiple));
	}
	
	@Test
	/**
	 * Tests the ability to find a name that is multiple words long
	 */
	public void testMarkPersonalNamesLongName() {
		String result = Extractor.markPersonalNames(startingStringLongName);
		assertTrue(result.equals(expectedStringLongName));
	}

}
