package edu.odu.cs.cs350.avocado4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.core.IsNull;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;


public class TextBlocksTest {
	
	
	String startingStringNER;
	String startingStringNoNER;
	String over4000Characters;
	List<String> splitBlocksNER;
	List<String> splitBlocksNoNER;
	
    @Before
    public void setUp()
    {
    	startingStringNER = "<NER>hello</NER>\n<NER>this is a test</NER>";
    	startingStringNoNER = "hello" + "\n" + "this is a test";
    	
    	over4000Characters = "";
    	Random rand = new Random();
    	int randChar;
    	for(int i = 0; i < 4001; i++) {
    		randChar = rand.nextInt(122-66) + 65;
    		over4000Characters += (randChar % 7 == 0) ? ' ' : ((char)randChar);
    	}
    	
    	splitBlocksNER = new ArrayList<String>();
    	splitBlocksNER = Arrays.asList("<NER>hello</NER>","<NER>this is a test</NER>");
    	splitBlocksNoNER = new ArrayList<String>();
    	splitBlocksNoNER = Arrays.asList("hello" + "\n" + "this is a test");
    }

	@Test
	/**
	 * This tests the parseTextBlocks method on a string longer than 4000 characters
	 */
	public void testParseTextBlocksOver4000() {
		try {
			TextBlocks.parseTextBlocks(over4000Characters);
			fail("parseTextBlocks method should have failed.");
		} catch(IllegalArgumentException ex) {
			// Error was thrown successfully.
		}
	}

	@Test
	/**
	 * This tests the parseTextBlocks method on a string containing NER tags
	 */
	public void testParseTextBlocksWithNER() {
		List<String> splitBlocks = TextBlocks.parseTextBlocks(startingStringNER);
		assertThat(startingStringNER, is("<NER>hello</NER>\n<NER>this is a test</NER>"));
		assertThat(splitBlocks, is(notNullValue()));
		assertThat(splitBlocks.get(0), is(splitBlocksNER.get(0)));
		assertThat(splitBlocks.get(1), is(splitBlocksNER.get(1)));
		assertThat(splitBlocks.size(), is(splitBlocksNER.size()));
	}
	
	@Test
	/**
	 * This tests the parseTextBlocks method on a string containing no NER tags
	 */
	public void testParseTextBlocksWithoutNER() {
		List<String> splitBlocks = TextBlocks.parseTextBlocks(startingStringNoNER);
		assertThat(startingStringNoNER, is("hello" + "\n" + "this is a test"));
		assertThat(splitBlocks, is(notNullValue()));
		assertThat(splitBlocks.get(0), is(splitBlocksNoNER.get(0)));
		assertThat(splitBlocks.size(), is(splitBlocksNoNER.size()));
	}

	@Test
	/**
	 * This tests the joinTextBlocks method
	 */
	public void testJoinTextBlocks() {
    	List<String> splitBlocksOriginal = new ArrayList<String>();
    	splitBlocksOriginal = Arrays.asList("<NER>hello</NER>","<NER>this is a test</NER>");
    	
		String combinedText = TextBlocks.joinTextBlocks(splitBlocksNER);
		assertThat(splitBlocksNER, is(splitBlocksOriginal));
		assertThat(combinedText, is(notNullValue()));
		assertThat(combinedText, is(startingStringNER));
		
    	splitBlocksOriginal = Arrays.asList("hello" + "\n" + "this is a test");
    	
		combinedText = TextBlocks.joinTextBlocks(splitBlocksNoNER);
		assertThat(splitBlocksNoNER, is(splitBlocksOriginal));
		assertThat(combinedText, is(notNullValue()));
		assertThat(combinedText, is(startingStringNoNER));
	}

	@Test
	/**This tests the markPersonalNames method
	 * This function also has an associated integration test
	 * The integration test supplies input and tests for expected output,
	 * so these were not included in the below unit tests.
	 */
	public void testMarkPersonalNames() {
		Writer w = new Writer();
		TextBlocks.markPersonalNames(startingStringNER, w);
		assertThat(w.read(), is(notNullValue()));
	}

}