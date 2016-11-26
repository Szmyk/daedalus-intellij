// This is a generated file. Not intended for manual editing.
package org.avallach.daedalus.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.avallach.daedalus.parser.psi.DaedalusTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.avallach.daedalus.parser.psi.*;

public class InstanceDeclImpl extends ASTWrapperPsiElement implements InstanceDecl {

  public InstanceDeclImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull Visitor visitor) {
    visitor.visitInstanceDecl(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) accept((Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<NameNode> getNameNodeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NameNode.class);
  }

  @Override
  @Nullable
  public ReferenceNode getReferenceNode() {
    return findChildByClass(ReferenceNode.class);
  }

}
