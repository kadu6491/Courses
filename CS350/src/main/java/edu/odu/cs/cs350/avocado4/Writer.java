package edu.odu.cs.cs350.avocado4;

/**
 * Writer Class
 * 
 * This class helps join inputs and outputs from the
 * API and System.in program usages.
 */
public class Writer {
	
	private String markedText;
	
	/**
	 * Default constructor for Writer class
	 * Writer will hold the final, processed output string of all text blocks provided
	 */
	public Writer() {
		markedText = "";
	}
	
	/**
	 * This function sets the markedText class attribute to be read later.
	 * 
	 * @param s String to set markedText class attribute
	 */
	public void write(String s) {
		markedText = s;
	}
	
	/**
	 * This function returns the markedText attribute for reading.
	 * 
	 * @return Returns the markedText class attribute
	 */
	public String read() {
		return markedText;
	}

}
