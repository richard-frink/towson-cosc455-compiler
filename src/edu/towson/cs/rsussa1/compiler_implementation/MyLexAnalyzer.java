package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */


//import java.util.ArrayList;
import edu.towson.cosc.cosc455.interfaces.LexicalAnalyzer;
import edu.towson.cs.rsussa1.tokens.Text;

public class MyLexAnalyzer implements LexicalAnalyzer {
	private String source;
	private char[] lexeme = new char [100];
	private char nextChar;
	private int lexLength = 0;
	private int currentPosition = 0;
	
	public void start(String file){
		source = file;
		getNextToken();
	}
	
	@Override
	public void getNextToken() {
		if(source.length() < currentPosition){
			Compiler.currentToken = "";
		}
		else{
			while(!lookupToken()){
				getCharacter();
				if(lexLength > 100){
					System.err.print("No legal lexeme found, exiting program");
					System.exit(0);
				}
			}
			String currentT = "";
			for(int i = 0; i < lexLength; i++)
				currentT = currentT + lexeme[i];
			Compiler.currentToken = currentT;
			lexeme = new char[100];
			lexLength = 0;
		}
	}

	@Override
	public void getCharacter() {
		if(source.length() > currentPosition){
			nextChar = source.charAt(currentPosition);
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
		for(int i = 0; i < lexLength; i++){
			System.out.print(currentLex);
			currentLex = currentLex + lexeme[i];
		}
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
