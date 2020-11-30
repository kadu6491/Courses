package edu.odu.cs.cs350.avocado4;

import edu.odu.cs.cs350.avocado4.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TextBlocks class
 * 
 * Contains the main driver for System.in input. Handles the joining and parsing of TextBlocks.
 */
public class TextBlocks {

	/**
	 * Main method driving the program if System input is provided.
	 *
	 * @param args Required argument for driver function
	 */
	public static void main(String[] args) {
		//read from standard input
		Scanner key = new Scanner(System.in,"utf-8");
		StringBuffer buf = new StringBuffer();
		while(key.hasNext())
		{
			buf.append(key.nextLine());
			if(key.hasNext()) {
				buf.append("\n");
			}
		}
		key.close();
		
		String text = buf.toString();
		
		//creates Writer object
		Writer w = new Writer();
		
		//TextBlock processing
		markPersonalNames(text, w);
		
		//prints to command line
		System.out.print(w.read());
	}

	/**
	 * Parses a string containing 1+ text blocks into a list of strings
	 * If NER tags are present, they indicate the start and end of substrings
	 * If no NER tags are present, the whole string is saved as one item in the list
	 * 
	 * @param s contains a single string containing 1+ text blocks
	 * @return a List of string variables, with each item of the list representing one text block
	 * @throws IllegalArgumentException If the length is too long, an IllegalArgumentException is thrown.
	 */
	public static List<String> parseTextBlocks(String s) throws IllegalArgumentException {
		List<String> splitBlocks = new ArrayList<String>();
		if (s.contains("<NER>")) {
			String[] blocks = StringUtils.substringsBetween(s, "<NER>", "</NER>");
			for (String block : blocks) {
				if(block.length() > 4000) {
					throw new IllegalArgumentException("Text blocks have a max length needs of 4,000 characters.");
				}
				splitBlocks.add("<NER>" + block + "</NER>");	
			}
		}
		else {
			if(s.length() > 4000) {
				throw new IllegalArgumentException("Text blocks have a max length needs of 4,000 characters.");
			}
			splitBlocks.add(s);
		}
		
		return splitBlocks;
	}

	/**
	 * Receives a list of strings and combines them into one string
	 * A new line is created between each item of the list
	 * 
	 * @param splitBlocks contains 1+ text blocks in a list
	 * @return a String, combinedText
	 */
	public static String joinTextBlocks(List<String> splitBlocks) {
		String combinedText = String.join("\n", splitBlocks);
		return combinedText;
	}
	
	/**
	 * Parses text blocks into a List,
	 * finds and marks name data,
	 * and rejoins the text blocks
	 * 
	 * @param s contains 1+ text blocks that possibly contain name data
	 * @param w is a Writer for holding processed text blocks
	 */
	public static void markPersonalNames(String s, Writer w) {
		List<String> splitBlocks = parseTextBlocks(s);
		
		//call processing in Cataloger.java
		splitBlocks = Cataloger.catalogue(splitBlocks);
		
		String combinedText = joinTextBlocks(splitBlocks);
		
		w.write(combinedText);
	}

}
