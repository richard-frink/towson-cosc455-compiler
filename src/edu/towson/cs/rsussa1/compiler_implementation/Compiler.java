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
	public static MySynAnalyzer Parser;
	public static MySemAnalyzer SemanticAna;
	public static String file = "";
	public static String filePath = "";
	
	public static void main(String[] args) throws IOException{
		Lexer = new MyLexAnalyzer();
		Parser = new MySynAnalyzer();
		SemanticAna = new MySemAnalyzer();
		
		String sourceFile = args[0];
		
		String ext = "";
		int i = sourceFile.lastIndexOf('.');
		if (i > 0) {
		    ext = sourceFile.substring(i+1);
		    file = sourceFile.substring(0, i);
		}
		
		if(ext.equalsIgnoreCase("mkd")){
			File sourceF = new File(sourceFile);
			sourceF.mkdirs();
			
			
			//need to get path for file ah i do not know how to manipulate files for crap dudeeeeee
			
			
			String sourceLine = null;
			String temp = "";
			
			FileReader fr = new FileReader(sourceFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((sourceLine = br.readLine()) != null){
				temp = temp + " " + sourceLine;
			}
			
			Lexer.start(temp);
			Parser.markdown();
			
			
			
			fr.close();
		}
		else{
			System.err.print("The file provided was not of the proper '.mkd' extension. Ending program now.");
		}
	}
}
