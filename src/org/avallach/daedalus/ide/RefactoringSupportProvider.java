package org.avallach.daedalus.ide;

import com.intellij.psi.PsiElement;
import org.avallach.commons.Debug;
import org.jetbrains.annotations.NotNull;

public class RefactoringSupportProvider extends com.intellij.lang.refactoring.RefactoringSupportProvider {
    @Override
    public boolean isInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        Debug.log(element, context);
        return true;
    }

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        Debug.log(element, context);
        return true;
    }
}
