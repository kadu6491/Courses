package edu.odu.cs.cs350.avocado4;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit Tests for Cataloger.java
 */
public class CatalogerTest {

	List<String> splitBlocks;
	Token test;
	
	String startingStringNERName;
	String startingStringNERNoName;
	String startingStringNoNERName;	
	String startingStringNoNERNoName;
	
	@Before
	public void setUp() {
		splitBlocks = new ArrayList<String>();
		startingStringNERName = "<NER>Hello, my name is Joe</NER>";
		startingStringNERNoName = "<NER>Hello, I have no name</NER>";
		startingStringNoNERName = "Hello, my name is Joe";
		startingStringNoNERNoName = "Hello, I have no name";
		test = new Token("test");
	}

	@Test
	/**This function also has an associated integration test
	 * The integration test supplies input and tests for expected output,
	 * so these were not included in the below unit tests.
	 */
	public void testCatalogue() {	
		splitBlocks.add(startingStringNERName);
		splitBlocks.add(startingStringNoNERName);
		splitBlocks.add(startingStringNERNoName);
		splitBlocks.add(startingStringNoNERNoName);
		
		int originalSize = splitBlocks.size();
		
		List<String> results = new ArrayList<String>();
		results = Cataloger.catalogue(splitBlocks);
		
		assertThat(results, is(notNullValue()));
		assertThat(results.size(), is(originalSize));
	}

	@Test
	/**
	 * 
	 */
	public void testCheckCapitalization()
	{
		List<Token> list = new ArrayList<Token>();
		
		Token t = new Token("Bird");
		Token t2 = new Token("BIRD");
		Token t3 = new Token("bird");

		list.add(t);
		list.add(t2);
		list.add(t3);
		Cataloger.checkCapitalization(list);
		
		test.getFeatures().setLexicalType(String.valueOf(Lexical.CAPITAL));
		String s = test.getFeaturesString();
		assertThat(t.getName(), is("Bird"));
		assertThat(t.getFeatures().getLexicalType(), is(String.valueOf(Lexical.CAPITAL)));
		assertNull(t.getPosition());
		assertNull(t.getClassification());
		assertThat(t.getFeaturesString(), is(s));
		
		test.getFeatures().setLexicalType(String.valueOf(Lexical.ALLCAPS));
		String s2 = test.getFeaturesString();
		assertThat(t2.getName(), is("BIRD"));
		assertThat(t2.getFeatures().getLexicalType(), is(String.valueOf(Lexical.ALLCAPS)));
		assertNull(t2.getPosition());
		assertNull(t2.getClassification());
		assertThat(t2.getFeaturesString(), is(s2));
		
		test.getFeatures().setLexicalType(String.valueOf(Lexical.OTHER));
		String s3 = test.getFeaturesString();
		assertThat(t3.getName(), is("bird"));
		assertThat(t3.getFeatures().getLexicalType(), is(String.valueOf(Lexical.OTHER)));
		assertNull(t3.getPosition());
		assertNull(t3.getClassification());
		assertThat(t3.getFeaturesString(), is(s3));
	}

	@Test
	public void testCheckDictionary() {
		List<Token> list = new ArrayList<Token>();
		Token t = new Token("bird");
		list.add(t);
		Cataloger.checkDictionary(list);
		
		test.getFeatures().setIsInDictionary(true);
		String s = test.getFeaturesString();
		assertThat(t.getFeatures().getIsInDictionary(), is(true));
		assertTrue(t.getName().equals("bird"));
		assertNull(t.getClassification());
		assertNull(t.getPosition());
		assertThat(t.getFeaturesString(), is(s));
	}

	
	@Test
	public void testCheckInitial() {
		List<Token> list = new ArrayList<Token>();
		Token t = new Token("A");
		Token t2 = new Token("b");
		Token t3 = new Token("Cat");
		list.add(t);
		list.add(t2);
		list.add(t3);
		Cataloger.checkInitial(list);
		
		test.getFeatures().setIsInitial(true);
		test.getFeatures().setLexicalType(String.valueOf(Lexical.CAPLETTER));
		String s = test.getFeaturesString();
		
		assertThat(t.getFeatures().getIsInitial(), is(true));
		assertThat(t.getFeatures().getLexicalType(), is(String.valueOf(Lexical.CAPLETTER)));
		assertThat(t2.getFeatures().getIsInitial(), is(false));
		assertThat(t2.getFeatures().getLexicalType(), is(String.valueOf(Lexical.OTHER)));
		assertThat(t3.getFeatures().getIsInitial(), is(false));
		assertThat(t3.getFeatures().getLexicalType(), is(String.valueOf(Lexical.OTHER)));
		
		assertTrue(t.getName().equals("A"));
		assertNull(t.getClassification());
		assertNull(t.getPosition());
		assertThat(t.getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the honorific attribute in FeatureSet.
	 */
	public void testCheckHonorific() {
		Token t = new Token("Major");
		Cataloger.checkHonorific(t);
		
		test.getFeatures().setHonorific(true);
		String s = test.getFeaturesString();
		assertThat(t.getFeatures().hasHonorific(), is(true));
		assertTrue(t.getName().equals("Major"));
		assertNull(t.getClassification());
		assertNull(t.getPosition());
		assertThat(t.getFeaturesString(), is(s));
		
		Token t2 = new Token("Dr");
		Cataloger.checkHonorific(t2);
		assertThat(t2.getFeatures().hasHonorific(), is(true));
		assertTrue(t2.getName().equals("Dr"));
		assertNull(t2.getClassification());
		assertNull(t2.getPosition());
		assertThat(t2.getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the prefix attribute in FeatureSet.
	 */
	public void testCheckPrefix() {
		Token t = new Token("el");
		Cataloger.checkPrefix(t);
		test.getFeatures().setPrefix(true);
		String s = test.getFeaturesString();
		
		assertThat(t.getFeatures().hasPrefix(), is(true));
		assertTrue(t.getName().equals("el"));
		assertNull(t.getClassification());
		assertNull(t.getPosition());
		assertThat(t.getFeaturesString(), is(s));
		
		Token t2 = new Token("babu");
		Cataloger.checkPrefix(t2);
		assertThat(t2.getFeatures().hasPrefix(), is(true));
		assertTrue(t2.getName().equals("babu"));
		assertNull(t2.getClassification());
		assertNull(t2.getPosition());
		assertThat(t2.getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the suffix attribute in FeatureSet.
	 */
	public void testCheckSuffix() {
		Token t = new Token("Jr");
		Cataloger.checkSuffix(t);
		test.getFeatures().setSuffix(true);
		String s = test.getFeaturesString();
		
		assertThat(t.getFeatures().hasSuffix(), is(true));
		assertTrue(t.getName().equals("Jr"));
		assertNull(t.getClassification());
		assertNull(t.getPosition());
		assertThat(t.getFeaturesString(), is(s));
		
		Token t2 = new Token("III");
		Cataloger.checkSuffix(t2);
		assertThat(t.getFeatures().hasSuffix(), is(true));
		assertTrue(t2.getName().equals("III"));
		assertNull(t2.getClassification());
		assertNull(t2.getPosition());
		assertThat(t2.getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the nonPersonalProperName method
	 */
	public void testFilterNonPersonalProperNames() {
		
		test.getFeatures().setNonPersonalProperName(true);
		String s = test.getFeaturesString();
		Token t = new Token("blvd");
		Cataloger.checkNonPersonalProperName(t);
		assertThat(t.getFeatures().hasNonPersonalProperName(), is(true));
		assertTrue(t.getName().equals("blvd"));
		assertNull(t.getClassification());
		assertNull(t.getPosition());
		assertThat(t.getFeaturesString(), is(s));
		
		
		Token t2 = new Token("road");
		Cataloger.checkNonPersonalProperName(t2);
		assertThat(t2.getFeatures().hasNonPersonalProperName(), is(true));
		assertTrue(t2.getName().equals("road"));
		assertNull(t2.getClassification());
		assertNull(t2.getPosition());
		assertThat(t2.getFeaturesString(), is(s));
	}
	
	
	@Test
	/**
	 * Tests the checkCitiesStates method
	 */
	public void testCheckCitiesStates() {
		List<Token> testCheckCitiesStates = Token.createTokenList("Hello. This is a test from John Smith from Hope, AR. I hope this works.");
		
		Cataloger.checkCitiesStates(testCheckCitiesStates);
		
		assertThat(testCheckCitiesStates.get(0).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(1).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(2).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(3).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(4).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(5).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(6).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(7).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(8).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(9).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(10).getFeatures().getCityState(), is(true));
		assertThat(testCheckCitiesStates.get(11).getFeatures().getCityState(), is(true));
		assertThat(testCheckCitiesStates.get(12).getFeatures().getCityState(), is(true));
		assertThat(testCheckCitiesStates.get(13).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(14).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(15).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(16).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(17).getFeatures().getCityState(), is(false));
		assertThat(testCheckCitiesStates.get(18).getFeatures().getCityState(), is(false));
		
		String s = test.getFeaturesString();
		
		assertTrue(testCheckCitiesStates.get(0).getName().equals("<NER>"));
		assertNull(testCheckCitiesStates.get(0).getClassification());
		assertThat(testCheckCitiesStates.get(0).getPosition(), is(0));
		assertThat(testCheckCitiesStates.get(0).getFeaturesString(), is(s));
		
		test.getFeatures().setCityState(true);
		s = test.getFeaturesString();
		
		assertTrue(testCheckCitiesStates.get(11).getName().equals("Hope"));
		assertNull(testCheckCitiesStates.get(11).getClassification());
		assertThat(testCheckCitiesStates.get(11).getPosition(), is(20));
		assertThat(testCheckCitiesStates.get(11).getFeaturesString(), is(s));
		
		
	}
	
	@Test
	/**
	 * Tests the checkCountries method
	 */
	public void testCheckCountries() {
		List<Token> testCheckCountries = Token.createTokenList("<NER>Hello my name is Bob from the United Kingdom of Great Britain and Northern Ireland! We are a United people!</NER>");
		
		Cataloger.checkCountries(testCheckCountries);
		
		assertThat(testCheckCountries.get(0).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(1).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(2).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(3).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(4).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(5).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(6).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(7).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(8).getFeatures().getCountry(), is(true));
		assertThat(testCheckCountries.get(9).getFeatures().getCountry(), is(true));
		assertThat(testCheckCountries.get(10).getFeatures().getCountry(), is(true));
		assertThat(testCheckCountries.get(11).getFeatures().getCountry(), is(true));
		assertThat(testCheckCountries.get(12).getFeatures().getCountry(), is(true));
		assertThat(testCheckCountries.get(13).getFeatures().getCountry(), is(true));
		assertThat(testCheckCountries.get(14).getFeatures().getCountry(), is(true));
		assertThat(testCheckCountries.get(15).getFeatures().getCountry(), is(true));
		assertThat(testCheckCountries.get(16).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(17).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(18).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(19).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(20).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(21).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(22).getFeatures().getCountry(), is(false));
		assertThat(testCheckCountries.get(23).getFeatures().getCountry(), is(false));
	
		String s = test.getFeaturesString();
		
		assertTrue(testCheckCountries.get(0).getName().equals("<NER>"));
		assertNull(testCheckCountries.get(0).getClassification());
		assertThat(testCheckCountries.get(0).getPosition(), is(0));
		assertThat(testCheckCountries.get(0).getFeaturesString(), is(s));
		
		test.getFeatures().setCountry(true);
		s = test.getFeaturesString();
		
		assertTrue(testCheckCountries.get(8).getName().equals("United"));
		assertNull(testCheckCountries.get(8).getClassification());
		assertThat(testCheckCountries.get(8).getPosition(), is(15));
		assertThat(testCheckCountries.get(8).getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the checkPunctuation method
	 */
	public void testCheckPunctuation() {
		List<Token> testPunct = new ArrayList<>(
			Arrays.asList(
				new Token("<NER>"), new Token("Hello"), new Token("my"), new Token("name"), new Token("is"), new Token("Bob"), new Token("."),
				new Token("This"), new Token("is"), new Token("a"), new Token("punctuation"), new Token("test"), new Token("!"),
				new Token("["), new Token("!"), new Token("\""), new Token("#"), new Token("$"), new Token("'"), new Token("&"),
				new Token("?"), new Token("\\"), new Token("}"), new Token("</NER>")
			)
		);
		
		Cataloger.checkPunctuation(testPunct);
		
		String s = test.getFeaturesString();
		
		assertTrue(testPunct.get(0).getName().equals("<NER>"));
		assertNull(testPunct.get(0).getClassification());
		assertNull(testPunct.get(0).getPosition());
		assertThat(testPunct.get(0).getFeaturesString(), is(s));
		
		test.getFeatures().setLexicalType(String.valueOf(Lexical.PUNCTUATION.toString()));
		s = test.getFeaturesString();
		
		assertTrue(testPunct.get(6).getName().equals("."));
		assertNull(testPunct.get(6).getClassification());
		assertNull(testPunct.get(6).getPosition());
		assertThat(testPunct.get(6).getFeaturesString(), is(s));
		
		assertThat(testPunct.get(0).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testPunct.get(1).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testPunct.get(2).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testPunct.get(3).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testPunct.get(4).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testPunct.get(5).getFeatures().getLexicalType(), is("OTHER"));
		
		assertThat(testPunct.get(6).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		
		assertThat(testPunct.get(7).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testPunct.get(8).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testPunct.get(9).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testPunct.get(10).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testPunct.get(11).getFeatures().getLexicalType(), is("OTHER"));
		
		assertThat(testPunct.get(12).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(13).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(14).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(15).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(16).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(17).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(18).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(19).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(20).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(21).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		assertThat(testPunct.get(22).getFeatures().getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		
		assertThat(testPunct.get(11).getFeatures().getLexicalType(), is("OTHER"));
	}
	
	@Test
	/**
	 * Tests the checkNumber() method
	 */
	public void testCheckNumber() {
		List<Token> testNum = new ArrayList<>(
			Arrays.asList(
				new Token("4"), new Token("342758904235723409857234058"), new Token("239129"), new Token("1234d"), new Token("d213"), new Token("-1"),
				new Token("2409"), new Token("abc123")
			)
		);
		
		Cataloger.checkNumber(testNum);
		
		assertThat(testNum.get(0).getFeatures().getLexicalType(), is(Lexical.NUMBER.toString()));
		assertThat(testNum.get(1).getFeatures().getLexicalType(), is(Lexical.NUMBER.toString()));
		assertThat(testNum.get(2).getFeatures().getLexicalType(), is(Lexical.NUMBER.toString()));
		assertThat(testNum.get(3).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNum.get(4).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNum.get(5).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNum.get(6).getFeatures().getLexicalType(), is(Lexical.NUMBER.toString()));
		assertThat(testNum.get(7).getFeatures().getLexicalType(), is("OTHER"));
		
		String s = test.getFeaturesString();
		
		assertTrue(testNum.get(3).getName().equals("1234d"));
		assertNull(testNum.get(3).getClassification());
		assertNull(testNum.get(3).getPosition());
		assertThat(testNum.get(3).getFeaturesString(), is(s));
		
		test.getFeatures().setLexicalType(String.valueOf(Lexical.NUMBER));
		s = test.getFeaturesString();
		
		assertTrue(testNum.get(0).getName().equals("4"));
		assertNull(testNum.get(0).getClassification());
		assertNull(testNum.get(0).getPosition());
		assertThat(testNum.get(0).getFeaturesString(), is(s));
		
	}
	
	
	@Test
	/**
	 * Tests the checkNewLine method
	 */
	public void testCheckNewLine() {
		List<Token> testNewLine = new ArrayList<>(
			Arrays.asList(
				new Token("<NER>"), new Token("Hello"), new Token("!"), new Token("\n"), new Token("Did"), new Token("it"),
				new Token("work"), new Token("?"), new Token("\n"), new Token("</NER>")
			)
		);
		
		Cataloger.checkNewLine(testNewLine);
		
		assertThat(testNewLine.get(0).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNewLine.get(1).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNewLine.get(2).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNewLine.get(3).getFeatures().getLexicalType(), is(Lexical.NEW_LINE.toString()));
		assertThat(testNewLine.get(4).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNewLine.get(5).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNewLine.get(6).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNewLine.get(7).getFeatures().getLexicalType(), is("OTHER"));
		assertThat(testNewLine.get(8).getFeatures().getLexicalType(), is(Lexical.NEW_LINE.toString()));
		assertThat(testNewLine.get(7).getFeatures().getLexicalType(), is("OTHER"));
		
		String s = test.getFeaturesString();
		
		assertTrue(testNewLine.get(0).getName().equals("<NER>"));
		assertNull(testNewLine.get(0).getClassification());
		assertNull(testNewLine.get(0).getPosition());
		assertThat(testNewLine.get(0).getFeaturesString(), is(s));
		
		test.getFeatures().setLexicalType(String.valueOf(Lexical.NEW_LINE));
		s = test.getFeaturesString();
		
		assertTrue(testNewLine.get(3).getName().equals("\n"));
		assertNull(testNewLine.get(3).getClassification());
		assertNull(testNewLine.get(3).getPosition());
		assertThat(testNewLine.get(3).getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the partOfSpeech method
	 */
	public void testPartOfSpeech() {
		List<Token> testPartOfSpeech = new ArrayList<>(
			Arrays.asList(
				new Token("<NER>"), new Token("Hello"), new Token("a"), new Token("."), new Token("and"), new Token("but"), new Token("or"), new Token("nor"),
				new Token("for"), new Token("so"), new Token("yet"), new Token("an"), new Token("the"), new Token("-"), new Token(","),
				new Token("</NER>")
			)
		);
		
		Cataloger.partOfSpeech(testPartOfSpeech);
		
		assertThat(testPartOfSpeech.get(0).getFeatures().getPartOfSpeech(), is(PartOfSpeech.OTHER.toString()));
		assertThat(testPartOfSpeech.get(1).getFeatures().getPartOfSpeech(), is(PartOfSpeech.OTHER.toString()));
		assertThat(testPartOfSpeech.get(2).getFeatures().getPartOfSpeech(), is(PartOfSpeech.ARTICLE.toString()));
		assertThat(testPartOfSpeech.get(3).getFeatures().getPartOfSpeech(), is(PartOfSpeech.PERIOD.toString()));
		assertThat(testPartOfSpeech.get(4).getFeatures().getPartOfSpeech(), is(PartOfSpeech.CONJUNCTION.toString()));
		assertThat(testPartOfSpeech.get(5).getFeatures().getPartOfSpeech(), is(PartOfSpeech.CONJUNCTION.toString()));
		assertThat(testPartOfSpeech.get(6).getFeatures().getPartOfSpeech(), is(PartOfSpeech.CONJUNCTION.toString()));
		assertThat(testPartOfSpeech.get(7).getFeatures().getPartOfSpeech(), is(PartOfSpeech.CONJUNCTION.toString()));
		assertThat(testPartOfSpeech.get(8).getFeatures().getPartOfSpeech(), is(PartOfSpeech.CONJUNCTION.toString()));
		assertThat(testPartOfSpeech.get(9).getFeatures().getPartOfSpeech(), is(PartOfSpeech.CONJUNCTION.toString()));
		assertThat(testPartOfSpeech.get(10).getFeatures().getPartOfSpeech(), is(PartOfSpeech.CONJUNCTION.toString()));
		assertThat(testPartOfSpeech.get(11).getFeatures().getPartOfSpeech(), is(PartOfSpeech.ARTICLE.toString()));
		assertThat(testPartOfSpeech.get(12).getFeatures().getPartOfSpeech(), is(PartOfSpeech.ARTICLE.toString()));
		assertThat(testPartOfSpeech.get(13).getFeatures().getPartOfSpeech(), is(PartOfSpeech.HYPHEN.toString()));
		assertThat(testPartOfSpeech.get(14).getFeatures().getPartOfSpeech(), is(PartOfSpeech.COMMA.toString()));
		assertThat(testPartOfSpeech.get(15).getFeatures().getPartOfSpeech(), is(PartOfSpeech.OTHER.toString()));
		
		test.getFeatures().setPartOfSpeech(String.valueOf(PartOfSpeech.OTHER));
		String s = test.getFeaturesString();
		
		assertTrue(testPartOfSpeech.get(0).getName().equals("<NER>"));
		assertNull(testPartOfSpeech.get(0).getClassification());
		assertNull(testPartOfSpeech.get(0).getPosition());
		assertThat(testPartOfSpeech.get(0).getFeaturesString(), is(s));
		
		test.getFeatures().setPartOfSpeech(String.valueOf(PartOfSpeech.ARTICLE));
		s = test.getFeaturesString();
		
		assertTrue(testPartOfSpeech.get(2).getName().equals("a"));
		assertNull(testPartOfSpeech.get(2).getClassification());
		assertNull(testPartOfSpeech.get(2).getPosition());
		assertThat(testPartOfSpeech.get(2).getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the checkStopList method
	 */
	public void testCheckStopList() {
		Token t1 = new Token("Hello");
		Token t2 = new Token("John");
		Token t3 = new Token("also");
		Token t4 = new Token("your");
		
		Cataloger.checkStopList(t1);
		Cataloger.checkStopList(t2);
		Cataloger.checkStopList(t3);
		Cataloger.checkStopList(t4);
		
		assertThat(t1.getFeatures().hasStopList(), is(false));
		assertThat(t2.getFeatures().hasStopList(), is(false));
		assertThat(t3.getFeatures().hasStopList(), is(true));
		assertThat(t4.getFeatures().hasStopList(), is(true));
		
		String s = test.getFeaturesString();
		
		assertTrue(t1.getName().equals("Hello"));
		assertNull(t1.getClassification());
		assertNull(t1.getPosition());
		assertThat(t1.getFeaturesString(), is(s));
		
		test.getFeatures().setHasStopList(true);
		s = test.getFeaturesString();
		
		assertTrue(t3.getName().equals("also"));
		assertNull(t3.getClassification());
		assertNull(t3.getPosition());
		assertThat(t3.getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the checkFirstNames method for special list names
	 */
	public void testCheckFirstNames() {
		List<Token> testFirstNames = new ArrayList<>(
			Arrays.asList(
				new Token("<NER>"), //0
				new Token("Their"), //1
				new Token("colleagues"), //2
				new Token("names"), //3
				new Token("are"), //4 
				new Token("Ajoy"), //5 
				new Token(","), //6 
				new Token("Akihiro"), //7
				new Token(","), //8
				new Token("Nelida"), //9
				new Token(","), //10
				new Token("and"), //11
				new Token("Zuxiong"), //12
				new Token("."), //13
				new Token("a"), //14
				new Token("</NER>") //15
			)
		);
		
		Cataloger.checkFirstNames(testFirstNames);
		
		assertThat(testFirstNames.get(0).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(1).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(2).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(3).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(4).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(5).getFeatures().getFirstName(), is(true));
		assertThat(testFirstNames.get(6).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(7).getFeatures().getFirstName(), is(true));
		assertThat(testFirstNames.get(8).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(9).getFeatures().getFirstName(), is(true));
		assertThat(testFirstNames.get(10).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(11).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(12).getFeatures().getFirstName(), is(true));
		assertThat(testFirstNames.get(13).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(14).getFeatures().getFirstName(), is(false));
		assertThat(testFirstNames.get(15).getFeatures().getFirstName(), is(false));
		
		String s = test.getFeaturesString();
		
		assertTrue(testFirstNames.get(0).getName().equals("<NER>"));
		assertNull(testFirstNames.get(0).getClassification());
		assertNull(testFirstNames.get(0).getPosition());
		assertThat(testFirstNames.get(0).getFeaturesString(), is(s));
		
		test.getFeatures().setFirstName(true);
		s = test.getFeaturesString();
		
		assertTrue(testFirstNames.get(5).getName().equals("Ajoy"));
		assertNull(testFirstNames.get(5).getClassification());
		assertNull(testFirstNames.get(5).getPosition());
		assertThat(testFirstNames.get(5).getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the checkFirstNames method for Common First Names
	 */
	public void testCheckCommonFirstNames() {
		List<Token> testFirstNames = new ArrayList<>(
			Arrays.asList(
				new Token("<NER>"), //0
				new Token("Their"), //1
				new Token("colleagues"), //2
				new Token("names"), //3
				new Token("include"), //4 
				new Token("Tanika"), //5 
				new Token(","), //6 
				new Token("Garbage"), //7
				new Token(","), //8
				new Token("Patricia"), //9
				new Token(","), //10
				new Token("Garbage"), //11
				new Token("."), //12
				new Token("a"), //13
				new Token("</NER>") //14
			)
		);
		
		Cataloger.checkFirstNames(testFirstNames);
		
		assertThat(testFirstNames.get(0).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(1).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(2).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(3).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(4).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(5).getFeatures().getCommonFirstName(), is(true));
		assertThat(testFirstNames.get(6).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(7).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(8).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(9).getFeatures().getCommonFirstName(), is(true));
		assertThat(testFirstNames.get(10).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(11).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(12).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(13).getFeatures().getCommonFirstName(), is(false));
		assertThat(testFirstNames.get(14).getFeatures().getCommonFirstName(), is(false));
		
		String s = test.getFeaturesString();
		
		assertTrue(testFirstNames.get(0).getName().equals("<NER>"));
		assertNull(testFirstNames.get(0).getClassification());
		assertNull(testFirstNames.get(0).getPosition());
		assertThat(testFirstNames.get(0).getFeaturesString(), is(s));
		
		test.getFeatures().setCommonFirstName(true);
		test.getFeatures().setFirstName(true);
		s = test.getFeaturesString();
		
		assertTrue(testFirstNames.get(5).getName().equals("Tanika"));
		assertNull(testFirstNames.get(5).getClassification());
		assertNull(testFirstNames.get(5).getPosition());
		assertThat(testFirstNames.get(5).getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the checkLastNames method with the LastNames wordlist
	 */
	public void testCheckLastNames() {
		List<Token> testLastNames = new ArrayList<>(
				Arrays.asList(
					new Token("<NER>"), //0
					new Token("Their"), //1
					new Token("colleagues"), //2
					new Token("names"), //3
					new Token("include"), //4 
					new Token("aalderink"), //5 
					new Token("and"), //6 
					new Token("mcgoff"), //7
					new Token("</NER>") //8
				)
			);
			
			Cataloger.checkLastNames(testLastNames);
			
			assertThat(testLastNames.get(0).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(1).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(2).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(3).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(4).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(5).getFeatures().getLastName(), is(true));
			assertThat(testLastNames.get(6).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(7).getFeatures().getLastName(), is(true));
			assertThat(testLastNames.get(8).getFeatures().getLastName(), is(false));
			
			String s = test.getFeaturesString();
			
			assertTrue(testLastNames.get(0).getName().equals("<NER>"));
			assertNull(testLastNames.get(0).getClassification());
			assertNull(testLastNames.get(0).getPosition());
			assertThat(testLastNames.get(0).getFeaturesString(), is(s));
			
			test.getFeatures().setLastName(true);
			s = test.getFeaturesString();
			
			assertTrue(testLastNames.get(5).getName().equals("aalderink"));
			assertNull(testLastNames.get(5).getClassification());
			assertNull(testLastNames.get(5).getPosition());
			assertThat(testLastNames.get(5).getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the checkLastNames method with the commonLastNames wordlist
	 */
	public void testCheckCommonLastNames() {
		List<Token> testLastNames = new ArrayList<>(
				Arrays.asList(
					new Token("<NER>"), //0
					new Token("Their"), //1
					new Token("colleagues"), //2
					new Token("names"), //3
					new Token("include"), //4 
					new Token("smith"), //5 
					new Token("and"), //6 
					new Token("clark"), //7
					new Token("</NER>") //8
				)
			);
			
			Cataloger.checkLastNames(testLastNames);
			
			assertThat(testLastNames.get(0).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(1).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(2).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(3).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(4).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(5).getFeatures().getLastName(), is(true));
			assertThat(testLastNames.get(6).getFeatures().getLastName(), is(false));
			assertThat(testLastNames.get(7).getFeatures().getLastName(), is(true));
			assertThat(testLastNames.get(8).getFeatures().getLastName(), is(false));
			
			String s = test.getFeaturesString();
			
			assertTrue(testLastNames.get(0).getName().equals("<NER>"));
			assertNull(testLastNames.get(0).getClassification());
			assertNull(testLastNames.get(0).getPosition());
			assertThat(testLastNames.get(0).getFeaturesString(), is(s));
			
			test.getFeatures().setCommonLastName(true);
			s = test.getFeaturesString();
			
			assertTrue(testLastNames.get(5).getName().equals("smith"));
			assertNull(testLastNames.get(5).getClassification());
			assertNull(testLastNames.get(5).getPosition());
			assertThat(testLastNames.get(5).getFeaturesString(), is(s));
	}
	
	@Test
	/**
	 * Tests the checkPlaces method
 	 */
	public void testCheckPlaces() {
		List<Token> testPlacesSentence = Token.createTokenList("Hopefully we can make it to Abiaca Creek. I like the Creek");
		
		Cataloger.checkPlaces(testPlacesSentence);
		
		assertThat(testPlacesSentence.get(0).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(1).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(2).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(3).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(4).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(5).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(6).getFeatures().getPlace(), is(true));
		assertThat(testPlacesSentence.get(7).getFeatures().getPlace(), is(true));
		assertThat(testPlacesSentence.get(8).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(9).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(10).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(11).getFeatures().getPlace(), is(false));
		assertThat(testPlacesSentence.get(12).getFeatures().getPlace(), is(false)); // shouldn't pick up JUST creek, since there's no lone creek in the file.
		
		String places = "Entering San Diego";
		List<Token> testPlacesIndividual = Token.createTokenList(places);
		/*Token t1 = new Token("Entering");
		Token t2 = new Token("San");
		Token t3 = new Token("Diego");
		testPlacesIndividual.add(t1);
		testPlacesIndividual.add(t2);
		testPlacesIndividual.add(t3);*/
		
		Cataloger.checkPlaces(testPlacesIndividual);
		
		assertThat(testPlacesIndividual.get(0).getFeatures().getPlace(), is(false));
		assertThat(testPlacesIndividual.get(1).getFeatures().getPlace(), is(true));
		assertThat(testPlacesIndividual.get(2).getFeatures().getPlace(), is(true));
		
		String s = test.getFeaturesString();
		
		assertTrue(testPlacesIndividual.get(0).getName().equals("Entering"));
		assertNull(testPlacesIndividual.get(0).getClassification());
		assertThat(testPlacesIndividual.get(0).getPosition(), is(0));
		assertThat(testPlacesIndividual.get(0).getFeaturesString(), is(s));
		
		test.getFeatures().setPlace(true);
		s = test.getFeaturesString();
		
		assertTrue(testPlacesIndividual.get(2).getName().equals("Diego"));
		assertNull(testPlacesIndividual.get(2).getClassification());
		assertThat(testPlacesIndividual.get(2).getPosition(), is(4));
		assertThat(testPlacesIndividual.get(2).getFeaturesString(), is(s));
	}
	
	
	@Test
	/**
	 * Tests the tokenizeWordLists method
	 */
	public void testTokenizeWordLists() {
		String test1 = "Hope, AR";
		String test2 = "<NER>Lake Minchumina, Alaska</NER>";
		String test3 = "Gallipolis Ferry, West Virginia";
		
		List<String> ret1 = Cataloger.tokenizeString(test1);
		List<String> ret2 = Cataloger.tokenizeString(test2);
		List<String> ret3 = Cataloger.tokenizeString(test3);
		
		assertThat(ret1.size(), is(3));
		assertThat(ret1.get(0), is("Hope"));
		assertThat(ret1.get(1), is(","));
		assertThat(ret1.get(2), is("AR"));
		
		assertThat(ret2.size(), is(6));
		assertThat(ret2.get(0), is("<NER>"));
		assertThat(ret2.get(1), is("Lake"));
		assertThat(ret2.get(2), is("Minchumina"));
		assertThat(ret2.get(3), is(","));
		assertThat(ret2.get(4), is("Alaska"));
		assertThat(ret2.get(5), is("</NER>"));
		
		assertThat(ret3.size(), is(5));
		assertThat(ret3.get(0), is("Gallipolis"));
		assertThat(ret3.get(1), is("Ferry"));
		assertThat(ret3.get(2), is(","));
		assertThat(ret3.get(3), is("West"));
		assertThat(ret3.get(4), is("Virginia"));
	}
	
	@Test
	/**
	 * Covers the Unit Test for tokenizedCompare
	 */
	public void testTokenizedCompare() {
		List<Token> exampleMarkPlaces = new ArrayList<>(
			Arrays.asList(
				new Token("I"),
				new Token("want"),
				new Token("to"),
				new Token("go"),
				new Token("to"),
				new Token("the"),
				new Token("United"),
				new Token("Kingdom"),
				new Token("or"),
				new Token("United"),
				new Token("States"),
				new Token(".")
			)
		);
		
		List<List<String>> tokenizedInput = new ArrayList<List<String>>(
			Arrays.asList(
				Arrays.asList("United", "Kingdom"),
				Arrays.asList("United", "States")
			)
		);
		
		String type = "country"; // arbitrary, used for setting feature set
		
		Cataloger.tokenizedCompare(exampleMarkPlaces, tokenizedInput, type);
		
		assertThat(exampleMarkPlaces.get(0).getFeatures().getCountry(), is(false));
		assertThat(exampleMarkPlaces.get(1).getFeatures().getCountry(), is(false));
		assertThat(exampleMarkPlaces.get(2).getFeatures().getCountry(), is(false));
		assertThat(exampleMarkPlaces.get(3).getFeatures().getCountry(), is(false));
		assertThat(exampleMarkPlaces.get(4).getFeatures().getCountry(), is(false));
		assertThat(exampleMarkPlaces.get(5).getFeatures().getCountry(), is(false));
		assertThat(exampleMarkPlaces.get(6).getFeatures().getCountry(), is(true));
		assertThat(exampleMarkPlaces.get(7).getFeatures().getCountry(), is(true));
		assertThat(exampleMarkPlaces.get(8).getFeatures().getCountry(), is(false));
		assertThat(exampleMarkPlaces.get(9).getFeatures().getCountry(), is(true));
		assertThat(exampleMarkPlaces.get(10).getFeatures().getCountry(), is(true));
		assertThat(exampleMarkPlaces.get(11).getFeatures().getCountry(), is(false));
	}
	
}
