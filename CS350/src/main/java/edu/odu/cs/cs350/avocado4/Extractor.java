package edu.odu.cs.cs350.avocado4;

/**
 * Extractor Class
 * 
 * Provides an API for external applications to call.
 */
public class Extractor {
	/**
	 * This exposes a method for API access from other programs.
	 * This method will mark personal names in a given text block.
	 * 
	 * @param textBlock a block of text to be marked with PER tags.
	 * @return a String containing personal names marked with PER tags.
	 */
	public static String markPersonalNames(String textBlock) {
		Writer w = new Writer();
		TextBlocks.markPersonalNames(textBlock, w);
		return w.read();
	}
}
