package edu.odu.cs.cs350.avocado4;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WriterTest {

	/**
	 * This is a test of creating a new Writer object.
	 */
	@Test
	public void testWriter() {
		String s = "This is a test of the writer";
		Writer w = new Writer();
		assertThat(w, is(notNullValue()));
		
		w.write(s);
		assertThat(w.read(), is(s));
	}

}
