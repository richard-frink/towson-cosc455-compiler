package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

import java.io.*;

public class Compiler {
	public static String currentToken;
	public static MyLexAnalyzer Lexer;
	
	public static void main(String[] args) throws IOException{
		Lexer = new MyLexAnalyzer();
		MySynAnalyzer Parser = new MySynAnalyzer();
		
		String sourceLine = null;
		String temp = "";
		
		FileReader fr = new FileReader(args[0]);
		BufferedReader br = new BufferedReader(fr);
		/**
		 * need to check if the file is actually of extension ".mkd"!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
		 */
		
		while((sourceLine = br.readLine()) != null){
			temp = temp + sourceLine;
		}
		Lexer.start(temp);
		Parser.markdown();
		
		
		
		fr.close();
	}
}
