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
import org.avallach.daedalus.parser.psi.ConstDef;
import org.avallach.daedalus.parser.psi.NameNode;

import java.awt.*;

public class DefinitionHighlighter extends PsiHighlighterFactoryBase {
    public static HighlightInfoType constantHighlight = new HighlightInfoType.HighlightInfoTypeImpl(
            HighlightSeverity.INFORMATION,
            TextAttributesKey.createTextAttributesKey(
                    "CONSTANT_DEFINITION",
                    new TextAttributes
                            (
                                    JBColor.BLUE,
                                    null,
                                    null,
                                    null,
                                    Font.ITALIC
                            ))
    );

    public DefinitionHighlighter(Project project, TextEditorHighlightingPassRegistrar registrar) {
        super(project, registrar);
    }

    private boolean isConstantDefinition(PsiElement element) {
        return element instanceof NameNode &&
                element.getParent() instanceof ConstDef;
    }

    @Override
    protected HighlightInfoType getStyle(PsiElement element) {
        return isConstantDefinition(element) ? constantHighlight : null;
    }
}
