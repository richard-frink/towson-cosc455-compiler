package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

public class Token {
	public String token;
	
	public Token(String str){
		token = str;
	}
	
	public String getToken(){
		return token;
	}
}
