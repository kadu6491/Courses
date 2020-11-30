package edu.odu.cs.cs350.avocado4;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LearningMachineTest {

	String trainingString;
	List<String> trainingSplitStrings;
	List<Token> list1;
	List<Token> list2;

	@Before
	public void setUp() {
		trainingString = "<NER>This is a test, <PER>Joe</PER></NER>\n<NER>Only a test</NER>";
		trainingSplitStrings = TextBlocks.parseTextBlocks(trainingString);
		list1 = Token.createTokenList(trainingSplitStrings.get(0));
		list2 = Token.createTokenList(trainingSplitStrings.get(1));
	}

	@Test
	/**
	 * Test of the train method
	 */
	public void testTrain() {
		String original = trainingString;
		LearningMachine.train(trainingString);
		assertThat(trainingString, is(original));
		//assert that the training data file is not empty
		//assert that the model file is not empty
		
	}

	@Test
	/**
	 * Test for the trainClassifyToken method
	 */
	public void testTrainClassifyToken() {
		LearningMachine.trainClassifyToken(list1);
		list1 = LearningMachine.removePERTags(list1);
		
		assertThat(list1.get(0).getName(), is("<NER>"));
		assertThat(list1.get(0).getClassification(), is("none"));
		assertThat(list1.get(1).getName(), is("This"));
		assertThat(list1.get(1).getClassification(), is("none"));
		assertThat(list1.get(2).getName(), is("is"));
		assertThat(list1.get(2).getClassification(), is("none"));
		assertThat(list1.get(3).getName(), is("a"));
		assertThat(list1.get(3).getClassification(), is("none"));
		assertThat(list1.get(4).getName(), is("test"));
		assertThat(list1.get(4).getClassification(), is("none"));
		assertThat(list1.get(5).getName(), is(","));
		assertThat(list1.get(5).getClassification(), is("none"));
		assertThat(list1.get(6).getName(), is("Joe"));
		assertThat(list1.get(6).getClassification(), is("both"));
		assertThat(list1.get(7).getName(), is("</NER>"));
		assertThat(list1.get(7).getClassification(), is("none"));
		

		LearningMachine.trainClassifyToken(list2);
		list2 = LearningMachine.removePERTags(list2);
		
		assertThat(list2.get(0).getName(), is("<NER>"));
		assertThat(list2.get(0).getClassification(), is("none"));
		assertThat(list2.get(1).getName(), is("Only"));
		assertThat(list2.get(1).getClassification(), is("none"));
		assertThat(list2.get(2).getName(), is("a"));
		assertThat(list2.get(2).getClassification(), is("none"));
		assertThat(list2.get(3).getName(), is("test"));
		assertThat(list2.get(3).getClassification(), is("none"));
		assertThat(list2.get(4).getName(), is("</NER>"));
		assertThat(list2.get(4).getClassification(), is("none"));
		
		Token test = new Token("testing");
		String s = test.getFeaturesString();
		assertThat(list1.get(0).getFeaturesString(), is(s));
		assertThat(list1.get(0).getPosition(), is(0));
		
		Token test2 = new Token("testing2");
		test2.setClassification("both");
		s = test2.getFeaturesString();
		assertThat(list1.get(6).getFeaturesString(), is(s));
		assertThat(list1.get(6).getPosition(), is(11));
	}

	@Test
	/**
	 * Test of the removePERTags method
	 */
	public void testRemovePERTags() {
		int originalSize = list1.size();
		List<Token> newList = LearningMachine.removePERTags(list1);
		Token test = new Token("test");
		String s = test.getFeaturesString();
		
		assertThat(newList.get(0).getName(), is("<NER>"));
		assertThat(newList.get(1).getName(), is("This"));
		assertThat(newList.get(2).getName(), is("is"));
		assertThat(newList.get(3).getName(), is("a"));
		assertThat(newList.get(4).getName(), is("test"));
		assertThat(newList.get(5).getName(), is(","));
		assertThat(newList.get(6).getName(), is("Joe"));
		assertThat(newList.get(7).getName(), is("</NER>"));
		
		assertThat(newList.size(), is(originalSize-2));
		assertNull(newList.get(0).getClassification());
		assertThat(newList.get(0).getPosition(), is(0));
		assertThat(newList.get(0).getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Test of the removeNERTags method
	 */
	public void testRemoveNERTags() {
		List<Token> newList = LearningMachine.removeNERTags(list1);
		
		assertThat(newList.get(0).getName(), is("This"));
		assertThat(newList.get(1).getName(), is("is"));
		assertThat(newList.get(2).getName(), is("a"));
		assertThat(newList.get(3).getName(), is("test"));
		assertThat(newList.get(4).getName(), is(","));
		assertThat(newList.get(5).getName(), is("Joe"));
	}

	@Test
	/**
	 * Test of the classifyTokens method
	 */
	public void testClassifyTokens() {
		List<Token> newList = LearningMachine.removePERTags(list1);
		LearningMachine.classifyTokens(list1);
		Token test = new Token("test");
		test.setClassification("none");
		String s = test.getFeaturesString();
		
		assertThat(newList.get(0).getName(), is("<NER>"));
		assertThat(newList.get(0).getClassification(), is("none"));
		assertThat(newList.get(1).getName(), is("This"));
		assertThat(newList.get(1).getClassification(), is("none"));
		assertThat(newList.get(2).getName(), is("is"));
		assertThat(newList.get(2).getClassification(), is("none"));
		assertThat(newList.get(3).getName(), is("a"));
		assertThat(newList.get(3).getClassification(), is("none"));
		assertThat(newList.get(4).getName(), is("test"));
		assertThat(newList.get(4).getClassification(), is("none"));
		assertThat(newList.get(5).getName(), is(","));
		assertThat(newList.get(5).getClassification(), is("none"));
		assertThat(newList.get(6).getName(), is("Joe"));
		assertThat(newList.get(6).getClassification(), is("both"));
		assertThat(newList.get(7).getName(), is("</NER>"));
		assertThat(newList.get(7).getClassification(), is("none"));
		
		assertThat(newList.size(), is(8));
		assertThat(newList.get(0).getClassification(), is(notNullValue()));
		assertThat(newList.get(0).getPosition(), is(0));
		assertThat(newList.get(0).getFeaturesString(), is(s));
		
	}

}
