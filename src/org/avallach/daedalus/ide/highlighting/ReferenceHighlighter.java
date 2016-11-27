package org.avallach.daedalus.ide.highlighting;

import com.intellij.codeHighlighting.TextEditorHighlightingPassRegistrar;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public class ReferenceHighlighter extends PsiHighlighterFactoryBase {

    public ReferenceHighlighter(Project project, TextEditorHighlightingPassRegistrar registrar) {
        super(project, registrar);
    }

    private boolean isUnresolvedReference(PsiElement element) {
        PsiReference reference = element.getReference();
        return reference != null && reference.resolve() == null;
    }

    @Override
    protected HighlightInfoType getStyle(PsiElement element) {
        return isUnresolvedReference(element) ? DefinitionHighlighter.constantHighlight : null;
    }
}
