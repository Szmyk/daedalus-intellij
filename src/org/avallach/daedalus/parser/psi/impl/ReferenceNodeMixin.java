package org.avallach.daedalus.parser.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import org.avallach.commons.Debug;
import org.avallach.daedalus.parser.ElementFactory;
import org.avallach.daedalus.parser.ReferenceResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReferenceNodeMixin extends ASTWrapperPsiElement implements PsiReference {
    public ReferenceNodeMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        return this;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return ResolveCache.getInstance(getProject())
                .resolveWithCaching(this, ReferenceResolver.INSTANCE, false, true);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }

    @Override
    public PsiElement getElement() {
        return this;
    }

    @Override
    public TextRange getRangeInElement() {
        return new TextRange(0, getTextLength());
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return getText();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        return ElementFactory.renameElement(this, newName);
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        Debug.log(getName(), psiElement instanceof PsiNamedElement ? ((PsiNamedElement) psiElement).getName() : psiElement.getText(), " -> IncorrectOperationException!");
        throw new IncorrectOperationException(); //TODO
    }

    @Override
    public boolean isReferenceTo(PsiElement psiElement) {
        boolean result = ReferenceResolver.INSTANCE.isReference(this, psiElement);
        Debug.log(this.getElement().getText(), psiElement.getText(), result);
        return result;
    }

    @Override
    public boolean isSoft() {
        return false;
    }
}
