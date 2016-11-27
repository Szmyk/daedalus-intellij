package org.avallach.daedalus.ide.highlighting;

import com.google.common.hash.Hashing;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.avallach.commons.Cache;
import org.avallach.daedalus.parser.LexerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class GenericSyntaxHighlighter extends SyntaxHighlighterBase {

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new LexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType elementType) {
        return tokenHighlightsCache.get(elementType);
    }
    private Cache<IElementType, TextAttributesKey[]> tokenHighlightsCache = new Cache<>(elementType ->
    {
        String name = elementType.getLanguage().getDisplayName() + "." + elementType.getIndex();
        int hash = Hashing.murmur3_32().hashInt(elementType.getIndex()).asInt();
        @Nullable Color foregroundColor = Color.getHSBColor(1.0f * hash / Integer.MAX_VALUE, 0.4f, 1.0f);
        TextAttributes textAttributes = new TextAttributes(foregroundColor, null, null, null, Font.PLAIN);
        TextAttributesKey textAttributesKey = TextAttributesKey.createTextAttributesKey(name, textAttributes);
        return new TextAttributesKey[]{textAttributesKey};
    });
}
