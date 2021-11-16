package generatedSources;
import java_cup.runtime.*;
import utils.RascalSymbol;
import generatedSources.sym;

%%

%public
%class Lexico
%unicode
%cup

%{

   StringBuffer string = new StringBuffer();

private Symbol symbol(int type) {
	return new utils.RascalSymbol(type, yyline+1, yycolumn+1);
}

private Symbol symbol(int type, Object value) {
	return new utils.RascalSymbol(type, yyline+1, yycolumn+1, value);
}
  
%}

/* Comments */
startComment = \{ 
endComment = \}
contentComment= [^}]*
Comment = {startComment}{contentComment}{endComment}

/* Integer  */
DecimalLiteral 	= 0 | [1-9][0-9]*
IntegerNumber 	= {DecimalLiteral}

/*String*/
String = "'"[^\n\r\']+"'"

UnicodeLetter  = [:letter:]
UnicodeDigit   = [:digit:]
Letter         = {UnicodeLetter}|"_"

/* Identifiers */
Identifier     = {Letter}({Letter} | {UnicodeDigit})*
Number = {IntegerNumber}


/* White spaces*/
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

%%

<YYINITIAL> {
    /* White spaces */
    {WhiteSpace}				    { /*Ignore*/ }

    /*Comments*/
    {Comment}						{ /*Ignore*/ }

	/* Reserved Words */

    "program"     { return symbol(sym.T_PROGRAM); }
    "var"         { return symbol(sym.T_VAR); }
    "procedure"   { return symbol(sym.T_PROCEDURE); }
    "function"    { return symbol(sym.T_FUNCTION); }
    "begin"       { return symbol(sym.T_BEGIN); }
    "end"         { return symbol(sym.T_END); }
    "false"       { return symbol(sym.T_FALSE); }
    "true"        { return symbol(sym.T_TRUE); }
    "if"          { return symbol(sym.T_IF); }
    "then"        { return symbol(sym.T_THEN); }
    "else"        { return symbol(sym.T_ELSE); }
    "while"       { return symbol(sym.T_WHILE); }
    "do"          { return symbol(sym.T_DO); }
    "read"        { return symbol(sym.T_READ); }
    "write"       { return symbol(sym.T_WRITE); }
    "and"         { return symbol(sym.T_AND); }
    "or"          { return symbol(sym.T_OR); }
    "not"         { return symbol(sym.T_NOT); }
    "div"         { return symbol(sym.T_DIV); }
    "boolean"     { return symbol(sym.T_BOOL); }


    /* Booleans */
    "true"                     	 	{ return symbol(sym.BOOLEAN_LITERAL, new Boolean(true)); }
    "false"                         { return symbol(sym.BOOLEAN_LITERAL, new Boolean(false)); }

	/* Other */

    /* Identifier*/
    {Identifier} 					{ return symbol(sym.T_IDENT, yytext());}

    "("           { return symbol(sym.T_ABRE_PARENTESES); }
    ")"           { return symbol(sym.T_FECHA_PARENTESES); }
    "."           { return symbol(sym.T_PONTO); }
    ","           { return symbol(sym.T_VIRGULA); }
    ";"           { return symbol(sym.T_PONTO_E_VIRGULA); }
    "+"           { return symbol(sym.T_MAIS); }
    "-"           { return symbol(sym.T_MENOS); }
    "*"           { return symbol(sym.T_MULTIPLICACAO); }
    "="           { return symbol(sym.T_IGUAL); }
    "<>"          { return symbol(sym.T_NAO_IGUAL); }
    ">"           { return symbol(sym.T_MAIOR_QUE); }
    "<"           { return symbol(sym.T_MENOR_QUE); }
    ">="          { return symbol(sym.T_MAIOR_IGUAL); }
    "<="          { return symbol(sym.T_MENOR_IGUAL); }
    ":="          { return symbol(sym.T_ATRIBUICAO); }
    ":"           { return symbol(sym.T_DOIS_PONTOS); }

    /* String literal */

    /* Number literal */
    {IntegerNumber}                 { return symbol(sym.T_NUM, new Integer(yytext())); }

}