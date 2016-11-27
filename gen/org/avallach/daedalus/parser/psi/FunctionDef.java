// This is a generated file. Not intended for manual editing.
package org.avallach.daedalus.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface FunctionDef extends PsiNameIdentifierOwner {

  @NotNull
  List<ConstDef> getConstDefList();

  @Nullable
  NameNode getNameNode();

  @NotNull
  List<ReferenceNode> getReferenceNodeList();

  @NotNull
  List<VarDecl> getVarDeclList();

}
