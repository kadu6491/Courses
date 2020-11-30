package edu.odu.cs.cs350.avocado4;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CatalogerIT {
	
	List<String> splitBlocks;
	
	String startingStringNERName;
	String expectedStringNERName;
	
	String startingStringNERNoName;
	
	String startingStringNoNERName;
	String expectedStringNoNERName;
	
	String startingStringNoNERNoName;
	
	@Before
	public void setUp() {
		splitBlocks = new ArrayList<String>();
		startingStringNERName = "<NER>Hello, my name is Joe</NER>";
		expectedStringNERName = "<NER>Hello, my name is <PER>Joe</PER></NER>";
		
		startingStringNERNoName = "<NER>Hello, I have no name</NER>";
		
		startingStringNoNERName = "Hello, my name is Joe";
		expectedStringNoNERName = "Hello, my name is <PER>Joe</PER>";
		
		startingStringNoNERNoName = "Hello, I have no name";
	}

	@Test
	public void testCatalogue() {
		String originalNER = startingStringNERNoName;
		String originalNoNER = startingStringNoNERNoName;
		
		splitBlocks.add(startingStringNERName);
		splitBlocks.add(startingStringNoNERName);
		splitBlocks.add(startingStringNERNoName);
		splitBlocks.add(startingStringNoNERNoName);
		
		List<String> results = new ArrayList<String>();
		results = Cataloger.catalogue(splitBlocks);
		
		assertThat(results.get(0), is(expectedStringNERName));
		assertThat(results.get(1), is(expectedStringNoNERName));
		assertThat(results.get(2), is(originalNER));
		assertThat(results.get(3), is(originalNoNER));
	}

}