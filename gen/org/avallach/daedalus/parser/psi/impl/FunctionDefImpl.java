// This is a generated file. Not intended for manual editing.
package org.avallach.daedalus.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.avallach.daedalus.parser.psi.DaedalusTypes.*;
import org.avallach.daedalus.parser.psi.*;

public class FunctionDefImpl extends DaedalusDefinitionImpl implements FunctionDef {

  public FunctionDefImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull Visitor visitor) {
    visitor.visitFunctionDef(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) accept((Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ConstDef> getConstDefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ConstDef.class);
  }

  @Override
  @Nullable
  public NameNode getNameNode() {
    return findChildByClass(NameNode.class);
  }

  @Override
  @NotNull
  public List<ReferenceNode> getReferenceNodeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ReferenceNode.class);
  }

  @Override
  @NotNull
  public List<VarDecl> getVarDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VarDecl.class);
  }

}
