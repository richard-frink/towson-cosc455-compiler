package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Richard Sussan
 */

import java.awt.color.CMMException;
import edu.towson.cs.rsussa1.tokens.*;
import edu.towson.cs.rsussa1.compiler_implementation.Compiler;
import edu.towson.cosc.cosc455.interfaces.*;

public class MySynAnalyzer implements SyntaxAnalyzer {
	
	@Override
	public void markdown() throws CMMException {
		//check every token and calls the appropriate methods and what-not
		
		try{
			if (!(new Hash_Begin()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A document begin tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			variableDefine();
			head();
			body();
			if (!(new Hash_End()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A document end tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void head() throws CompilerException {
		// i need to put in a line that checks if the currentToken is the possible match or if it skips the uneccesary token possibility
		
		
		try{
			if (!(new Carrot()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A head begin tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			title();
			if (!(new Carrot()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A head end tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void title() throws CompilerException {
		try{
			if (!(new Angle_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A title open tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Angle_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A title close was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
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
		
		try{		
		//check if (the token is hash_begin) then (check if (stack is empty) then (break) else (throw exception))
		//if else (the var_def)
		//else throw exception
		
		
			if ((new Var_Def()).isLegal(Compiler.currentToken)){
				Compiler.Lexer.getNextToken();
				if ((new Text()).isLegal(Compiler.currentToken)){
					Compiler.Lexer.getNextToken();
					if ((new EQSign()).isLegal(Compiler.currentToken)){
						Compiler.Lexer.getNextToken();
						if ((new Text()).isLegal(Compiler.currentToken)){
							Compiler.Lexer.getNextToken();
							if ((new Var_End()).isLegal(Compiler.currentToken)){
								Compiler.Lexer.getNextToken();
							}
							else{
								throw new CompilerException("");
							}
						}
						else{
							throw new CompilerException("");
						}
					}
					else{
						throw new CompilerException("");
					}
				}
				else{
					throw new CompilerException("");
				}
			}
			else{
				throw new CompilerException("");
			}
		}catch(CompilerException e){
			e.printStackTrace();
		}
	}

	@Override
	public void variableUse() throws CompilerException {
		try{
			if (!(new Var_Use()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A variable use tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Var_End()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A variable end tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bold() throws CompilerException {
		try{
			if (!(new Bold()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A bold tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Bold()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A bold tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void italics() throws CompilerException {
		try{
			if (!(new Italic()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An italic tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Italic()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An italic tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
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
		try{
			if (!(new LP_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A link begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new LP_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A link end token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Address_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Address_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address end token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void audio() throws CompilerException {
		try{
			if (!(new Audio()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An audio tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Address_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Address_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address end token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void video() throws CompilerException {
		try{
			if (!(new Video()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A video tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Address_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
			if (!(new Address_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void newline() throws CMMException {
		try{
			if (!(new Page_Break()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A newline token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}
}
