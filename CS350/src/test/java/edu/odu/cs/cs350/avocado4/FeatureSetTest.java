package edu.odu.cs.cs350.avocado4;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Note:
 * Not sure if this needs a test, considering it's just getters 
 * and setters. However, I'll assume so since test driven development!
 * Doesn't hurt to have complete tests, and this sets that the behavior
 * for these methods should not change.
 * 
 * Unit Tests for FeatureSet.java
 */
public class FeatureSetTest {
	
	@Test
	/**
	 * Covers the Unit Test for FeatureSet() constructor.
	 */
	public void testFeatureSet() {
		FeatureSet featureSet = new FeatureSet();
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setLexicalType(String lexical)
	 */
	public void testSetLexicalType() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setLexicalType(Lexical.PUNCTUATION.toString());
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is(Lexical.PUNCTUATION.toString()));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setPartOfSpeech(String pos)
	 */
	public void testSetPartOfSpeech() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setPartOfSpeech(PartOfSpeech.ARTICLE.toString());
		
		assertThat(featureSet.getPartOfSpeech(), is(PartOfSpeech.ARTICLE.toString()));
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setIsInDictionary(boolean dictionary)
	 */
	public void testSetIsInDictionary() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setIsInDictionary(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(true));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setIsInitial(boolean initial)
	 */
	public void testSetIsInitial() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setIsInitial(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(true));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setHonorific(boolean hasHonorific) 
	 */
	public void testSetHonorific() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setHonorific(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(true));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setPrefix(boolean hasPrefix) 
	 */
	public void testSetPrefix() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setPrefix(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(true));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setSuffix(boolean hasSuffix) 
	 */
	public void testSetSuffix() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setSuffix(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(true));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setNonPersonalProperName(boolean hasNonPersonalProperName)
	 */
	public void testSetNonPersonalProperName() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setNonPersonalProperName(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(true));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setHasStopList(boolean hasStopList)
	 */
	public void testSetHasStopList() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setHasStopList(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(true));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setCommonFirstName(boolean b)
	 */
	public void testSetCommonFirstName() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setCommonFirstName(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(true));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setFirstName(boolean b)
	 */
	public void testSetFirstName() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setFirstName(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(true));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setCommonLastName(boolean b)
	 */
	public void testSetCommonLastName() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setCommonLastName(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(true));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setLastName(boolean b)
	 */
	public void testSetLastName() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setLastName(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(true));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setCityState(boolean b)
	 */
	public void testSetCityState() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setCityState(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(true));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setCountry(boolean b)
	 */
	public void testSetCountry() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setCountry(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(true));
		assertThat(featureSet.getPlace(), is(false));
	}
	
	@Test
	/**
	 * Covers setPlace(boolean b)
	 */
	public void testSetPlace() {
		FeatureSet featureSet = new FeatureSet();
		featureSet.setPlace(true);
		
		assertNull(featureSet.getPartOfSpeech());
		assertThat(featureSet.getLexicalType(), is("OTHER"));
		
		assertThat(featureSet.getIsInDictionary(), is(false));
		assertThat(featureSet.getIsInitial(), is(false));
		assertThat(featureSet.hasHonorific(), is(false));
		assertThat(featureSet.hasPrefix(), is(false));
		assertThat(featureSet.hasSuffix(), is(false));
		assertThat(featureSet.hasNonPersonalProperName(), is(false));
		assertThat(featureSet.hasStopList(), is(false));
		assertThat(featureSet.getCommonFirstName(), is(false));
		assertThat(featureSet.getFirstName(), is(false));
		assertThat(featureSet.getCommonLastName(), is(false));
		assertThat(featureSet.getLastName(), is(false));
		assertThat(featureSet.getCityState(), is(false));
		assertThat(featureSet.getCountry(), is(false));
		assertThat(featureSet.getPlace(), is(true));
	}
}
