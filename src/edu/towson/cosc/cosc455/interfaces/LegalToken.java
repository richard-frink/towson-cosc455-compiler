package edu.towson.cosc.cosc455.interfaces;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

import edu.towson.cs.rsussa1.compiler_implementation.Token;

public interface LegalToken {
	static Token t = new Token("");
	
	public boolean isLegal(String str);

	public String getHTML(boolean openTag);
}
