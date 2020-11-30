package edu.odu.cs.cs350.avocado4;

import edu.odu.cs.extract.wordlists.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import java.lang.Character;
import java.text.SimpleDateFormat;

/**
 * Cataloger Class
 * 
 * Catalogs Token data based on WordLists, lexical data, part of speech, and several other attributes
 */
public class Cataloger {

	/**
	 * This method classifies each 'token' in a block of text as either a beginning or ending.
	 * It then places the PER tags around those classified tokens, combines the tokens back into a string, and returns.
	 * 
	 * @param splitBlocks This is the list of text blocks (wrapped in NER tags).
	 * @return splitBlocks This is the combined token list in String form
	 */
	public static List<String> catalogue(List<String> splitBlocks) {
		HashMap<String, Token> map = new HashMap<>();
		
		for (int i = 0; i < splitBlocks.size(); i++) {
			List<Token> tokensList = Token.createTokenList(splitBlocks.get(i));
			List<Token> newTokenList = new ArrayList<Token>();
			
			for (Token t : tokensList) {
				if(map.containsKey(t.getName())) {
					Token temp = map.get(t.getName());
					t.setFeatures(temp.getFeatures());
				}
				else {
					newTokenList.add(t);
				}
			}
			
			checkPunctuation(newTokenList);
			checkDictionary(newTokenList);
			checkCapitalization(newTokenList);
			checkInitial(newTokenList);
			partOfSpeech(newTokenList);
			checkNumber(newTokenList);
			checkFirstNames(newTokenList);
			checkLastNames(newTokenList);
			checkCitiesStates(newTokenList);
			checkCountries(newTokenList);
			checkPlaces(newTokenList);
			checkNewLine(newTokenList);
			
			for (Token tok : newTokenList) {
				//Functions only relevant to words/numbers
				if (tok.getName().matches("\\w")) {
					checkHonorific(tok);
					checkPrefix(tok);
					checkSuffix(tok);
					checkNonPersonalProperName(tok);
					checkStopList(tok);	
				}
				map.put(tok.getName(), tok);
			}
			
			LearningMachine.classifyTokens(tokensList);
			Token.placePERTags(tokensList);
			
			splitBlocks.set(i, Token.combineTokenList(tokensList));
		}
		
		return splitBlocks;
	}

	/**
	 * Updates the Token's feature list lexicalType value to an Lexical enumeration String
	 * value, or it will remain blank otherwise. Checks for all caps, or a capital letter 
	 * in a token list which will be passed to our machine learning model.
	 * 
	 * @param token List of tokens to be evaluated for new lines.
	 */
	public static void checkCapitalization(List<Token> token)
	{
		for(Token tk : token)
		{
			//checks for capitalized word
			if(tk.getName().matches("[A-Z][a-z]+"))
			{
				tk.getFeatures().setLexicalType(String.valueOf(Lexical.CAPITAL));
			}
			
			//checks for all caps word
			if(tk.getName().matches("^[A-Z]{2,}$")) 
			{
				tk.getFeatures().setLexicalType(String.valueOf(Lexical.ALLCAPS));
			}

		}
	}
	
	/**
	 * Updates the Token's feature list lexicalType value to an Lexical enumeration String
	 * value, or it will remain blank otherwise. Checks for punctuation in a token list which
	 * will be passed to our machine learning model.
	 * 
	 * @param token List of tokens to be evaluated for new lines.
	 */
	public static void checkPunctuation(List<Token> token) {
		for (Token tk : token) {
			//if (tk.getName().matches("[!\"#$%&'()*+,-./:;<=>?@\\[]^_`\\{|\\}~]")) {
			if (tk.getName().matches("[\\W]")) {
				tk.getFeatures().setLexicalType(String.valueOf(Lexical.PUNCTUATION));
			}
		}
	}
	
	/**
	 * Updates the Token's feature list lexicalType value to an Lexical enumeration String
	 * value, or it will remain blank otherwise. Checks for numbers in a token list which
	 * will be passed to our machine learning model.
	 * 
	 * @param token List of tokens to be evaluated for new lines.
	 */
	public static void checkNumber(List<Token> token) {
		for (Token tk : token) {
			if (tk.getName().matches("^\\d+$")) {
				tk.getFeatures().setLexicalType(String.valueOf(Lexical.NUMBER));
			}
		}
	}
	
	/**
	 * Updates the Token's feature list lexicalType value to an Lexical enumeration String
	 * value, or it will remain blank otherwise. Filters out common articles and conjunctions which
	 * will be passed to our machine learning model.
	 * 
	 * @param tokenList List of tokens to be evaluated for new lines.
	 */
	public static void checkNewLine(List<Token> tokenList) {
		for (Token tk : tokenList) {
			if(tk.getName().contains("\n"))
			{
				tk.getFeatures().setLexicalType(String.valueOf(Lexical.NEW_LINE));
			}
		}
	}

	/**
	 * Updates the Token's feature list partOfSpeech value to an PartOfSpeech enumeration String
	 * value, or it will remain blank otherwise. Filters out common articles and conjunctions which
	 * will be passed to our machine learning model.
	 * 
	 * @param tokenList List of tokens to be evaluated for articles and conjunctions.
	 */
	public static void partOfSpeech(List<Token> tokenList)
	{
		String[] article = {"a", "an", "the"};
		String[] conjunction = {"and", "but", "or", "nor", "for", "so", "yet"};

		for(Token tk : tokenList)
		{	
			if (Arrays.asList(article).contains(tk.getName())) {
				tk.getFeatures().setPartOfSpeech(String.valueOf(PartOfSpeech.ARTICLE));
			}
			else if (Arrays.asList(conjunction).contains(tk.getName())) {
				tk.getFeatures().setPartOfSpeech(String.valueOf(PartOfSpeech.CONJUNCTION));
			}
			
			else if (tk.getName().contains("."))
			{
				// FeatureSet set = new FeatureSet();
				tk.getFeatures().setPartOfSpeech(String.valueOf(PartOfSpeech.PERIOD));
			}

			else if (tk.getName().contains(","))
			{
				// FeatureSet set = new FeatureSet();
				tk.getFeatures().setPartOfSpeech(String.valueOf(PartOfSpeech.COMMA));
			}

			else if(tk.getName().contains("-"))
			{
				// FeatureSet set = new FeatureSet();
				tk.getFeatures().setPartOfSpeech(String.valueOf(PartOfSpeech.HYPHEN));
			}
			else {
				tk.getFeatures().setPartOfSpeech(String.valueOf(PartOfSpeech.OTHER));
			}
		}
	}
	
	/**
	 * This method imports a list of English dictionary words.
	 * The token passed is compared with the list, ignoring case, and updates the token's Feature Set
	 * 
	 * @param tokensList This is the token to be checked against the Dictionary.
	 */
	public static void checkDictionary(List<Token> tokensList) {
		List<Token> alphaList = new ArrayList<Token>();
		for (Token t : tokensList) {
			alphaList.add(t);
		}
		
		Collections.sort(alphaList);
		
		//get dictionary library
		Iterator<String> dictIt = WordLists.englishDictionary().iterator();
		
		int i = 0;
		
		while (dictIt.hasNext()) {
			String word = dictIt.next();
			
			while(i < alphaList.size()) {
				
				if (alphaList.get(i).getName().matches("\\w")) {
					i++;
				}
				
				//dictionary word is the same as token name
				else if(word.compareToIgnoreCase(alphaList.get(i).getName()) == 0) {
					alphaList.get(i).getFeatures().setIsInDictionary(true);
					i++;
				}
				
				//dictionary word is alphabetically greater than token name
				else if(word.compareToIgnoreCase(alphaList.get(i).getName()) > 0) {
					i++;
				}
				
				//dictionary word is alphabetically less than token name
				else {
					break;
				}
			}
		}
	}
	
	/**
	 * This method checks a token to see if it is both a single character and capitalized
	 * The token passed is compared with the list, ignoring case, and updates the token's Feature Set
	 * 
	 * @param tokenList This is the token to be checked as an initial
	 */
	public static void checkInitial(List<Token> tokenList) {
		//indicates whether the previous token was an initial
		Boolean prev = false;
		for (Token t : tokenList) {
			if (Character.isUpperCase(t.getName().charAt(0)) && t.getName().length() == 1) {
				t.getFeatures().setIsInitial(true);
				t.getFeatures().setLexicalType(String.valueOf(Lexical.CAPLETTER));
				prev = true;
			}
			else {
				if (prev && t.getName().equals(".")) {
					t.getFeatures().setIsInitial(true);
				}
				prev = false;
			}
		}
	}
	
	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common honorifics. This
	 * method will update the Token's feature list honorific value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param t Token to be checked for honorific
	 */
	public static void checkHonorific(Token t) {
		Iterator<String> it = WordLists.honorifics().iterator();
		
		while(it.hasNext()) {
			String honorific = it.next();
			
			if(t.getName().equalsIgnoreCase(honorific)) {
				t.getFeatures().setHonorific(true);
				return;
			}	
		}
	}
	
	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common prefixes. This
	 * method will update the Token's feature list prefix value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param t Token to be checked for prefix
	 */
	public static void checkPrefix(Token t) {
		Iterator<String> it = WordLists.lastNamePrefixes().iterator();
		
		while(it.hasNext()) {
			String prefix = it.next();
			
			if(t.getName().contains(prefix)) {
				t.getFeatures().setPrefix(true);
				return;
			}	
		}
	}
	
	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common suffixes. This
	 * method will update the Token's feature list suffix value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param t Token to be checked for prefix
	 */
	public static void checkSuffix(Token t) {
		Iterator<String> it = WordLists.lastNameSuffixes().iterator();
		
		while(it.hasNext()) {
			String suffix = it.next();
			
			if(t.getName().equalsIgnoreCase(suffix)) {
				t.getFeatures().setSuffix(true);
				return;
			}	
		}
	}
	
	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common non personal identifier cues. This
	 * method will update the Token's feature list nonPersonalProperName value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param t Token to be checked for non personal identifier cue
	 */
	public static void checkNonPersonalProperName(Token t) {
		Iterator<String> it = WordLists.nonPersonalIdentifierCues().iterator();
		
		while(it.hasNext()) {
			String npic = it.next();
			
			if(t.getName().equalsIgnoreCase(npic)) {
				t.getFeatures().setNonPersonalProperName(true);
				return;
			}	
		}
	}

	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common stop words. This
	 * method will update the Token's feature list hasStopList value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param stopWord Token to be checked for common stop word
	 */
	public static void checkStopList(Token stopWord)
	{
		Iterator<String> sl = WordLists.stoplist().iterator();

		while(sl.hasNext()) {
			String stop = sl.next();

			if (stopWord.getName().equalsIgnoreCase(stop)) {
				stopWord.getFeatures().setHasStopList(true);
			}
		}
	}
	
	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common first names. This
	 * method will update the Token's feature list isFirstName value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param tokenList Token to be checked for common first names
	 */
	public static void checkFirstNames(List<Token> tokenList) {
		for (Token t : tokenList) {
			if (t.getName().matches("\\w")) {
				continue;
			}
			
			Iterator<String> it = WordLists.commonFirstNames().iterator();
			
			while(it.hasNext()) {
				String name = it.next();
				
				if (name.toLowerCase().contains(t.getName().toLowerCase())) {
					//Do not mark as first name if it's an article or conjunction
					if(t.getFeatures().getPartOfSpeech() == null
							|| t.getFeatures().getPartOfSpeech().equals(String.valueOf(PartOfSpeech.ARTICLE)) 
							|| t.getFeatures().getPartOfSpeech().equals(String.valueOf(PartOfSpeech.CONJUNCTION)))  {
						continue;
					}
					t.getFeatures().setCommonFirstName(true);
					break;
				}
			}
			
			it = WordLists.firstNames().iterator();
			
			while(it.hasNext()) {
				String name = it.next();
				
				if(name.toLowerCase().equalsIgnoreCase(t.getName().toLowerCase())) {
					//Do not mark as first name if it's an article or conjunction
					if(t.getFeatures().getPartOfSpeech() == null
							|| t.getFeatures().getPartOfSpeech().equals(String.valueOf(PartOfSpeech.ARTICLE)) 
							|| t.getFeatures().getPartOfSpeech().equals(String.valueOf(PartOfSpeech.CONJUNCTION)))  {
						continue;
					}
					
					t.getFeatures().setFirstName(true);
				}
			}	
		}
	}
	
	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common last names. This
	 * method will update the Token's feature list isLastName value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param tokenList Token to be checked for common last names
	 */
	public static void checkLastNames(List<Token> tokenList) {	
		for (Token t : tokenList) {
			if (t.getName().matches("\\w")) {
				continue;
			}
			
			Iterator<String> it = WordLists.commonLastNames().iterator();
			
			while(it.hasNext()) {
				String name = it.next();
				
				if (name.toLowerCase().contains(t.getName().toLowerCase())) {
					//Do not mark as first name if it's an article or conjunction
					if(t.getFeatures().getPartOfSpeech() == null
							|| t.getFeatures().getPartOfSpeech().equals(String.valueOf(PartOfSpeech.ARTICLE)) 
							|| t.getFeatures().getPartOfSpeech().equals(String.valueOf(PartOfSpeech.CONJUNCTION)))  {
						continue;
					}
					t.getFeatures().setCommonLastName(true);
					break;
				}
			}
			
			it = WordLists.lastNames().iterator();
			
			while(it.hasNext()) {
				String name = it.next();
				
				if(name.toLowerCase().contains(t.getName().toLowerCase())) {
					//Do not mark as first name if it's an article or conjunction
					if(t.getFeatures().getPartOfSpeech() == null
							|| t.getFeatures().getPartOfSpeech().equals(String.valueOf(PartOfSpeech.ARTICLE)) 
							|| t.getFeatures().getPartOfSpeech().equals(String.valueOf(PartOfSpeech.CONJUNCTION)))  {
						continue;
					}
					t.getFeatures().setLastName(true);
				}
			}	
		}
	}
	
	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common cities and states. This
	 * method will update the Token's feature list isCityState value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param tokenList Token to be checked for common cities and states
	 */
	public static void checkCitiesStates(List<Token> tokenList) {
		// Initialize a list of lists.
		// This will serve as a way of storing tokenized WordList contents.
		// For example: "Anaktuvuk Pass, Alaska" will become
		// ['Anaktuvuk', 'Pass', ',', 'Alaska'] along with all the other cities/states.
		List<List<String>> tokenizedCitiesStates = new ArrayList<List<String>>();
		
		String orig = Token.combineTokenList(tokenList);
		
		// Iterate through the citiesAndStatesUS from the WordLists library.
		Iterator<String> it = WordLists.citiesAndStatesUS().iterator();
		while(it.hasNext()) {
			String cityState = it.next();
			if(!orig.contains(cityState)) continue;
			List<String> piece = tokenizeString(cityState);
			
			if(piece != null) {
				tokenizedCitiesStates.add(tokenizeString(cityState)); // Tokenize the string for comparison	
			}
		}
		
		tokenizedCompare(tokenList, tokenizedCitiesStates, "citystate");
	}

	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common country names. This
	 * method will update the Token's feature list isCountry value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param tokenList Token to be checked for common countries
	 */
	public static void checkCountries(List<Token> tokenList) {
		List<List<String>> tokenizedCountries = new ArrayList<List<String>>();
		
		// Iterate through the countriesAndTerritories from the WordLists library.
		Iterator<String> it = WordLists.countriesAndTerritories().iterator();
		
		String orig = Token.combineTokenList(tokenList);
		
		while(it.hasNext()) {
			String country = it.next();
			if(!orig.contains(country)) continue;
			List<String> piece = tokenizeString(country);
			
			if(piece != null) {
				tokenizedCountries.add(tokenizeString(country)); // Tokenize the string for comparison	
			}
		}
		
		tokenizedCompare(tokenList, tokenizedCountries, "country");
	}
	
	/**
	 * Uses the extract-wordlist API (Steven Zeil, 2015) to check for common place names. This
	 * method will update the Token's feature list isPlace value to true, or it will remain
	 * false otherwise.
	 * 
	 * @param tokenList Token to be checked for common places
	 */
	public static void checkPlaces(List<Token> tokenList) {
		
		List<List<String>> tokenizedPlaces = new ArrayList<List<String>>();
		
		// Iterate through the countriesAndTerritories from the WordLists library.
		Iterator<String> it = WordLists.places().iterator();
		
		String orig = Token.combineTokenList(tokenList);
		
		while(it.hasNext()) {
			String place = it.next();
			if(!orig.contains(place)) continue;
			List<String> piece = tokenizeString(place);
			
			if(piece != null) {
				tokenizedPlaces.add(tokenizeString(place)); // Tokenize the string for comparison	
				
			}
		}
		
		tokenizedCompare(tokenList, tokenizedPlaces, "place");
	}
	
	/**
	 * Tokenizes strings for the purpose of comparing them to tokens.
	 * Uses the same logic as Token's createTokenList, except returns list of Strings.
	 * This helps provide a one-to-one mapping between a word list and an 
	 * input. This is useful with those checks that need to consider things
	 * like spaces between them.
	 * 
	 * @param s String to be placed into a tokenized format
	 * @return List of strings which represents the tokenized str param
	 */
	public static List<String> tokenizeString(String s) {
		//Create a list of tokens
		List<String> strings = new ArrayList<String>();
		int count = 0;
		
		int beginning = 0;
		String thisChar = "";
		String nextChar = "";
		String beginTag = "";
		String endTag = "";
		
		for (int i=0; i < s.length(); i++) {
			//beginning being greater than i means that those characters have already been grabbed as part of another token
			if (i < beginning) {
				continue;
			}
			
			//gets next char
			thisChar = String.valueOf(s.charAt(i));
			if (i != s.length()-1) {
				nextChar = String.valueOf(s.charAt(i+1));
			}
			else {
				nextChar = " ";
			}
			
			//gets this char and the four characters after it
			if(i+5 == s.length()) {
				beginTag = s.substring(i);
			}
			else if(i+4 < s.length()) {
				beginTag = s.substring(i, i+5);
			}
			
			//gets this char and the five characters after it
			if(i+6 == s.length()) {
				endTag = s.substring(i);
			}
			else if(i+5 < s.length()) {
				endTag = s.substring(i, i+6);
			}
			
			//skips the token if it is a whitespace, but increases the count to keep the positions accurate
			if (thisChar.matches(" ")) {
				beginning = i+1;
				count++;
			}
			
			//creates tokens made of alphabetical letters
			else if(thisChar.matches("[a-zA-Z]")) {
				if(nextChar.matches("[^a-zA-Z]")) {
					strings.add(s.substring(beginning, i+1));
					beginning = i+1;
					count++;
				}
			}
			
			//creates tokens made of numbers
			else if(thisChar.matches("[0-9]")) {
				if(nextChar.matches("[^0-9]")) {
					strings.add(s.substring(beginning, i+1));
					beginning = i+1;
					count++;
				}
			}
			
			//creates tokens holding <PER> and <NER> tags
			else if (beginTag.equals("<PER>") || beginTag.equals("<NER>")) {
				strings.add(beginTag);
				beginning = i+5;
				count++;
			}
			
			//creates tokens holding </PER> and </NER> tags
			else if (endTag.equals("</PER>") || endTag.equals("</NER>")) {
				strings.add(endTag);
				beginning = i+6;
				count++;
			}
			
			//creates tokens of characters that are not alphabetical or numbers
			else if (thisChar.matches("\\W")) {
				strings.add(s.substring(beginning, i+1));
				beginning = i+1;
				count++;
			}
		}

		return strings;
	}
	
	/**
	 * This method compares list of tokenized lists to a provided token list.
	 * This implementation is to handle spaces between tokens.
	 * "Los Angelos" should be picked up as a place.
	 * 
	 * @param tokenList The token list to be modified
	 * @param listOfTokenizedLists The tokenized word list of lists to be checked over
	 * @param type The type of attribute that's being checked for.
	 */
	public static void tokenizedCompare(List<Token> tokenList, List<List<String>> listOfTokenizedLists, String type)
	{
		List<Integer> foundInd = new ArrayList<Integer>();
		List<Integer> indIsLoc = new ArrayList<Integer>();
		
		for(List<String> loc : listOfTokenizedLists) {
			// foundInd represents found indexes in the token list, returned by findAll.
			// indIsLoc represents the current status of the index.
			if(loc.size() == 0) continue;
			
			foundInd = Token.findAll(tokenList, loc.get(0));
			if(foundInd.size() == 0) continue;
			
			indIsLoc.clear();
			foundInd.forEach(i -> indIsLoc.add(-1));
			
			for(int i = 1; i < loc.size(); i++) {
				String match = loc.get(i);
				
				boolean leave = false;
				for(int j = 0; j < foundInd.size(); j++) {
					if(tokenList.size() <= foundInd.get(j)+i) continue;
					
					String tokenMapMatch = tokenList.get(foundInd.get(j)+i).getName();
					
					if(!match.equals(tokenMapMatch)) {
						indIsLoc.set(j, -1); // Index NOT found.
						if(type.equals("place")) { leave = true; break; }
						continue;
					} else if (match.equals(tokenMapMatch) && i == loc.size()-1) {
						indIsLoc.set(j, loc.size()); // Index is found, so we set it to the size to iterate through later.
					}
				}
				
				if(leave) break;
			}
			
			for(int i = 0; i < foundInd.size(); i++) {
				if(indIsLoc.get(i) != -1) {
					for(int j = 0; j < indIsLoc.get(i); j++) {
						switch(type) {
							case "place":
								tokenList.get(foundInd.get(i)+j).getFeatures().setPlace(true);
								break;
							case "country":
								tokenList.get(foundInd.get(i)+j).getFeatures().setCountry(true);
								break;
							case "citystate":
								tokenList.get(foundInd.get(i)+j).getFeatures().setCityState(true);
								break;
							default:
								break;
						}
					}
				}
			}
		}
	}
}