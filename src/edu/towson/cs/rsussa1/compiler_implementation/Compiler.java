package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

import java.io.*;

public class Compiler {
	public static Token t;
	public static MyLexAnalyzer Lexer;
	
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
