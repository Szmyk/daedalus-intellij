package org.avallach.daedalus.ide;

import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.avallach.daedalus.parser.psi.DaedalusTypes;
import org.avallach.daedalus.parser.psi.NameNode;
import org.jetbrains.annotations.NotNull;

public class RefactoringSupportProvider extends com.intellij.lang.refactoring.RefactoringSupportProvider {
    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        return element instanceof NameNode &&
                context instanceof LeafPsiElement &&
                ((LeafPsiElement) context).getElementType() == DaedalusTypes.IDENTIFIER_TOKEN;
    }
}
