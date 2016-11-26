// This is a generated file. Not intended for manual editing.
package org.avallach.daedalus.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VarDecl extends PsiElement {

  @NotNull
  List<NameNode> getNameNodeList();

  @NotNull
  List<ReferenceNode> getReferenceNodeList();

  @Nullable
  PsiElement getIntegerLiteral();

}
