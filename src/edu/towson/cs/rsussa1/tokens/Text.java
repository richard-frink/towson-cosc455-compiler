package edu.towson.cs.rsussa1.tokens;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */
import edu.towson.cosc.cosc455.interfaces.LegalToken;
import edu.towson.cs.rsussa1.compiler_implementation.Token;

public class Text implements LegalToken {
	static Token t = new Token("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.':?_!/");
	
	@Override
	public boolean isLegal(String str){
		boolean exists = false;
		String temp = t.getToken();
		
		for(int i = 0; i < temp.length(); i++){
			if((String.valueOf(temp.charAt(i))).equalsIgnoreCase(str) == true){
				exists = true;
				break;
			}
		}
		if(exists){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public String getHTML(boolean openTag) {
		return "";
	}
}
