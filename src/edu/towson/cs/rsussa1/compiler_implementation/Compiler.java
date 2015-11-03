package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

import java.io.*;
import java.util.Stack;

public class Compiler {
	public static String currentToken;
	public static MyLexAnalyzer Lexer;
	public static Stack<String> myStack = new Stack<String>();
	
	public static void main(String[] args) throws IOException{
		Lexer = new MyLexAnalyzer();
		MySynAnalyzer Parser = new MySynAnalyzer();
		
		String sourceLine = null;
		
		FileReader fr = new FileReader(args[0]);
		BufferedReader br = new BufferedReader(fr);
		
		while((sourceLine = br.readLine()) != null){
			Lexer.start(sourceLine);
			
			
		}
		
		/**
		 * there needs to be tons of code here to build a stack and process things as they are read from the source file
		 * then it needs to be able to print a stack trace, i'm thinking just a sort of line of jargin, aka everying
		 * in order as it should but just with a space in between, i.e. #begin ^ < my title > ^ yada yada yada.....
		 */
		
		fr.close();
	}
}
