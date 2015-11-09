package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */


//import java.util.ArrayList;
import edu.towson.cosc.cosc455.interfaces.LexicalAnalyzer;
import edu.towson.cs.rsussa1.tokens.*;

public class MyLexAnalyzer implements LexicalAnalyzer {
	private String source;
	private char[] lexeme = new char [100];
	private char nextChar;
	private int lexLength;
	private int currentPosition;
	//private ArrayList<Token> tokens = new ArrayList<Token>();	
	
	public void start(String file){
		source = file;
		getNextToken();
	}
	
	@Override
	public void getNextToken() {
		if(source == ""){
			Compiler.currentToken = "";
		}
		else if(lexLength == 100){
			System.err.print("No legal lexeme found in the source.");
		}
		else{
			while(lexLength <= 100 || !lookupToken()){
				getCharacter();
			}
			if(lexLength == 100){
				System.err.print("No legal lexeme found, exiting program");
				System.exit(0);
			}
			String currentT = "";
			for(int i = 0; i < lexLength; i++)
				currentT = currentT + lexeme[i];
			Compiler.currentToken = currentT;
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
		String currentLex = "";
		for(int i = 0; i < lexLength; i++)
			currentLex = currentLex + lexeme[i];
		if(currentLex.equalsIgnoreCase("{") || currentLex.equalsIgnoreCase("}") || currentLex.equalsIgnoreCase("[") ||
				currentLex.equalsIgnoreCase("]") || currentLex.equalsIgnoreCase("(") || currentLex.equalsIgnoreCase(")") ||
				currentLex.equalsIgnoreCase("<") || currentLex.equalsIgnoreCase(">") || currentLex.equalsIgnoreCase("^") ||
				currentLex.equalsIgnoreCase("@") || currentLex.equalsIgnoreCase("=") || currentLex.equalsIgnoreCase("*") ||
				currentLex.equalsIgnoreCase("**") || currentLex.equalsIgnoreCase("#begin") || currentLex.equalsIgnoreCase("#end") ||
				currentLex.equalsIgnoreCase("+") || currentLex.equalsIgnoreCase(";") || currentLex.equalsIgnoreCase("~") ||
				currentLex.equalsIgnoreCase("$def") || currentLex.equalsIgnoreCase("$use") || currentLex.equalsIgnoreCase("$end") ||
				currentLex.equalsIgnoreCase("%")){
			return true;
		}
		else{
			return isText(currentLex);
		}
	}
	
	public boolean isText(String text){
		if((new Text()).isLegal(text) && !(new Text()).isLegal((String.valueOf(source.charAt(currentPosition)))))
			return true;
		return false;
	}
}
