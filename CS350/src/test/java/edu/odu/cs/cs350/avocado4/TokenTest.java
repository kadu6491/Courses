package edu.odu.cs.cs350.avocado4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit Tests for Token.java
 */
public class TokenTest {
	
	Token tokenTest;
	Token tokenTest2;
	Token tokenTestClassifier;
	String generateTokenList;
	String generateTokenList2;
	String featuresString;

	@Before
	public void setUp() throws Exception {
		tokenTest = new Token("John");
		tokenTest2 = new Token("John", "start");
		generateTokenList = "<NER>This is a cool test, John Smith.</NER>";
		generateTokenList2 = "This is a cool test, John Smith.";
		featuresString = "false,false,false,false,false,false,false,null,OTHER,false,false,false,false,false,false,false";	
	}
	

	@Test
	/**
	 * Covers the Unit Test for Token(String name) method.
	 */
	public void testTokenConstructor() {
		assertThat(tokenTest.getName(), is("John"));
		assertNull(tokenTest.getClassification());
		assertThat(tokenTest.getFeatures(), is(notNullValue()));
		assertThat(tokenTest.getFeaturesString(), is(featuresString));
		assertNull(tokenTest.getPosition());
	}
	
	@Test
	/**
	 * Covers the Unit Test for Token(String name, String classification) method.
	 */
	public void testTokenSecondConstructor() {
		assertThat(tokenTest2.getName(), is("John"));
		assertThat(tokenTest2.getClassification(), is("start"));
		assertThat(tokenTest2.getFeatures(), is(notNullValue()));
		assertThat(tokenTest2.getFeaturesString(), is(featuresString));
		assertNull(tokenTest2.getPosition());
	}
	
	@Test
	/**
	 * Covers the Unit Test for setName(String s) method.
	 */
	public void testSetName() {
		tokenTest.setName("Jack");
		
		assertThat(tokenTest.getName(), is("Jack"));
		assertNull(tokenTest.getClassification());
		assertThat(tokenTest.getFeatures(), is(notNullValue()));
		assertThat(tokenTest.getFeaturesString(), is(featuresString));
		assertNull(tokenTest.getPosition());
	}
	
	@Test
	/**
	 * Covers the Unit Test for setClassification(String c) method.
	 */
	public void testSetClassification() {
		tokenTest.setClassification("beginning");
		
		assertThat(tokenTest.getName(), is("John"));
		assertThat(tokenTest.getClassification(), is("beginning"));
		assertThat(tokenTest.getFeatures(), is(notNullValue()));
		assertThat(tokenTest.getFeaturesString(), is(featuresString));
		assertNull(tokenTest.getPosition());
	}

	@Test
	/**
	 * Covers the Unit Test for createTokenList(String s) method for strings containing NER tags.
	 */
	public void testCreateTokenListWithNER() {
		String s = generateTokenList;
		List<Token> generatedTokens = Token.createTokenList(generateTokenList);
		
		assertThat(generatedTokens.get(0).getName(), is("<NER>"));
		assertNull(generatedTokens.get(0).getClassification());
		assertThat(generatedTokens.get(0).getPosition(), is(0));
		assertThat(generatedTokens.get(1).getName(), is("This"));
		assertNull(generatedTokens.get(1).getClassification());
		assertThat(generatedTokens.get(1).getPosition(), is(1));
		assertThat(generatedTokens.get(2).getName(), is("is"));
		assertNull(generatedTokens.get(2).getClassification());
		assertThat(generatedTokens.get(2).getPosition(), is(3));
		assertThat(generatedTokens.get(3).getName(), is("a"));
		assertNull(generatedTokens.get(3).getClassification());
		assertThat(generatedTokens.get(3).getPosition(), is(5));
		assertThat(generatedTokens.get(4).getName(), is("cool"));
		assertNull(generatedTokens.get(4).getClassification());
		assertThat(generatedTokens.get(4).getPosition(), is(7));
		assertThat(generatedTokens.get(5).getName(), is("test"));
		assertNull(generatedTokens.get(5).getClassification());
		assertThat(generatedTokens.get(5).getPosition(), is(9));
		assertThat(generatedTokens.get(6).getName(), is(","));
		assertNull(generatedTokens.get(6).getClassification());
		assertThat(generatedTokens.get(6).getPosition(), is(10));
		assertThat(generatedTokens.get(7).getName(), is("John"));
		assertNull(generatedTokens.get(7).getClassification());
		assertThat(generatedTokens.get(7).getPosition(), is(12));
		assertThat(generatedTokens.get(8).getName(), is("Smith"));
		assertNull(generatedTokens.get(8).getClassification());
		assertThat(generatedTokens.get(8).getPosition(), is(14));
		assertThat(generatedTokens.get(9).getName(), is("."));
		assertNull(generatedTokens.get(9).getClassification());
		assertThat(generatedTokens.get(9).getPosition(), is(15));
		assertThat(generatedTokens.get(10).getName(), is("</NER>"));
		assertNull(generatedTokens.get(10).getClassification());
		assertThat(generatedTokens.get(10).getPosition(), is(16));
		
		assertThat(generateTokenList, is(s));
	}
	
	@Test
	/**
	 * Covers the Unit Test for createTokenList(String s) method for strings without NER tags.
	 */
	public void testCreateTokenListWithNoNER() {
		String s = generateTokenList2;
		List<Token> generatedTokens = Token.createTokenList(generateTokenList2);
		
		assertThat(generatedTokens.get(0).getName(), is("This"));
		assertNull(generatedTokens.get(0).getClassification());
		assertThat(generatedTokens.get(0).getPosition(), is(0));
		assertThat(generatedTokens.get(1).getName(), is("is"));
		assertNull(generatedTokens.get(1).getClassification());
		assertThat(generatedTokens.get(1).getPosition(), is(2));
		assertThat(generatedTokens.get(2).getName(), is("a"));
		assertNull(generatedTokens.get(2).getClassification());
		assertThat(generatedTokens.get(2).getPosition(), is(4));
		assertThat(generatedTokens.get(3).getName(), is("cool"));
		assertNull(generatedTokens.get(3).getClassification());
		assertThat(generatedTokens.get(3).getPosition(), is(6));
		assertThat(generatedTokens.get(4).getName(), is("test"));
		assertNull(generatedTokens.get(4).getClassification());
		assertThat(generatedTokens.get(4).getPosition(), is(8));
		assertThat(generatedTokens.get(5).getName(), is(","));
		assertNull(generatedTokens.get(5).getClassification());
		assertThat(generatedTokens.get(5).getPosition(), is(9));
		assertThat(generatedTokens.get(6).getName(), is("John"));
		assertNull(generatedTokens.get(6).getClassification());
		assertThat(generatedTokens.get(6).getPosition(), is(11));
		assertThat(generatedTokens.get(7).getName(), is("Smith"));
		assertNull(generatedTokens.get(7).getClassification());
		assertThat(generatedTokens.get(7).getPosition(), is(13));
		assertThat(generatedTokens.get(8).getName(), is("."));
		assertNull(generatedTokens.get(8).getClassification());
		assertThat(generatedTokens.get(8).getPosition(), is(14));
		
		assertThat(generateTokenList2, is(s));
	}
	
	@Test
	/**
	 * Covers the Unit Test for combineTokenList(List<Token> tokensList) method.
	 */
	public void testCombineTokenList() {
		List<Token> generatedTokens = Token.createTokenList(generateTokenList);
		String combined = Token.combineTokenList(generatedTokens);
		assertThat(combined, is("<NER>This is a cool test, John Smith.</NER>"));
	}

	@Test
	/**
	 * Covers the Unit Test for placePERTags(List<Token> tokenList) method.
	 */
	public void testPlacePERTags() {
		List<Token> list = new ArrayList<Token>();
		Token token1 = new Token("John","start");
		Token token2 = new Token("Smith","end");
		Token token3 = new Token("Bob","both");
		String classification = token1.getClassification();
		FeatureSet fs = token1.getFeatures();
		String fsString = token1.getFeaturesString();
		
		list.add(token1);
		list.add(token2);
		list.add(token3);
		
		Token.placePERTags(list);
		
		assertThat(token1.getName(), is("<PER>John"));
		assertThat(token2.getName(), is("Smith</PER>"));
		assertThat(token3.getName(), is("<PER>Bob</PER>"));
		assertThat(token1.getClassification(), is(classification));
		assertNull(token1.getPosition());
		assertThat(token1.getFeatures(), is(fs));
		assertThat(token1.getFeaturesString(), is(fsString));
	}
	
	@Test
	/**
	 * Covers the Unit Test for setFeatures(FeatureSet fs) method.
	 */
	public void testSetFeatureSet() {
		Token token = new Token("Joe");
		token.setFeatures(new FeatureSet());
		
		assertThat(token.getName(), is("Joe"));
		assertNull(token.getClassification());
		
		//just checking if it's not null
		assertThat(token.getFeatures().getIsInDictionary(), is(false));
	}
	
	@Test
	/**
	 * Covers the Unit Test for getFeatures() method.
	 */
	public void testGetFeaturesString() {
		Token token = new Token("Joe");
		FeatureSet fs = token.getFeatures();
		String s = token.getFeaturesString();
		
		assertThat(token.getName(), is("Joe"));
		assertNull(token.getClassification());
		assertThat(token.getFeatures(), is(fs));
		assertNull(token.getPosition());
		
		assertThat(s, is(token.getFeatures().getIsInDictionary()
				+ "," + token.getFeatures().getIsInitial()
				+ "," + token.getFeatures().hasHonorific()
				+ "," + token.getFeatures().hasPrefix()
				+ "," + token.getFeatures().hasSuffix()
				+ "," + token.getFeatures().hasNonPersonalProperName()
				+ "," + token.getFeatures().hasStopList()
				+ "," + token.getFeatures().getPartOfSpeech()
				+ "," + token.getFeatures().getLexicalType()
				+ "," + token.getFeatures().getCommonFirstName()
				+ "," + token.getFeatures().getFirstName()
				+ "," + token.getFeatures().getCommonLastName()
				+ "," + token.getFeatures().getLastName()
				+ "," + token.getFeatures().getCityState()
				+ "," + token.getFeatures().getCountry()
				+ "," + token.getFeatures().getPlace()
				));
	}

	@Test
	/**
	 * Covers the Unit Test for findAll(List<Token> list, String str) method.
	 */
	public void testFindAll() {
		List<Token> tokenListTest = new ArrayList<Token>();
		tokenListTest.addAll(Arrays.asList(
				new Token("Hello"),
				new Token("this"),
				new Token("is"),
				new Token("a"),
				new Token("test")
				));
		
		List<Integer> ret = Token.findAll(tokenListTest, "test");
		assertThat(ret.size(), is(1));
		assertThat(ret.get(0), is(4));
		
		List<Token> tokenListTest2 = new ArrayList<Token>();
		
		tokenListTest2.addAll(Arrays.asList(
				new Token("Hello"),
				new Token("this"),
				new Token("is"),
				new Token("a"),
				new Token("test"),
				new Token("."),
				new Token("Hello"),
				new Token("Hello"),
				new Token("Hello"),
				new Token("test"),
				new Token(".")
				));
		
		List<Integer> ret2 = Token.findAll(tokenListTest2, "Hello");
		assertThat(ret2.size(), is(4));
		assertThat(ret2.get(0), is(0));
		assertThat(ret2.get(1), is(6));
		assertThat(ret2.get(2), is(7));
		assertThat(ret2.get(3), is(8));
		
		List<Token> tokenListTest3 = new ArrayList<Token>();
		Token t = new Token("Hello");
		tokenListTest3.add(t);
		FeatureSet fs = t.getFeatures();
		
		List<Integer> ret3 = Token.findAll(tokenListTest3, "Hello");
		assertThat(ret3.size(), is(1));
		assertThat(ret3.get(0), is(0));
		assertThat(t.getName(), is("Hello"));
		assertNull(t.getClassification());
		assertNull(t.getPosition());
		assertThat(t.getFeatures(), is(fs));
	}
	
	@Test
	/**
	 * Covers the Unit Test for compareTo(Token t) method.
	 */
	public void testCompareTo() {
		Token token1 = new Token("John");
		Token token2 = new Token("Smith");
		Token token3 = new Token("john");
		Token token4 = new Token("john");
		
		assertThat(token1.compareTo(token2), is(-9));
		assertThat(token2.compareTo(token3), is(9));
		assertThat(token1.compareTo(token3), is(0));
		assertThat(token3.compareTo(token4), is(0));
	}
}
