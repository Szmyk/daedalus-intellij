package org.avallach.daedalus.parser;

import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static generated.GeneratedTypes.*;

%%

%{
  public _DaedalusLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _DaedalusLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s

IDENTIFIER_TOKEN=[a-zA-Z_][:jletterdigit:]+
STRING_LITERAL=\"[^\r\n\"]*\"
INTEGER_LITERAL=[0-9]+
FLOAT_LITERAL=[0-9]+\.[0-9]+
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*([^*]|\*+[^*/])*(\*+"/")?
WHITESPACE=[ \t\r\n]+

%%
<YYINITIAL> {
  {WHITE_SPACE}           { return com.intellij.psi.TokenType.WHITE_SPACE; }

  "class"                 { return CLASS; }
  "prototype"             { return PROTOTYPE; }
  "instance"              { return INSTANCE; }
  "const"                 { return CONST; }
  "var"                   { return VAR; }
  "func"                  { return FUNC; }
  "if"                    { return IF; }
  "else"                  { return ELSE; }
  "return"                { return RETURN; }
  "void"                  { return VOID; }
  "int"                   { return INT; }
  "float"                 { return FLOAT; }
  "string"                { return STRING; }
  "{"                     { return LEFT_BRACE; }
  "}"                     { return RIGHT_BRACE; }
  "("                     { return LEFT_PAREN; }
  ")"                     { return RIGHT_PAREN; }
  "["                     { return LEFT_BRACKET; }
  "]"                     { return RIGHT_BRACKET; }
  "+"                     { return PLUS_OP; }
  "-"                     { return MINUS_OP; }
  "*"                     { return MUL_OP; }
  "/"                     { return DIV_OP; }
  "%"                     { return REM_OP; }
  "="                     { return ASSIGNMENT_OP; }
  "+="                    { return ASSIGNMENT_PLUS_OP; }
  "-="                    { return ASSIGNMENT_MINUS_OP; }
  "*="                    { return ASSIGNMENT_MUL_OP; }
  "/="                    { return ASSIGNMENT_DIV_OP; }
  "<"                     { return LESS_THAN; }
  ">"                     { return GREATER_THAN; }
  "=="                    { return EQUALS_OP; }
  "!="                    { return NOT_EQUALS_OP; }
  "<="                    { return LESS_THAN_OR_EQUAL_OP; }
  ">="                    { return GREATER_THAN_OR_EQUAL_OP; }
  "<<"                    { return BIT_SHIFT_LEFT_OP; }
  ">>"                    { return BIT_SHIFT_RIGHT_OP; }
  "|"                     { return BIT_OR_OP; }
  "&"                     { return BIT_AND_OP; }
  "~"                     { return BIT_NEGATION_OP; }
  "&&"                    { return AND_OP; }
  "||"                    { return OR_OP; }
  "!"                     { return NEGATION_OP; }
  "."                     { return DOT; }
  ","                     { return COMMA; }
  ";"                     { return SEMICOLON; }

  {IDENTIFIER_TOKEN}      { return IDENTIFIER_TOKEN; }
  {STRING_LITERAL}        { return STRING_LITERAL; }
  {INTEGER_LITERAL}       { return INTEGER_LITERAL; }
  {FLOAT_LITERAL}         { return FLOAT_LITERAL; }
  {LINE_COMMENT}          { return LINE_COMMENT; }
  {BLOCK_COMMENT}         { return BLOCK_COMMENT; }
  {WHITESPACE}            { return WHITESPACE; }

}

[^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
