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
	private static Stack<String> t;
	
	private void createHtmlStack(){
		while(!(myStack.isEmpty())){
			System.out.println(myStack.pop());
		}
		while(!myStack.isEmpty()){
			t = new Stack<String>();
			if((t.pop()).equalsIgnoreCase("<")){
				
			}
			else if((t.pop()).equalsIgnoreCase(">")){
				
			}
			else if((t.pop()).equalsIgnoreCase("^")){
				
			}
			else if((t.pop()).equalsIgnoreCase("{")){
				
			}
			else if((t.pop()).equalsIgnoreCase("}")){
				
			}
			else if((t.pop()).equalsIgnoreCase("#begin")){
				
			}
			else if((t.pop()).equalsIgnoreCase("#end")){
				
			}
			else if((t.pop()).equalsIgnoreCase("*")){
				
			}
			else if((t.pop()).equalsIgnoreCase("**")){
				
			}
			else if((t.pop()).equalsIgnoreCase("~")){
				
			}
			else if((t.pop()).equalsIgnoreCase("+")){
				
			}
			else if((t.pop()).equalsIgnoreCase(";")){
				
			}
			
		}
		/**
		 * create an if statement for every possible string and check to see if it matches up
		 * 
		 * <
		 * >
		 * ^
		 * {
		 * }
		 * #begin
		 * #end
		 * *
		 * **
		 * ~
		 * +
		 * ;
		 * 
		 * 
		 * otherwise check for text
		 * 
		 * SPECIAL CASES
		 * 
		 * if text with no var end before it then just push to stack
		 * 
		 * if text w/ that (above) then create temp stack and pop off all things into temp stack
		 * until you find definition of variable, then push back on nothing for var-use and
		 * in all var use cases of that variable push back on it's definition instead (check if = var use, else push and pop)
		 * 
		 * if see an end to a link, capture the text inside and then determine if it is just a link or if it is a video el.
		 * audio el. or just a hyperlink, then push onto the stack the appropriate representation of the data
		 */
	}
	
	//passed a backwards stack and as stack is popped off it is  concatenated into a string
	public void createHtml(){
		createHtmlStack();
		String temp = t.pop();
		while(!(t.isEmpty())){
			temp = temp + " " + t.pop();
		}
		createNewFile(temp);
		
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

		} catch(IOException ioe) {
			ioe.printStackTrace();
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
