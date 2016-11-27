package org.avallach.daedalus.parser.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.avallach.commons.Debug;
import org.avallach.daedalus.parser.ElementFactory;
import org.avallach.daedalus.parser.psi.NameNode;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class DaedalusDefinitionImpl extends ASTWrapperPsiElement implements PsiNameIdentifierOwner {
    public DaedalusDefinitionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return PsiTreeUtil.getChildOfType(this, NameNode.class);
    }

    @Nullable
    @Override
    public String getName() {
        PsiElement identifier = getNameIdentifier();
        return identifier != null ? identifier.getText() : null;
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String newName) throws IncorrectOperationException {
        PsiElement nameIdentifier = getNameIdentifier();
        if (nameIdentifier != null)
        {
            ElementFactory.renameElement(nameIdentifier, newName);
            return this;
        }
        else
            throw new IncorrectOperationException();
    }
}