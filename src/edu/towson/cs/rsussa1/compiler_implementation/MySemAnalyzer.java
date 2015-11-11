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

import edu.towson.cs.rsussa1.tokens.*;

public class MySemAnalyzer {
	public static Stack<String> myStack = new Stack<String>();
	private static Stack<String> htmlStack = new Stack<String>();
	private static Stack<String> t;
	
	public void createHtml(){
		createHtmlStack();
		
		String temp = "";
		temp = htmlStack.pop();
		
		while(!htmlStack.isEmpty()){
			String element = htmlStack.pop();
			temp = temp + " " + element;
		}
		
		createNewFile(temp);		
	}
	
	public void addToStack(String str){
		myStack.push(str);
	}
	
	private void createHtmlStack(){
		while(!myStack.isEmpty()){
			String temp = myStack.pop();
			if(temp.equalsIgnoreCase("<")){
				htmlStack.push((new Angle_Open()).getHTML(true));
			}
			else if(temp.equalsIgnoreCase(">")){
				htmlStack.push((new Angle_Close()).getHTML(true));
			}
			else if(temp.equalsIgnoreCase("^")){
				t = new Stack<String>();
				boolean exists = false;
				while(!htmlStack.isEmpty()){
					String element = htmlStack.pop();
					t.push(element);
					if(element.equalsIgnoreCase((new Carrot()).getHTML(false))){
						exists = true;
						break;
					}
				}
				while(!t.isEmpty()){
					htmlStack.push(t.pop());
				}
				if(exists == false){
					htmlStack.push((new Carrot()).getHTML(false));
				}
				else{
					htmlStack.push((new Carrot()).getHTML(true));
				}
			}
			else if(temp.equalsIgnoreCase("{")){
				htmlStack.push((new Curly_Open()).getHTML(true));
			}
			else if(temp.equalsIgnoreCase("}")){
				htmlStack.push((new Curly_Close()).getHTML(true));
			}
			else if(temp.equalsIgnoreCase("#begin")){
				htmlStack.push((new Hash_Begin()).getHTML(true));
			}
			else if(temp.equalsIgnoreCase("#end")){
				htmlStack.push((new Hash_End()).getHTML(true));
			}
			else if(temp.equalsIgnoreCase("*")){
				t = new Stack<String>();
				boolean exists = false;
				while(!htmlStack.isEmpty()){
					String element = htmlStack.pop();
					t.push(element);
					if(element.equalsIgnoreCase((new Italic()).getHTML(false))){
						exists = true;
						break;
					}
				}
				while(!t.isEmpty()){
					htmlStack.push(t.pop());
				}
				if(exists == false){
					htmlStack.push((new Italic()).getHTML(false));
				}
				else{
					htmlStack.push((new Italic()).getHTML(true));
				}
			}
			else if(temp.equalsIgnoreCase("**")){
				t = new Stack<String>();
				boolean exists = false;
				while(!htmlStack.isEmpty()){
					String element = htmlStack.pop();
					t.push(element);
					if(element.equalsIgnoreCase((new Bold()).getHTML(false))){
						exists = true;
						break;
					}
				}
				while(!t.isEmpty()){
					htmlStack.push(t.pop());
				}
				if(exists == false){
					htmlStack.push((new Bold()).getHTML(false));
				}
				else{
					htmlStack.push((new Bold()).getHTML(true));
				}
			}
			else if(temp.equalsIgnoreCase("~")){
				htmlStack.push((new Page_Break()).getHTML(true));
			}
			else if(temp.equalsIgnoreCase("+")){
				htmlStack.push((new List_Open()).getHTML(true));
			}
			else if(temp.equalsIgnoreCase(";")){
				htmlStack.push((new List_Close()).getHTML(true));
			}
			else if((new Text()).isLegal(temp)){
				htmlStack.push(temp);
			}
			else if((new Var_End()).isLegal(temp)){
				String var_name = trimString(myStack.pop());
				replaceVariables(var_name);
			}
			else if(temp.equalsIgnoreCase(")")){
				String newLink = "";
				String address = myStack.pop();
				myStack.pop();
				temp = myStack.pop();
				if(temp.equalsIgnoreCase("@")){
					newLink = "<audio controls>  <source src=\"" + address + "\">    </audio> "; 
				}
				else if(temp.equalsIgnoreCase("%")){
					newLink = "<iframe src=\"" + address + "\"/> "; 
				}
				else{
					myStack.pop();
					newLink = "<a href = \"" + address + "\">" + myStack.pop() + "</a>"; 
					myStack.pop();
				}
				htmlStack.push(newLink);
			}
		}
	}
	
	private void replaceVariables(String var_name){
		String var_def = "";
		t = new Stack<String>();
		while(!myStack.isEmpty()){
			String check = myStack.pop();
			if(!(new Text()).isLegal(check)){
				check = trimString(check);
			}
			if(check.equalsIgnoreCase("$end")){
				String temp_var_def = trimString(myStack.pop());
				check = myStack.pop();
				if(check.equalsIgnoreCase("=")){
					String temp_name = trimString(myStack.pop());
					if(temp_name.equalsIgnoreCase(var_name)){
						var_def = temp_var_def;
						myStack.pop();
						break;
					}
					else{
						t.push("$end");
						t.push(temp_var_def);
						t.push("=");
						t.push(temp_name);
						t.push(myStack.pop());
					}
				}
				else if(check.equalsIgnoreCase("$use")){
					if(!temp_var_def.equalsIgnoreCase(var_name)){
						t.push("$end");
					}
					t.push(temp_var_def);
					t.push("$use");
				}
				else{
					t.push(check);
				}
			}
			else if(check.equalsIgnoreCase("$use"));
			else{
				t.push(check);
			}
		}
		try{
			if(myStack.isEmpty()){
				throw new CompilerException("Static Semantic Error - No variable definition found for \"" + var_name + "\" !");
			}
		} catch(CompilerException e){
			e.printStackTrace();
			System.exit(0);
		}
		while(!t.isEmpty()){
			String temp = t.pop();
			if(temp.equalsIgnoreCase("$def")){
				myStack.push(temp);
				myStack.push(t.pop());
				myStack.push(t.pop());
				myStack.push(t.pop());
				myStack.push(t.pop());
			}
			else if(temp.equalsIgnoreCase("$use")){
				String temp_var_name = t.pop();
				if(temp_var_name.equalsIgnoreCase(var_name)){
					myStack.push(var_def);
				}
				else{
					myStack.push("$use");
					myStack.push(temp_var_name);
					myStack.push(t.pop());
				}
			}
			else{
				myStack.push(temp);
			}
		}
	}
	
	private String trimString(String needsTrim){
		String trimmed = "";
		for(int i = 0; i < needsTrim.length(); i++){
			if(!(needsTrim.charAt(i) == ' ')){
				trimmed = trimmed + needsTrim.charAt(i);
			}
		}
		return trimmed;
	}
	
	public void createNewFile(String sourceFile){
		try {
			String temp = Compiler.file + ".html";
			File file = new File(temp);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sourceFile);
			bw.close();

			openHTMLFileInBrowser(temp);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void openHTMLFileInBrowser(String htmlFileStr){
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
