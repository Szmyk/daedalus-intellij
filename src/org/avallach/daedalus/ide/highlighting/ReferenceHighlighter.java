package org.avallach.daedalus.ide.highlighting;

import com.intellij.codeHighlighting.TextEditorHighlightingPassRegistrar;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.ui.JBColor;

import java.awt.*;

public class ReferenceHighlighter extends PsiHighlighterFactoryBase {

    public static HighlightInfoType unresolvedReferenceHighlight = new HighlightInfoType.HighlightInfoTypeImpl(
            HighlightSeverity.INFORMATION,
            TextAttributesKey.createTextAttributesKey(
                    "UNRESOLVED_REFERENCE",
                    new TextAttributes
                            (
                                    JBColor.RED,
                                    null,
                                    JBColor.RED,
                                    EffectType.WAVE_UNDERSCORE,
                                    Font.PLAIN
                            ))
    );

    public ReferenceHighlighter(Project project, TextEditorHighlightingPassRegistrar registrar) {
        super(project, registrar);
    }

    private boolean isUnresolvedReference(PsiElement element) {
        PsiReference reference = element.getReference();
        return reference != null && reference.resolve() == null;
    }

    @Override
    protected HighlightInfoType getStyle(PsiElement element) {
        return isUnresolvedReference(element) ? unresolvedReferenceHighlight : null;
    }
}
