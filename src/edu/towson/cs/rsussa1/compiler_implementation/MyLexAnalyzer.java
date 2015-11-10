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
	private char tempChar = '\0';
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
			do {
				if(nextChar == '$'){
					getCharacter();
					if(nextChar == 'd' || nextChar == 'D'){
						getCharacter();
						if(nextChar == 'e' || nextChar == 'E'){
							getCharacter();
							if(nextChar == 'f' || nextChar == 'F'){
								break;
							}
						}
					}
					else if(nextChar == 'u' || nextChar == 'U'){
						getCharacter();
						if(nextChar == 's' || nextChar == 'S'){
							getCharacter();
							if(nextChar == 'e' || nextChar == 'E'){
								break;
							}
						}
					}
					else if(nextChar == 'e' || nextChar == 'E'){
						getCharacter();
						if(nextChar == 'n' || nextChar == 'N'){
							getCharacter();
							if(nextChar == 'd' || nextChar == 'D'){
								break;
							}
						}
					}
				}
				else if(nextChar == '#'){
					getCharacter();
					if(nextChar == 'b' || nextChar == 'B'){
						getCharacter();
						if(nextChar == 'e' || nextChar == 'E'){
							getCharacter();
							if(nextChar == 'g' || nextChar == 'G'){
								getCharacter();
								if(nextChar == 'i' || nextChar == 'I'){
									getCharacter();
									if(nextChar == 'n' || nextChar == 'N'){
										break;
									}
								}
							}
						}
					}
					else if(nextChar == 'e' || nextChar == 'E'){
						getCharacter();
						if(nextChar == 'n' || nextChar == 'N'){
							getCharacter();
							if(nextChar == 'd' || nextChar == 'D'){
								break;
							}
						}
					}
				}
				if(lookupToken()){
					lexLength--;
					break;
				}
				else if(!isText()){
					lexLength--;
					break;
				}
				else{
					getCharacter();
					continue;
				}
			} while(true);
			String currentT = "";
			for(int i = 0; i < lexLength; i++)
				currentT = currentT + lexeme[i];
			Compiler.currentToken = currentT;
			lexeme = new char[100];
			lexLength = 0;
			if(tempChar != '\0'){
				nextChar = tempChar;
				lexeme[0] = nextChar;
				tempChar = '\0';
				lexLength++;
			}
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
			currentLex = currentLex + lexeme[i];
		}
		System.out.println(currentLex + "    checking for token");
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
		return false;
	}
	
	public boolean isText(){
		if(!lookupToken()){
			String currentLex = "";
			for(int i = 0; i < lexLength; i++){
				currentLex = currentLex + lexeme[i];
			}
			System.out.println(currentLex + "    checking for text");
			if((new Text()).isLegal(currentLex)){
				return true;
			}
		}
		return false;
	}
}
