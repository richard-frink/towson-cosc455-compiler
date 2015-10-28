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
		
		fr.close();
	}
}
