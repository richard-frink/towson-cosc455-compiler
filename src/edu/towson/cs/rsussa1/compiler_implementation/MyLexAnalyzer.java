package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

import edu.towson.cosc.cosc455.interfaces.LexicalAnalyzer;

public class MyLexAnalyzer implements LexicalAnalyzer {
	private String source;
	private char[] lexeme = new char [100];
	private char nextChar;
	private int lexLength;
	private int currentPosition;
	
	public void start(String file){
		source = file;
		getNextToken();
	}
	
	@Override
	public void getNextToken() {
		if(source == ""){
			Compiler.currentToken = "";
		}
		else{
			
		}
		
	}

	@Override
	public void getCharacter() {
		if(!source.equals("")){
			nextChar = source.charAt(currentPosition);
			source.substring(1);
			//this cuts off the first char, the one we are now going to process
			if(!isSpace(String.valueOf(nextChar))){
				addCharacter();
			}
			else{
				currentPosition++;
				getCharacter();
			}
		}
	}

	@Override
	public void addCharacter() {
		lexeme[lexLength] = nextChar;
		lexLength++;
		if(!source.equals(""))
			currentPosition++;
			getCharacter();
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
		
		return false;
	}
}
