// This is a generated file. Not intended for manual editing.
package org.avallach.daedalus.parser.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.avallach.daedalus.parser.psi.impl.*;

public interface DaedalusTypes {

  IElementType CLASS_DEF = new ElementType("CLASS_DEF");
  IElementType CONST_DEF = new ElementType("CONST_DEF");
  IElementType FUNCTION_DEF = new ElementType("FUNCTION_DEF");
  IElementType INSTANCE_DECL = new ElementType("INSTANCE_DECL");
  IElementType INSTANCE_DEF = new ElementType("INSTANCE_DEF");
  IElementType NAME_NODE = new ElementType("NAME_NODE");
  IElementType PROTOTYPE_DEF = new ElementType("PROTOTYPE_DEF");
  IElementType REFERENCE_NODE = new ElementType("REFERENCE_NODE");
  IElementType VAR_DECL = new ElementType("VAR_DECL");

  IElementType AND_OP = new TokenType("&&");
  IElementType ASSIGNMENT_DIV_OP = new TokenType("/=");
  IElementType ASSIGNMENT_MINUS_OP = new TokenType("-=");
  IElementType ASSIGNMENT_MUL_OP = new TokenType("*=");
  IElementType ASSIGNMENT_OP = new TokenType("=");
  IElementType ASSIGNMENT_PLUS_OP = new TokenType("+=");
  IElementType BIT_AND_OP = new TokenType("&");
  IElementType BIT_NEGATION_OP = new TokenType("~");
  IElementType BIT_OR_OP = new TokenType("|");
  IElementType BIT_SHIFT_LEFT_OP = new TokenType("<<");
  IElementType BIT_SHIFT_RIGHT_OP = new TokenType(">>");
  IElementType BLOCK_COMMENT = new TokenType("BLOCK_COMMENT");
  IElementType CLASS = new TokenType("class");
  IElementType COMMA = new TokenType(",");
  IElementType CONST = new TokenType("const");
  IElementType DIV_OP = new TokenType("/");
  IElementType DOT = new TokenType(".");
  IElementType ELSE = new TokenType("else");
  IElementType EQUALS_OP = new TokenType("==");
  IElementType FLOAT = new TokenType("float");
  IElementType FLOAT_LITERAL = new TokenType("FLOAT_LITERAL");
  IElementType FUNC = new TokenType("func");
  IElementType GREATER_THAN = new TokenType(">");
  IElementType GREATER_THAN_OR_EQUAL_OP = new TokenType(">=");
  IElementType IDENTIFIER_TOKEN = new TokenType("IDENTIFIER_TOKEN");
  IElementType IF = new TokenType("if");
  IElementType INSTANCE = new TokenType("instance");
  IElementType INT = new TokenType("int");
  IElementType INTEGER_LITERAL = new TokenType("INTEGER_LITERAL");
  IElementType LEFT_BRACE = new TokenType("{");
  IElementType LEFT_BRACKET = new TokenType("[");
  IElementType LEFT_PAREN = new TokenType("(");
  IElementType LESS_THAN = new TokenType("<");
  IElementType LESS_THAN_OR_EQUAL_OP = new TokenType("<=");
  IElementType LINE_COMMENT = new TokenType("LINE_COMMENT");
  IElementType MINUS_OP = new TokenType("-");
  IElementType MUL_OP = new TokenType("*");
  IElementType NEGATION_OP = new TokenType("!");
  IElementType NOT_EQUALS_OP = new TokenType("!=");
  IElementType OR_OP = new TokenType("||");
  IElementType PLUS_OP = new TokenType("+");
  IElementType PROTOTYPE = new TokenType("prototype");
  IElementType REM_OP = new TokenType("%");
  IElementType RETURN = new TokenType("return");
  IElementType RIGHT_BRACE = new TokenType("}");
  IElementType RIGHT_BRACKET = new TokenType("]");
  IElementType RIGHT_PAREN = new TokenType(")");
  IElementType SEMICOLON = new TokenType(";");
  IElementType STRING = new TokenType("string");
  IElementType STRING_LITERAL = new TokenType("STRING_LITERAL");
  IElementType VAR = new TokenType("var");
  IElementType VOID = new TokenType("void");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == CLASS_DEF) {
        return new ClassDefImpl(node);
      }
      else if (type == CONST_DEF) {
        return new ConstDefImpl(node);
      }
      else if (type == FUNCTION_DEF) {
        return new FunctionDefImpl(node);
      }
      else if (type == INSTANCE_DECL) {
        return new InstanceDeclImpl(node);
      }
      else if (type == INSTANCE_DEF) {
        return new InstanceDefImpl(node);
      }
      else if (type == NAME_NODE) {
        return new NameNodeImpl(node);
      }
      else if (type == PROTOTYPE_DEF) {
        return new PrototypeDefImpl(node);
      }
      else if (type == REFERENCE_NODE) {
        return new ReferenceNodeImpl(node);
      }
      else if (type == VAR_DECL) {
        return new VarDeclImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
