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
	private static int currentPosition = 0;
	
	public void start(String file){
		source = file;
		getCharacter();
		getNextToken();
	}
	
	@Override
	public void getNextToken() {
		if(source.length() < currentPosition){
			Compiler.currentToken = "";
		}
		else{
			try{
				if(nextChar == '$'){
					addCharacter();
					getCharacter();
					getVar();
				}
				else if(nextChar == '#'){
					addCharacter();
					getCharacter();
					getHash();
				}
				else if(nextChar == '*'){
					addCharacter();
					getCharacter();
					if(nextChar == '*'){
						addCharacter();
					}
				}
				else if(lookupToken()){
					addCharacter();
				}
				else{
					if((new Text()).isLegal(String.valueOf(nextChar))){
						addCharacter();
						getText();
					}
					else{
						throw new CompilerException("Lexical Error - No legal lexeme found");
					}
				}
			} catch(CompilerException e){
				e.printStackTrace();
				System.exit(0);
			}
			String currentT = "";
			for(int i = 0; i < lexLength; i++){
				currentT = currentT + lexeme[i];
			}
			Compiler.currentToken = currentT;
			for(int i = 0; i < 100; i++){
				lexeme[i] = ' ';
			}
			lexLength = 0;
			getCharacter();
		}
	}

	@Override
	public void getCharacter() {
		if(source.length() > currentPosition){
			nextChar = source.charAt(currentPosition);
			if(isSpace(String.valueOf(nextChar))){
				currentPosition++;
				getCharacter();
			}
		}
	}

	@Override
	public void addCharacter() {
		lexeme[lexLength] = nextChar;
		lexLength++;
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
		String currentLex = "" + nextChar;
		if(currentLex.equalsIgnoreCase("{") || currentLex.equalsIgnoreCase("}") || currentLex.equalsIgnoreCase("[") ||
				currentLex.equalsIgnoreCase("]") || currentLex.equalsIgnoreCase("(") || currentLex.equalsIgnoreCase(")") ||
				currentLex.equalsIgnoreCase("<") || currentLex.equalsIgnoreCase(">") || currentLex.equalsIgnoreCase("^") ||
				currentLex.equalsIgnoreCase("@") || currentLex.equalsIgnoreCase("=") || currentLex.equalsIgnoreCase("*") ||
				currentLex.equalsIgnoreCase("**") || currentLex.equalsIgnoreCase("+") || currentLex.equalsIgnoreCase(";") || 
				currentLex.equalsIgnoreCase("~") ||	currentLex.equalsIgnoreCase("%")){
			return true;
		}
		return false;
	}
	
	public void getText(){
		char temp = source.charAt(currentPosition);
		while((new Text()).isLegal(String.valueOf(temp))){
			nextChar = temp;
			addCharacter();
			temp = source.charAt(currentPosition);
			while(temp == ' '){
				nextChar = temp;
				addCharacter();
				temp = source.charAt(currentPosition);
			}
		}
	}
	
	public void getVar(){
		try{
			if(nextChar == 'd' || nextChar == 'D'){
				addCharacter();
				getCharacter();
				if(nextChar == 'e' || nextChar == 'E'){
					addCharacter();
					getCharacter();
					if(nextChar == 'f' || nextChar == 'F'){
						addCharacter();
					}
				}
			}
			else if(nextChar == 'u' || nextChar == 'U'){
				addCharacter();
				getCharacter();
				if(nextChar == 's' || nextChar == 'S'){
					addCharacter();
					getCharacter();
					if(nextChar == 'e' || nextChar == 'E'){
						addCharacter();
					}
				}
			}
			else if(nextChar == 'e' || nextChar == 'E'){
				addCharacter();
				getCharacter();
				if(nextChar == 'n' || nextChar == 'N'){
					addCharacter();
					getCharacter();
					if(nextChar == 'd' || nextChar == 'D'){
						addCharacter();
					}
				}
			}
			else{
				throw new CompilerException("Lexical Error - No legal lexeme found");
			}
		} catch(CompilerException e){
			e.printStackTrace();
		}
	}
	
	public void getHash(){
		try{
			if(nextChar == 'b' || nextChar == 'B'){
				addCharacter();
				getCharacter();
				if(nextChar == 'e' || nextChar == 'E'){
					addCharacter();
					getCharacter();
					if(nextChar == 'g' || nextChar == 'G'){
						addCharacter();
						getCharacter();
						if(nextChar == 'i' || nextChar == 'I'){
							addCharacter();
							getCharacter();
							if(nextChar == 'n' || nextChar == 'N'){
								addCharacter();
							}
						}
					}
				}
			}
			else if(nextChar == 'e' || nextChar == 'E'){
				addCharacter();
				getCharacter();
				if(nextChar == 'n' || nextChar == 'N'){
					addCharacter();
					getCharacter();
					if(nextChar == 'd' || nextChar == 'D'){
						addCharacter();
					}
				}
			}
			else{
				throw new CompilerException("Lexical Error - No legal lexeme found");
			}
		} catch(CompilerException e){
			e.printStackTrace();
		}
	}
}
