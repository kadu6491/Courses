package edu.odu.cs.cs350.avocado4;

import java.util.List;
import java.util.ArrayList;

/**
 * Window Class
 * 
 * The Window class represents a list of tokens as a 'Window' into a string.
 * Window is implemented to look for 5 token ahead/behind.
 */
public class Window {
	
	private List<Token> tokensInWindow;
	private String tokenFeaturesString;
	
	/**
	 * Parameterized constructor for the Window class. Takes tokens to check, along with a
	 * target integer. The constructor initializes the tokensInWindow and tokenFeaturesString
	 * class attributes.
	 * 
	 * @param tokensToCheck full list of tokens in the text block being checked
	 * @param target integer indicating the position of the token to be in the center of the window
	 */
	public Window(List<Token> tokensToCheck, int target) {
		tokensInWindow = new ArrayList<Token>();
		StringBuffer buf = new StringBuffer();
		for (int i = -5; i < 6; i++) {
			if (target + i < 0) {
				this.tokensInWindow.add(new Token("null", "null"));
				buf.append("null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null");
			}
			else if (target + i > tokensToCheck.size()-1) {
				this.tokensInWindow.add(new Token("null", "null"));
				buf.append("null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null");
			}
			else {
				this.tokensInWindow.add(tokensToCheck.get(target+i));
				buf.append(tokensToCheck.get(target+i).getFeaturesString());
				buf.append(",");
				buf.append(tokensToCheck.get(target+i).getClassification());
			}
			if(i!=5) {
				buf.append(",");
			}
		}
		
		this.tokenFeaturesString = buf.toString();
		
		//for debugging window string output
		//System.out.println("feature string " + tokensToCheck.get(target).getName() + " " + tokenFeaturesString);
	}

	/**
	 * Returns the tokenFeaturesString attribute. This attribute is set in the constructor.
	 * 
	 * @return String containing the tokenFeaturesString attribute
	 */
	public String getWindowFeatures() {
		return this.tokenFeaturesString;
	}
	
	/**
	 * Returns the tokensInWindow attribute. This attribute is set in the constructor.
	 * 
	 * @return List of tokens in this window
	 */
	public List<Token> getWindowTokens() {
		return this.tokensInWindow;
	}
}
