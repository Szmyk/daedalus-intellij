package org.avallach.daedalus.parser.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.indexing.FileBasedIndex;
import org.avallach.commons.Debug;
import org.avallach.daedalus.parser.ElementFactory;
import org.avallach.daedalus.parser.FileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class ReferenceNodeMixin extends ASTWrapperPsiElement implements PsiReference {
    public ReferenceNodeMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        return this;
    }

    private Stream<PsiNamedElement> findFileDefinitions(@NotNull PsiFile file) {
        Collection<PsiNamedElement> allDefinitions = PsiTreeUtil.findChildrenOfType(file, PsiNamedElement.class);
        return allDefinitions.stream().filter(this::isReferenceTo);
    }

    private Stream<PsiNamedElement> findProjectDefinitions(Project project) {
        PsiManager psiManager = PsiManager.getInstance(project);
        return FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, FileType.INSTANCE,
                GlobalSearchScope.allScope(project)).stream()
                .flatMap(virtualFile -> findFileDefinitions(psiManager.findFile(virtualFile)));
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        Stream<PsiNamedElement> localDefinitions = findFileDefinitions(getContainingFile());
        Optional<PsiNamedElement> result = localDefinitions.findAny();
        if (!result.isPresent())
            result = findProjectDefinitions(getProject()).findAny();
        if (result.isPresent())
            return result.get();
        else
            return null;
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
        return psiElement instanceof PsiNamedElement && getText().equals(((PsiNamedElement) psiElement).getName());
    }

    @Override
    public boolean isSoft() {
        return false;
    }
}
