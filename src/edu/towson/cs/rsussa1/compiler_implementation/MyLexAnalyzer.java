package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

import edu.towson.cosc.cosc455.interfaces.LexicalAnalyzer;

public class MyLexAnalyzer implements LexicalAnalyzer {
	private String source;
	private char nextChar;
	private int currentPosition;
	private Token currentToken;
	//private String[] legalTokens = {};   i have defined all classes for all legal tokens
				//still need to put together "VAR DEF, VAR USE, AUDIO LINK, VIDEO LINK, GENERAL LINK"
				//have idea to work in an abstract parent class for all portions of all 5 major parts
				//but have not done so yet ****as of - 10/18/2015****
	
	public void start(String file){
		source = file;
		
		
	}
	
	@Override
	public void getNextToken() {
		
	}

	@Override
	public void getCharacter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCharacter() {
		if(false){ //isspace false
			
		}
		else{
			getCharacter();
		}

	}

	@Override
	public boolean isSpace(String c) {
		if(c.equals(" ")){
			return true;
		}
		return false;
	}

	@Override
	public boolean lookupToken() {
		// TODO Auto-generated method stub
		return false;
	}

}
