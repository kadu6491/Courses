package edu.odu.cs.cs350.avocado4;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WindowTest {
	String test1;
	List<Token> listTest1;

	@Before
	public void setUp() {
		test1 = "Hello Joe";
		listTest1 = Token.createTokenList(test1);
	}

	@Test
	/**
	 * Covers the Window(List<Token> tokensToCheck, int target) parameterized constructor
	 */
	public void testWindow() {
		int originalSize = listTest1.size();
		Window window1 = new Window(listTest1, 0);
		assertThat(listTest1.size(), is(originalSize));
		assertThat(listTest1.get(0).getName(), is("Hello"));
		assertThat(listTest1.get(1).getName(), is("Joe"));
		assertThat(window1.getWindowTokens().size(), is(11));
		assertThat(window1.getWindowFeatures(), is(notNullValue()));
	}

}
