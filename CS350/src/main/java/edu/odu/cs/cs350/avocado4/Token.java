package edu.odu.cs.cs350.avocado4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Token Class
 * 
 * This class serves as the definition for our logical distinctions in input.
 * Token and FeatureSet are one-to-one. Tokens will be provided to the machine learning model.
 */
public class Token implements Comparable<Token> {
	
	private String name;
	private String classification;
	private FeatureSet features;
	private Integer positionInOriginal;

	/**
	 * Single argument constructor for Token.
	 * The classification attribute is set to a blank string.
	 * FeatureSet is initialized to a blank FeatureSet.
	 * 
	 * @param name The name associated with the token.
	 */
	public Token(String name) {
		this.name = name;
		this.classification = null;
		this.features = new FeatureSet();
		this.positionInOriginal = null;
	}
	
	/**
	 * Two argument constructor for Token.
	 * FeatureSet is initialized to a blank FeatureSet.
	 * 
	 * @param name The name associated with the token.
	 * @param classification The classification associated with the token.
	 */
	public Token(String name, String classification) {
		this.name = name;
		this.classification = classification;
		this.features = new FeatureSet();
		this.positionInOriginal = null;
	}
	
	/**
	 * Sets the name attribute
	 * 
	 * @param s New token name
	 */
	public void setName(String s) {
		this.name = s;
	}
	
	/**
	 * Returns the name attribute
	 * 
	 * @return Name of the token object
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the classification attribute
	 * 
	 * @param c Classification string associated with Token object
	 */
	public void setClassification(String c) {
		this.classification = c;
	}
	
	/**
	 * Gets the classification attribute
	 * 
	 * @return Classification string attribute
	 */
	public String getClassification() {
		return this.classification;
	}
	
	/**
	 * Sets the features attribute
	 * 
	 * @param fs FeatureSet of a token
	 */
	public void setFeatures(FeatureSet fs) {
		this.features = fs;
	}
	
	/**
	 * Gets the features attribute
	 * 
	 * @return FeatureSet features attribute
	 */
	public FeatureSet getFeatures() {
		return this.features;
	}
	
	/**
	 * Sets the positionInOriginal attribute 
	 * 
	 * The purpose of this attribute is to preserve the locations of the token in the original string.
	 * @param pos The index of the token's original position in the string it derived from
	 */
	public void setPosition(Integer pos) {
		this.positionInOriginal = pos;
	}
	
	/**
	 * Returns the positionInOriginal attribute
	 * 
	 * @return Integer representing the token's position in the string it derived from
	 */
	public Integer getPosition() {
		return this.positionInOriginal;
	}
	
	/**
	 * Returns the feature string. This is simply all the features concatenated into a string
	 * delimited by a comma and returned.
	 * 
	 * @return String combines all aspects of this tokens feature set into a string with "," delimiter
	 */
	public String getFeaturesString() {
		String featureString = this.features.getIsInDictionary()
				+ "," + this.features.getIsInitial()
				+ "," + this.features.hasHonorific()
				+ "," + this.features.hasPrefix()
				+ "," + this.features.hasSuffix()
				+ "," + this.features.hasNonPersonalProperName()
				+ "," + this.features.hasStopList()
				+ "," + this.features.getPartOfSpeech()
				+ "," + this.features.getLexicalType()
				+ "," + this.features.getCommonFirstName()
				+ "," + this.features.getFirstName()
				+ "," + this.features.getCommonLastName()
				+ "," + this.features.getLastName()
				+ "," + this.features.getCityState()
				+ "," + this.features.getCountry()
				+ "," + this.features.getPlace();
		return featureString;
	}	
	
	/**
	 * The list returned will be Tokens with the name attribute associated with each section of text
	 * delimited by a space.
	 * 
	 * @param s Raw string that will be split into Tokens
	 * @return a list of Tokens from a string
	 */
	public static List<Token> createTokenList(String s) {
		//Create a list of tokens
		List<Token> tokens = new ArrayList<Token>();
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
					Token newToken = new Token(s.substring(beginning, i+1));
					newToken.setPosition(count);
					tokens.add(newToken);
					beginning = i+1;
					count++;
				}
			}
			
			//creates tokens made of numbers
			else if(thisChar.matches("[0-9]")) {
				if(nextChar.matches("[^0-9]")) {
					Token newToken = new Token(s.substring(beginning, i+1));
					newToken.setPosition(count);
					tokens.add(newToken);
					beginning = i+1;
					count++;
				}
			}
			
			//creates tokens holding <PER> and <NER> tags
			else if (beginTag.equals("<PER>") || beginTag.equals("<NER>")) {
				Token newToken = new Token(beginTag);
				newToken.setPosition(count);
				tokens.add(newToken);
				beginning = i+5;
				count++;
			}
			
			//creates tokens holding </PER> and </NER> tags
			else if (endTag.equals("</PER>") || endTag.equals("</NER>")) {
				Token newToken = new Token(endTag);
				newToken.setPosition(count);
				tokens.add(newToken);
				beginning = i+6;
				count++;
			}
			
			//creates tokens of characters that are not alphabetical or numbers
			else if (thisChar.matches("\\W")) {
				Token newToken = new Token(s.substring(beginning, i+1));
				newToken.setPosition(count);
				tokens.add(newToken);
				beginning = i+1;
				count++;
			}
		}

		return tokens;
	}
	
	/**
	 * Combines all tokens in a list of tokens back into a String.
	 * 
	 * @param tokensList list of tokens
	 * @return String with all tokens
	 */
	public static String combineTokenList(List<Token> tokensList) {
		StringBuffer buf = new StringBuffer();
		int count = 0;
		for (int i = 0; i < tokensList.size(); i++) {
			while (tokensList.get(i).getPosition() > count) {
				buf.append(" ");
				count++;
			}
			
			buf.append(tokensList.get(i).getName());
			count++;
		}
		
		String combinedList = buf.toString();
		
		return combinedList;
	}
	
	/**
	 * This will wrap the name of a token in PER tags
	 * depending on the classification.
	 *
	 * @param tokenList List of tokens to place PER tags
	 */
	public static void placePERTags(List<Token> tokenList) {
		for(Token t : tokenList) {
			if (t.getClassification().contains("start")) {
				t.setName("<PER>" + t.getName());
			}
			else if (t.getClassification().contains("end")) {
				t.setName(t.getName() + "</PER>");
			}
			else if (t.getClassification().contains("both")) {
				t.setName("<PER>" + t.getName() + "</PER>");
			}
		}
	}
	
	/**
	 * Finds all the occurrences of a specific string in the given token list.
	 * Purpose for implementation is related to finding tokens with spaces in between
	 * them. For example, "Hope, AR".
	 * 
	 * @param list List of tokens to be searched through.
	 * @param str String to be found
	 * @return List of indexes 
	 */
	public static List<Integer> findAll(List<Token> list, String str) {
		List<Integer> found = new ArrayList<Integer>();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(str)) {
				found.add(i);
			}
		}
		
		return found;
	}
	
	@Override
	/**
	 * This is the overridden compareTo method from Object.
	 * Compares this token to the token provided via parameter.
	 * Uses compareTo String implementation on Token name attribute.
	 * 
	 * @param t Token to be compared to
	 */
	public int compareTo(Token t) {
		return this.getName().toLowerCase().compareTo(t.getName().toLowerCase());
	}

}
