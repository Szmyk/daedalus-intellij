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

public class PrototypeDefImpl extends ASTWrapperPsiElement implements PrototypeDef {

  public PrototypeDefImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull Visitor visitor) {
    visitor.visitPrototypeDef(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) accept((Visitor)visitor);
    else super.accept(visitor);
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

}
