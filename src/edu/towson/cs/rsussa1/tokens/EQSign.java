package edu.towson.cs.rsussa1.tokens;

import edu.towson.cosc.cosc455.interfaces.LegalToken;
import edu.towson.cs.rsussa1.compiler_implementation.Token;

public class EQSign implements LegalToken {
	static Token t = new Token("=");
	
	@Override
	public boolean isLegal(String str){
		if(str.equalsIgnoreCase(t.getToken())){
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
