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
	//private char tempChar = '\0';
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
					addCharacter();
					if(nextChar == 'd' || nextChar == 'D'){
						getCharacter();
						addCharacter();
						if(nextChar == 'e' || nextChar == 'E'){
							getCharacter();
							addCharacter();
							if(nextChar == 'f' || nextChar == 'F'){
								addCharacter();
								break;
							}
						}
					}
					else if(nextChar == 'u' || nextChar == 'U'){
						getCharacter();
						addCharacter();
						if(nextChar == 's' || nextChar == 'S'){
							getCharacter();
							addCharacter();
							if(nextChar == 'e' || nextChar == 'E'){
								addCharacter();
								break;
							}
						}
					}
					else if(nextChar == 'e' || nextChar == 'E'){
						getCharacter();
						addCharacter();
						if(nextChar == 'n' || nextChar == 'N'){
							getCharacter();
							addCharacter();
							if(nextChar == 'd' || nextChar == 'D'){
								addCharacter();
								break;
							}
						}
					}
				}
				else if(nextChar == '#'){
					getCharacter();
					addCharacter();
					if(nextChar == 'b' || nextChar == 'B'){
						getCharacter();
						addCharacter();
						if(nextChar == 'e' || nextChar == 'E'){
							getCharacter();
							addCharacter();
							if(nextChar == 'g' || nextChar == 'G'){
								getCharacter();
								addCharacter();
								if(nextChar == 'i' || nextChar == 'I'){
									getCharacter();
									addCharacter();
									if(nextChar == 'n' || nextChar == 'N'){
										addCharacter();
										break;
									}
								}
							}
						}
					}
					else if(nextChar == 'e' || nextChar == 'E'){
						getCharacter();
						addCharacter();
						if(nextChar == 'n' || nextChar == 'N'){
							getCharacter();
							addCharacter();
							if(nextChar == 'd' || nextChar == 'D'){
								addCharacter();
								break;
							}
						}
					}
				}
				if(lookupToken()){
					break;
				}
				else if(isText()){
					getText();
					break;
				}
				else{
					addCharacter();
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
			//if(tempChar != '\0'){
			//	nextChar = tempChar;
			//	lexeme[0] = nextChar;
			//	tempChar = '\0';
			//	lexLength++;
			//}
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
	
	public void getText(){
		
	}
}
