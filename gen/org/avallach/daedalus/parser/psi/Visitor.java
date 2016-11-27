// This is a generated file. Not intended for manual editing.
package org.avallach.daedalus.parser.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public class Visitor extends PsiElementVisitor {

  public void visitClassDef(@NotNull ClassDef o) {
    visitPsiElement(o);
  }

  public void visitConstDef(@NotNull ConstDef o) {
    visitPsiElement(o);
  }

  public void visitFunctionDef(@NotNull FunctionDef o) {
    visitPsiNameIdentifierOwner(o);
  }

  public void visitInstanceDecl(@NotNull InstanceDecl o) {
    visitPsiElement(o);
  }

  public void visitInstanceDef(@NotNull InstanceDef o) {
    visitPsiElement(o);
  }

  public void visitNameNode(@NotNull NameNode o) {
    visitPsiElement(o);
  }

  public void visitPrototypeDef(@NotNull PrototypeDef o) {
    visitPsiElement(o);
  }

  public void visitReferenceNode(@NotNull ReferenceNode o) {
    visitPsiElement(o);
  }

  public void visitVarDecl(@NotNull VarDecl o) {
    visitPsiElement(o);
  }

  public void visitPsiNameIdentifierOwner(@NotNull PsiNameIdentifierOwner o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
