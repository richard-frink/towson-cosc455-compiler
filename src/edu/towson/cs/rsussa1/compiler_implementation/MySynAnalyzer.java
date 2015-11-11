package edu.towson.cs.rsussa1.compiler_implementation;

/**
 * COSC455 - Programming Languages and Implementation
 * 
 * Syntax analyzer class for the given HTML Markdown grammar
 * 
 * Richard Sussan
 */

import java.awt.color.CMMException;

import edu.towson.cs.rsussa1.tokens.*;
import edu.towson.cs.rsussa1.compiler_implementation.Compiler;
import edu.towson.cosc.cosc455.interfaces.*;

public class MySynAnalyzer implements SyntaxAnalyzer {
	
	/**
	 * fully implements all of the provided HTML markdown grammer by checking a token and then retrieving new tokens,
	 * and where the grammar is not followed then an error is thrown with a good description as to why
	 * the grammar was incorrect
	 * 
	 */
	
	@Override
	public void markdown() throws CMMException {
		try{
			if (!(new Hash_Begin()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A document begin tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if((new Var_Def()).isLegal(Compiler.currentToken)){
				variableDefine();
			}
			if((new Carrot()).isLegal(Compiler.currentToken)){
				head();
			}
			body();
			if (!(new Hash_End()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A document end tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.SemanticAna.createHtml();
			}
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void head() throws CompilerException {		
		try{
			if (!(new Carrot()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A head begin tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			title();
			if (!(new Carrot()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A head end tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
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
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Angle_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A title close tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void body() throws CompilerException {
		try{
			if((new Curly_Open()).isLegal(Compiler.currentToken)){
				paragraph();
				body();
			}
			else if((new Var_Use()).isLegal(Compiler.currentToken) || (new Bold()).isLegal(Compiler.currentToken) || (new Italic()).isLegal(Compiler.currentToken) || 
					(new List_Open()).isLegal(Compiler.currentToken) || (new Audio()).isLegal(Compiler.currentToken) || (new Video()).isLegal(Compiler.currentToken) || 
					(new LP_Open()).isLegal(Compiler.currentToken) || (new Page_Break()).isLegal(Compiler.currentToken) || (new Text()).isLegal(Compiler.currentToken)){
				innerText();
				body();
			}
			else if((new Page_Break()).isLegal(Compiler.currentToken)){
				newline();
				body();
			}
			else if((new Hash_End()).isLegal(Compiler.currentToken));
			else{
				throw new CompilerException("SYNTAX ERROR - No legal syntax was found. '" + Compiler.currentToken + "' was found.");
			}
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paragraph() throws CompilerException {
		try{
			if (!(new Curly_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A paragraph begin tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if((new Var_Def()).isLegal(Compiler.currentToken)){
				variableDefine();
			}
			innerText();
			if (!(new Curly_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A paragraph end tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void innerText() throws CompilerException {
		try{
			if((new Var_Use()).isLegal(Compiler.currentToken)){
				variableUse();
				innerText();
			}
			else if((new Bold()).isLegal(Compiler.currentToken)){
				bold();
				innerText();
			}
			else if((new Italic()).isLegal(Compiler.currentToken)){
				italics();
				innerText();
			}
			else if((new List_Open()).isLegal(Compiler.currentToken)){
				listitem();
				innerText();
			}
			else if((new Audio()).isLegal(Compiler.currentToken)){
				audio();
				innerText();
			}
			else if((new Video()).isLegal(Compiler.currentToken)){
				video();
				innerText();
			}
			else if((new LP_Open()).isLegal(Compiler.currentToken)){
				link();
				innerText();
			}
			else if((new Page_Break()).isLegal(Compiler.currentToken)){
				newline();
				innerText();
			}
			else if((new Text()).isLegal(Compiler.currentToken)){
				addToParseTree();
				Compiler.Lexer.getNextToken();
				innerText();
			}
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void variableDefine() throws CompilerException {		
		
		try{		
			if ((new Var_Def()).isLegal(Compiler.currentToken)){
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			else{
				throw new CompilerException("SYNTAX ERROR - A variable definition tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			if ((new Text()).isLegal(Compiler.currentToken)){
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			else{
				throw new CompilerException("SYNTAX ERROR - Valid text was expected when '" + Compiler.currentToken + "' was found.");
			}
			if ((new EQSign()).isLegal(Compiler.currentToken)){
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			else{
				throw new CompilerException("SYNTAX ERROR - An equals sign was expected when '" + Compiler.currentToken + "' was found.");
			}
			if ((new Text()).isLegal(Compiler.currentToken)){
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			else{
				throw new CompilerException("SYNTAX ERROR - Valid text was expected when '" + Compiler.currentToken + "' was found.");
			}
			if ((new Var_End()).isLegal(Compiler.currentToken)){
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			else{
				throw new CompilerException("SYNTAX ERROR - A variable end tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			if ((new Var_Def()).isLegal(Compiler.currentToken)){
				variableDefine();
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
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Var_End()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A variable end tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
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
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Bold()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A bold tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
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
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Italic()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An italic tag was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void listitem() throws CompilerException {
		try{
			if (!(new List_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A list begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			innerItem();
			if (!(new List_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An list end token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if((new List_Open()).isLegal(Compiler.currentToken)){
				listitem();
			}
		}catch(CompilerException e){
			e.printStackTrace();
		}
	}

	@Override
	public void innerItem() throws CMMException {
		try{
			if((new LP_Open()).isLegal(Compiler.currentToken)){
				link();
				innerItem();
			}
			else if((new Bold()).isLegal(Compiler.currentToken)){
				bold();
				innerItem();
			}
			else if((new Italic()).isLegal(Compiler.currentToken)){
				italics();
				innerItem();
			}
			else if((new Text()).isLegal(Compiler.currentToken)){
				addToParseTree();
				Compiler.Lexer.getNextToken();
				innerItem();
			}
			else if((new Var_Use()).isLegal(Compiler.currentToken)){
				variableUse();
				innerItem();
			}
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void link() throws CompilerException {
		try{
			if (!(new LP_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A link begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new LP_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - A link end token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Address_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Address_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address end token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
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
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Address_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Address_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address end token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
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
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Address_Open()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Text()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - Valid Text was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
			if (!(new Address_Close()).isLegal(Compiler.currentToken)){
				throw new CompilerException("SYNTAX ERROR - An address begin token was expected when '" + Compiler.currentToken + "' was found.");
			}
			else{
				addToParseTree();
				Compiler.Lexer.getNextToken();
			}
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
				addToParseTree();
				Compiler.Lexer.getNextToken();
		} catch(CompilerException e) {
			e.printStackTrace();
		}
	}

	//adds legal tokens to the parse tree in the semantic analyzer
	public void addToParseTree(){
		Compiler.SemanticAna.addToStack(Compiler.currentToken);
	}
}
