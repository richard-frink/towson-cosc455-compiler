package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

import java.awt.color.CMMException;
import java.util.ArrayList;

import edu.towson.cs.rsussa1.tokens.*;
import edu.towson.cs.rsussa1.compiler_implementation.Compiler;
import edu.towson.cosc.cosc455.interfaces.*;

public class MySynAnalyzer implements SyntaxAnalyzer {

	private ArrayList<LegalToken> TAGS;
	protected boolean errorFound;
	
	public MySynAnalyzer(){
		//i believe all these may be unnecessary
		//but i am going to leave it for now..........
		//TAGS = new ArrayList<LegalToken>();
		//TAGS.add(new Address_Close()); TAGS.add(new Address_Open()); TAGS.add(new Angle_Close()); TAGS.add(new Angle_Open());
		//TAGS.add(new Audio()); TAGS.add(new Bold()); TAGS.add(new Carrot()); TAGS.add(new Curly_Close());
		//TAGS.add(new Curly_Open()); TAGS.add(new Hash_Begin()); TAGS.add(new Hash_End()); TAGS.add(new Italic());
		//TAGS.add(new List_Close()); TAGS.add(new List_Open()); TAGS.add(new LP_Close()); TAGS.add(new LP_Open());
		//TAGS.add(new Page_Break()); TAGS.add(new Text()); TAGS.add(new Var_Def()); TAGS.add(new Var_End());
		//TAGS.add(new Var_Use()); TAGS.add(new Video());
	}
	
	@Override
	public void markdown() throws CMMException {
		resetError();
		
		//check every token and call the appropriate methods and what-not
		
		if (!(new Hash_Begin()).isLegal(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR - A document begin tag was expected when '" + Compiler.currentToken + "' was found.");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}

	@Override
	public void head() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void title() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void body() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void paragraph() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void innerText() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void variableDefine() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void variableUse() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void bold() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void italics() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void listitem() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void innerItem() throws CMMException {
		// TODO Auto-generated method stub

	}

	@Override
	public void link() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void audio() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void video() throws CompilerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void newline() throws CMMException {
		// TODO Auto-generated method stub

	}
	
	void setError(){errorFound = true;}
	void resetError(){errorFound = false;}
	boolean getError(){return errorFound;}
}
