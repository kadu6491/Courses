package edu.odu.cs.cs350.avocado4;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WindowIT {
	String test1;
	List<Token> listTest1;
	
	@Before
	public void setUp() {
		test1 = "Hello Joe";
		listTest1 = Token.createTokenList(test1);
	}

	@Test
	public void testWindow() {
		int originalSize = listTest1.size();
		Window window1 = new Window(listTest1, 0);
		StringBuffer buf = new StringBuffer();
		
		String features0 = listTest1.get(0).getFeaturesString() + "," + listTest1.get(0).getClassification();
		String features1 = listTest1.get(1).getFeaturesString() + "," + listTest1.get(1).getClassification();
		String featuresnull = "null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null";
		
		for (int i=0; i<11; i++) {
			if(i == 5) {
				buf.append(features0);
			}
			else if(i==6) {
				buf.append(features1);
			}
			else {
				buf.append(featuresnull);
			}
			if (i<10) {
				buf.append(",");
			}
		}
		
		String windowFeatures = buf.toString();
		
		assertThat(window1.getWindowFeatures(), is(windowFeatures));
	}

}
