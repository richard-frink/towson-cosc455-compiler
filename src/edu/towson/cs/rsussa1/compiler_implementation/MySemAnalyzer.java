package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class MySemAnalyzer {
	public static Stack<String> myStack = new Stack<String>();
	private static Stack<String> htmlStack = new Stack<String>();


	
	private void createHtmlStack(){
		htmlStack.push("");
		
		
	}
	
	public void createHtml(){
		createHtmlStack();
		
		
		
		try{
			File dir = new File(Compiler.file);//needs to find the PATH of the file input to the compiler
			dir.mkdirs();
			File tmp = new File(dir, Compiler.file + ".html");// needs to name the file the same thing except ".html"
			tmp.createNewFile();
		} catch(IOException ioe) {
			System.err.println("Failed to find file");
			ioe.printStackTrace();
		}
	}
	
	public void createNewFile(String sourceFile){
		try {
			File file = new File(Compiler.file + ".html");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sourceFile);
			bw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openHTMLFileInBrowswer(String htmlFileStr){
		File file= new File(htmlFileStr.trim());
		if(!file.exists()){
			System.err.println("File "+ htmlFileStr +" does not exist.");
			return;
		}
		try{
			Desktop.getDesktop().browse(file.toURI());
		} catch(IOException ioe){
			System.err.println("Failed to open file");
			ioe.printStackTrace();
		}
		return ;
	}
}
