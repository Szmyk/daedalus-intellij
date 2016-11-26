// This is a generated file. Not intended for manual editing.
package org.avallach.daedalus.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.avallach.daedalus.parser.psi.DaedalusTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class Parser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == CLASS_DEF) {
      r = ClassDef(b, 0);
    }
    else if (t == CONST_DEF) {
      r = ConstDef(b, 0);
    }
    else if (t == FUNCTION_DEF) {
      r = FunctionDef(b, 0);
    }
    else if (t == INSTANCE_DECL) {
      r = InstanceDecl(b, 0);
    }
    else if (t == INSTANCE_DEF) {
      r = InstanceDef(b, 0);
    }
    else if (t == NAME_NODE) {
      r = NameNode(b, 0);
    }
    else if (t == PROTOTYPE_DEF) {
      r = PrototypeDef(b, 0);
    }
    else if (t == REFERENCE_NODE) {
      r = ReferenceNode(b, 0);
    }
    else if (t == VAR_DECL) {
      r = VarDecl(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return DaedalusFile(b, l + 1);
  }

  /* ********************************************************** */
  // Mult [ ( "+" | "-" ) Add ]
  static boolean Add(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Add")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Mult(b, l + 1);
    r = r && Add_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ ( "+" | "-" ) Add ]
  private static boolean Add_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Add_1")) return false;
    Add_1_0(b, l + 1);
    return true;
  }

  // ( "+" | "-" ) Add
  private static boolean Add_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Add_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Add_1_0_0(b, l + 1);
    r = r && Add(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "+" | "-"
  private static boolean Add_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Add_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS_OP);
    if (!r) r = consumeToken(b, MINUS_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "{" [ Expression { "," Expression }* ] "}"
  static boolean ArrayLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayLiteral")) return false;
    if (!nextTokenIs(b, LEFT_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_BRACE);
    r = r && ArrayLiteral_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ Expression { "," Expression }* ]
  private static boolean ArrayLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayLiteral_1")) return false;
    ArrayLiteral_1_0(b, l + 1);
    return true;
  }

  // Expression { "," Expression }*
  private static boolean ArrayLiteral_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayLiteral_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    r = r && ArrayLiteral_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { "," Expression }*
  private static boolean ArrayLiteral_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayLiteral_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ArrayLiteral_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArrayLiteral_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Expression
  private static boolean ArrayLiteral_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayLiteral_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ComplexReference ( "=" | "+=" | "-=" | "*=" | "/=" ) Expression
  static boolean Assignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Assignment")) return false;
    if (!nextTokenIs(b, IDENTIFIER_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ComplexReference(b, l + 1);
    r = r && Assignment_1(b, l + 1);
    p = r; // pin = 2
    r = r && Expression(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // "=" | "+=" | "-=" | "*=" | "/="
  private static boolean Assignment_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Assignment_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGNMENT_OP);
    if (!r) r = consumeToken(b, ASSIGNMENT_PLUS_OP);
    if (!r) r = consumeToken(b, ASSIGNMENT_MINUS_OP);
    if (!r) r = consumeToken(b, ASSIGNMENT_MUL_OP);
    if (!r) r = consumeToken(b, ASSIGNMENT_DIV_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Equal [ "&" BitAnd ]
  static boolean BitAnd(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitAnd")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Equal(b, l + 1);
    r = r && BitAnd_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "&" BitAnd ]
  private static boolean BitAnd_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitAnd_1")) return false;
    BitAnd_1_0(b, l + 1);
    return true;
  }

  // "&" BitAnd
  private static boolean BitAnd_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitAnd_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BIT_AND_OP);
    r = r && BitAnd(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BitAnd [ "|" BitOr ]
  static boolean BitOr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitOr")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BitAnd(b, l + 1);
    r = r && BitOr_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "|" BitOr ]
  private static boolean BitOr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitOr_1")) return false;
    BitOr_1_0(b, l + 1);
    return true;
  }

  // "|" BitOr
  private static boolean BitOr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitOr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BIT_OR_OP);
    r = r && BitOr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Add [ ( "<<" | ">>" ) BitShift ]
  static boolean BitShift(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitShift")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Add(b, l + 1);
    r = r && BitShift_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ ( "<<" | ">>" ) BitShift ]
  private static boolean BitShift_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitShift_1")) return false;
    BitShift_1_0(b, l + 1);
    return true;
  }

  // ( "<<" | ">>" ) BitShift
  private static boolean BitShift_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitShift_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BitShift_1_0_0(b, l + 1);
    r = r && BitShift(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "<<" | ">>"
  private static boolean BitShift_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitShift_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BIT_SHIFT_LEFT_OP);
    if (!r) r = consumeToken(b, BIT_SHIFT_RIGHT_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "class" NameNode "{" { VarDecl ";" }* "}"
  public static boolean ClassDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassDef")) return false;
    if (!nextTokenIs(b, CLASS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_DEF, null);
    r = consumeToken(b, CLASS);
    p = r; // pin = 1
    r = r && report_error_(b, NameNode(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LEFT_BRACE)) && r;
    r = p && report_error_(b, ClassDef_3(b, l + 1)) && r;
    r = p && consumeToken(b, RIGHT_BRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // { VarDecl ";" }*
  private static boolean ClassDef_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassDef_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ClassDef_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ClassDef_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // VarDecl ";"
  private static boolean ClassDef_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassDef_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarDecl(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BitShift [ ( "<" | ">" | "<=" | ">=" ) Comparison ]
  static boolean Comparison(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comparison")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BitShift(b, l + 1);
    r = r && Comparison_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ ( "<" | ">" | "<=" | ">=" ) Comparison ]
  private static boolean Comparison_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comparison_1")) return false;
    Comparison_1_0(b, l + 1);
    return true;
  }

  // ( "<" | ">" | "<=" | ">=" ) Comparison
  private static boolean Comparison_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comparison_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Comparison_1_0_0(b, l + 1);
    r = r && Comparison(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "<" | ">" | "<=" | ">="
  private static boolean Comparison_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comparison_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LESS_THAN);
    if (!r) r = consumeToken(b, GREATER_THAN);
    if (!r) r = consumeToken(b, LESS_THAN_OR_EQUAL_OP);
    if (!r) r = consumeToken(b, GREATER_THAN_OR_EQUAL_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ComplexReferenceNode [ "." ComplexReferenceNode ]
  static boolean ComplexReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComplexReference")) return false;
    if (!nextTokenIs(b, IDENTIFIER_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ComplexReferenceNode(b, l + 1);
    r = r && ComplexReference_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "." ComplexReferenceNode ]
  private static boolean ComplexReference_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComplexReference_1")) return false;
    ComplexReference_1_0(b, l + 1);
    return true;
  }

  // "." ComplexReferenceNode
  private static boolean ComplexReference_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComplexReference_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && ComplexReferenceNode(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ReferenceNode [ "[" SimpleValue "]" ]
  static boolean ComplexReferenceNode(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComplexReferenceNode")) return false;
    if (!nextTokenIs(b, IDENTIFIER_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ReferenceNode(b, l + 1);
    r = r && ComplexReferenceNode_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "[" SimpleValue "]" ]
  private static boolean ComplexReferenceNode_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComplexReferenceNode_1")) return false;
    ComplexReferenceNode_1_0(b, l + 1);
    return true;
  }

  // "[" SimpleValue "]"
  private static boolean ComplexReferenceNode_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComplexReferenceNode_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_BRACKET);
    r = r && SimpleValue(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "=" ( Expression | ArrayLiteral )
  static boolean ConstAssignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstAssignment")) return false;
    if (!nextTokenIs(b, ASSIGNMENT_OP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGNMENT_OP);
    r = r && ConstAssignment_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expression | ArrayLiteral
  private static boolean ConstAssignment_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstAssignment_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    if (!r) r = ArrayLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "const" TypeReference NameNode [ "[" SimpleValue "]" ] ConstAssignment
  public static boolean ConstDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstDef")) return false;
    if (!nextTokenIs(b, CONST)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONST_DEF, null);
    r = consumeToken(b, CONST);
    p = r; // pin = 1
    r = r && report_error_(b, TypeReference(b, l + 1));
    r = p && report_error_(b, NameNode(b, l + 1)) && r;
    r = p && report_error_(b, ConstDef_3(b, l + 1)) && r;
    r = p && ConstAssignment(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ "[" SimpleValue "]" ]
  private static boolean ConstDef_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstDef_3")) return false;
    ConstDef_3_0(b, l + 1);
    return true;
  }

  // "[" SimpleValue "]"
  private static boolean ConstDef_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstDef_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_BRACKET);
    r = r && SimpleValue(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // { ( FunctionDef | ConstDef | VarDecl | ClassDef | PrototypeDef | InstanceDef | InstanceDecl ) ";" }*
  static boolean DaedalusFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DaedalusFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!DaedalusFile_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DaedalusFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ( FunctionDef | ConstDef | VarDecl | ClassDef | PrototypeDef | InstanceDef | InstanceDecl ) ";"
  private static boolean DaedalusFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DaedalusFile_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DaedalusFile_0_0(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // FunctionDef | ConstDef | VarDecl | ClassDef | PrototypeDef | InstanceDef | InstanceDecl
  private static boolean DaedalusFile_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DaedalusFile_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionDef(b, l + 1);
    if (!r) r = ConstDef(b, l + 1);
    if (!r) r = VarDecl(b, l + 1);
    if (!r) r = ClassDef(b, l + 1);
    if (!r) r = PrototypeDef(b, l + 1);
    if (!r) r = InstanceDef(b, l + 1);
    if (!r) r = InstanceDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Comparison [ ( "==" | "!=" ) Equal ]
  static boolean Equal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Equal")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Comparison(b, l + 1);
    r = r && Equal_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ ( "==" | "!=" ) Equal ]
  private static boolean Equal_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Equal_1")) return false;
    Equal_1_0(b, l + 1);
    return true;
  }

  // ( "==" | "!=" ) Equal
  private static boolean Equal_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Equal_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Equal_1_0_0(b, l + 1);
    r = r && Equal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "==" | "!="
  private static boolean Equal_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Equal_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS_OP);
    if (!r) r = consumeToken(b, NOT_EQUALS_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LogicAnd [ "||" Expression ]
  static boolean Expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LogicAnd(b, l + 1);
    r = r && Expression_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "||" Expression ]
  private static boolean Expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression_1")) return false;
    Expression_1_0(b, l + 1);
    return true;
  }

  // "||" Expression
  private static boolean Expression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OR_OP);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ReferenceNode "(" [ Expression { "," Expression }* ] ")"
  static boolean FuncCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncCall")) return false;
    if (!nextTokenIs(b, IDENTIFIER_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ReferenceNode(b, l + 1);
    r = r && consumeToken(b, LEFT_PAREN);
    p = r; // pin = 2
    r = r && report_error_(b, FuncCall_2(b, l + 1));
    r = p && consumeToken(b, RIGHT_PAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ Expression { "," Expression }* ]
  private static boolean FuncCall_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncCall_2")) return false;
    FuncCall_2_0(b, l + 1);
    return true;
  }

  // Expression { "," Expression }*
  private static boolean FuncCall_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncCall_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    r = r && FuncCall_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { "," Expression }*
  private static boolean FuncCall_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncCall_2_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!FuncCall_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FuncCall_2_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Expression
  private static boolean FuncCall_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FuncCall_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "func" TypeReference NameNode ParameterList StatementBlock
  public static boolean FunctionDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDef")) return false;
    if (!nextTokenIs(b, FUNC)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DEF, null);
    r = consumeToken(b, FUNC);
    p = r; // pin = 1
    r = r && report_error_(b, TypeReference(b, l + 1));
    r = p && report_error_(b, NameNode(b, l + 1)) && r;
    r = p && report_error_(b, ParameterList(b, l + 1)) && r;
    r = p && StatementBlock(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "if" Expression StatementBlock { "else" "if" Expression StatementBlock }* [ "else" StatementBlock ]
  static boolean IfBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfBlock")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, IF);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1));
    r = p && report_error_(b, StatementBlock(b, l + 1)) && r;
    r = p && report_error_(b, IfBlock_3(b, l + 1)) && r;
    r = p && IfBlock_4(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // { "else" "if" Expression StatementBlock }*
  private static boolean IfBlock_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfBlock_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!IfBlock_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IfBlock_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "else" "if" Expression StatementBlock
  private static boolean IfBlock_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfBlock_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && consumeToken(b, IF);
    r = r && Expression(b, l + 1);
    r = r && StatementBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "else" StatementBlock ]
  private static boolean IfBlock_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfBlock_4")) return false;
    IfBlock_4_0(b, l + 1);
    return true;
  }

  // "else" StatementBlock
  private static boolean IfBlock_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfBlock_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && StatementBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "instance" NameNode { "," NameNode }* "(" ReferenceNode ")"
  public static boolean InstanceDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceDecl")) return false;
    if (!nextTokenIs(b, INSTANCE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INSTANCE_DECL, null);
    r = consumeToken(b, INSTANCE);
    r = r && NameNode(b, l + 1);
    r = r && InstanceDecl_2(b, l + 1);
    p = r; // pin = 3
    r = r && report_error_(b, consumeToken(b, LEFT_PAREN));
    r = p && report_error_(b, ReferenceNode(b, l + 1)) && r;
    r = p && consumeToken(b, RIGHT_PAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // { "," NameNode }*
  private static boolean InstanceDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceDecl_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!InstanceDecl_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InstanceDecl_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," NameNode
  private static boolean InstanceDecl_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceDecl_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && NameNode(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "instance" NameNode "(" ReferenceNode ")" "{" { ( Assignment | FuncCall ) ";" }* "}"
  public static boolean InstanceDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceDef")) return false;
    if (!nextTokenIs(b, INSTANCE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INSTANCE_DEF, null);
    r = consumeToken(b, INSTANCE);
    r = r && NameNode(b, l + 1);
    r = r && consumeToken(b, LEFT_PAREN);
    r = r && ReferenceNode(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    r = r && consumeToken(b, LEFT_BRACE);
    p = r; // pin = 6
    r = r && report_error_(b, InstanceDef_6(b, l + 1));
    r = p && consumeToken(b, RIGHT_BRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // { ( Assignment | FuncCall ) ";" }*
  private static boolean InstanceDef_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceDef_6")) return false;
    int c = current_position_(b);
    while (true) {
      if (!InstanceDef_6_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InstanceDef_6", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ( Assignment | FuncCall ) ";"
  private static boolean InstanceDef_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceDef_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InstanceDef_6_0_0(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // Assignment | FuncCall
  private static boolean InstanceDef_6_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceDef_6_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Assignment(b, l + 1);
    if (!r) r = FuncCall(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BitOr [ "&&" LogicAnd ]
  static boolean LogicAnd(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LogicAnd")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BitOr(b, l + 1);
    r = r && LogicAnd_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "&&" LogicAnd ]
  private static boolean LogicAnd_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LogicAnd_1")) return false;
    LogicAnd_1_0(b, l + 1);
    return true;
  }

  // "&&" LogicAnd
  private static boolean LogicAnd_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LogicAnd_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AND_OP);
    r = r && LogicAnd(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Unary [ ( "*" | "/" | "%" ) Mult ]
  static boolean Mult(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Mult")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Unary(b, l + 1);
    r = r && Mult_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ ( "*" | "/" | "%" ) Mult ]
  private static boolean Mult_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Mult_1")) return false;
    Mult_1_0(b, l + 1);
    return true;
  }

  // ( "*" | "/" | "%" ) Mult
  private static boolean Mult_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Mult_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Mult_1_0_0(b, l + 1);
    r = r && Mult(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "*" | "/" | "%"
  private static boolean Mult_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Mult_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MUL_OP);
    if (!r) r = consumeToken(b, DIV_OP);
    if (!r) r = consumeToken(b, REM_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER_TOKEN
  public static boolean NameNode(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NameNode")) return false;
    if (!nextTokenIs(b, IDENTIFIER_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER_TOKEN);
    exit_section_(b, m, NAME_NODE, r);
    return r;
  }

  /* ********************************************************** */
  // "(" [ VarDecl { "," VarDecl }* ] ")"
  static boolean ParameterList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParameterList")) return false;
    if (!nextTokenIs(b, LEFT_PAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, LEFT_PAREN);
    p = r; // pin = 1
    r = r && report_error_(b, ParameterList_1(b, l + 1));
    r = p && consumeToken(b, RIGHT_PAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ VarDecl { "," VarDecl }* ]
  private static boolean ParameterList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParameterList_1")) return false;
    ParameterList_1_0(b, l + 1);
    return true;
  }

  // VarDecl { "," VarDecl }*
  private static boolean ParameterList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParameterList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarDecl(b, l + 1);
    r = r && ParameterList_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // { "," VarDecl }*
  private static boolean ParameterList_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParameterList_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ParameterList_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ParameterList_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," VarDecl
  private static boolean ParameterList_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParameterList_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && VarDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "prototype" NameNode "(" ReferenceNode ")" "{" { ( Assignment | FuncCall ) ";" }* "}"
  public static boolean PrototypeDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrototypeDef")) return false;
    if (!nextTokenIs(b, PROTOTYPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PROTOTYPE_DEF, null);
    r = consumeToken(b, PROTOTYPE);
    p = r; // pin = 1
    r = r && report_error_(b, NameNode(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LEFT_PAREN)) && r;
    r = p && report_error_(b, ReferenceNode(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, RIGHT_PAREN)) && r;
    r = p && report_error_(b, consumeToken(b, LEFT_BRACE)) && r;
    r = p && report_error_(b, PrototypeDef_6(b, l + 1)) && r;
    r = p && consumeToken(b, RIGHT_BRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // { ( Assignment | FuncCall ) ";" }*
  private static boolean PrototypeDef_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrototypeDef_6")) return false;
    int c = current_position_(b);
    while (true) {
      if (!PrototypeDef_6_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PrototypeDef_6", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ( Assignment | FuncCall ) ";"
  private static boolean PrototypeDef_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrototypeDef_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PrototypeDef_6_0_0(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // Assignment | FuncCall
  private static boolean PrototypeDef_6_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrototypeDef_6_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Assignment(b, l + 1);
    if (!r) r = FuncCall(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER_TOKEN
  public static boolean ReferenceNode(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceNode")) return false;
    if (!nextTokenIs(b, IDENTIFIER_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER_TOKEN);
    exit_section_(b, m, REFERENCE_NODE, r);
    return r;
  }

  /* ********************************************************** */
  // "return" [ Expression ]
  static boolean ReturnStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement")) return false;
    if (!nextTokenIs(b, RETURN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, RETURN);
    p = r; // pin = 1
    r = r && ReturnStatement_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ Expression ]
  private static boolean ReturnStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement_1")) return false;
    Expression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // INTEGER_LITERAL | ReferenceNode
  static boolean SimpleValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleValue")) return false;
    if (!nextTokenIs(b, "", IDENTIFIER_TOKEN, INTEGER_LITERAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INTEGER_LITERAL);
    if (!r) r = ReferenceNode(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "{" { ( FuncCall | Assignment | IfBlock | ReturnStatement | ConstDef | VarDecl | Expression ) [ ";" ] }* "}"
  static boolean StatementBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementBlock")) return false;
    if (!nextTokenIs(b, LEFT_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_BRACE);
    r = r && StatementBlock_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // { ( FuncCall | Assignment | IfBlock | ReturnStatement | ConstDef | VarDecl | Expression ) [ ";" ] }*
  private static boolean StatementBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementBlock_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!StatementBlock_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StatementBlock_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ( FuncCall | Assignment | IfBlock | ReturnStatement | ConstDef | VarDecl | Expression ) [ ";" ]
  private static boolean StatementBlock_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementBlock_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StatementBlock_1_0_0(b, l + 1);
    r = r && StatementBlock_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FuncCall | Assignment | IfBlock | ReturnStatement | ConstDef | VarDecl | Expression
  private static boolean StatementBlock_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementBlock_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FuncCall(b, l + 1);
    if (!r) r = Assignment(b, l + 1);
    if (!r) r = IfBlock(b, l + 1);
    if (!r) r = ReturnStatement(b, l + 1);
    if (!r) r = ConstDef(b, l + 1);
    if (!r) r = VarDecl(b, l + 1);
    if (!r) r = Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ ";" ]
  private static boolean StatementBlock_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementBlock_1_0_1")) return false;
    consumeToken(b, SEMICOLON);
    return true;
  }

  /* ********************************************************** */
  // ReferenceNode | "void" | "int" | "float" | "string" | "func"
  static boolean TypeReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeReference")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ReferenceNode(b, l + 1);
    if (!r) r = consumeToken(b, VOID);
    if (!r) r = consumeToken(b, INT);
    if (!r) r = consumeToken(b, FLOAT);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, FUNC);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // [ "-" | "!" | "~" | "+" ] Value
  static boolean Unary(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Unary")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Unary_0(b, l + 1);
    r = r && Value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ "-" | "!" | "~" | "+" ]
  private static boolean Unary_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Unary_0")) return false;
    Unary_0_0(b, l + 1);
    return true;
  }

  // "-" | "!" | "~" | "+"
  private static boolean Unary_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Unary_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MINUS_OP);
    if (!r) r = consumeToken(b, NEGATION_OP);
    if (!r) r = consumeToken(b, BIT_NEGATION_OP);
    if (!r) r = consumeToken(b, PLUS_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INTEGER_LITERAL | FLOAT_LITERAL | STRING_LITERAL | FuncCall | ComplexReference | ( "(" Expression ")" )
  static boolean Value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Value")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INTEGER_LITERAL);
    if (!r) r = consumeToken(b, FLOAT_LITERAL);
    if (!r) r = consumeToken(b, STRING_LITERAL);
    if (!r) r = FuncCall(b, l + 1);
    if (!r) r = ComplexReference(b, l + 1);
    if (!r) r = Value_5(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "(" Expression ")"
  private static boolean Value_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Value_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_PAREN);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "var" TypeReference NameNode [ "[" SimpleValue "]" | { "," NameNode }* ]
  public static boolean VarDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VAR_DECL, null);
    r = consumeToken(b, VAR);
    p = r; // pin = 1
    r = r && report_error_(b, TypeReference(b, l + 1));
    r = p && report_error_(b, NameNode(b, l + 1)) && r;
    r = p && VarDecl_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ "[" SimpleValue "]" | { "," NameNode }* ]
  private static boolean VarDecl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_3")) return false;
    VarDecl_3_0(b, l + 1);
    return true;
  }

  // "[" SimpleValue "]" | { "," NameNode }*
  private static boolean VarDecl_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarDecl_3_0_0(b, l + 1);
    if (!r) r = VarDecl_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "[" SimpleValue "]"
  private static boolean VarDecl_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_BRACKET);
    r = r && SimpleValue(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // { "," NameNode }*
  private static boolean VarDecl_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_3_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!VarDecl_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "VarDecl_3_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," NameNode
  private static boolean VarDecl_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && NameNode(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
