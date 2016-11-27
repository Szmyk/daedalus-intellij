package org.avallach.daedalus.ide;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.avallach.commons.Debug;
import org.avallach.daedalus.parser.LexerAdapter;
import org.avallach.daedalus.parser.psi.DaedalusTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TokenHighlighter extends SyntaxHighlighterBase {
    private Map<IElementType, TextAttributesKey[]> styles = new HashMap<>();
    private TextAttributesKey[] emptyStyle = new TextAttributesKey[0];

    public TokenHighlighter()
    {
        styles.put(TokenType.WHITE_SPACE, emptyStyle);
        applyStyle(TokenGroups.IDENTIFIERS,       DefaultLanguageHighlighterColors.IDENTIFIER);
        applyStyle(DaedalusTypes.INTEGER_LITERAL, DefaultLanguageHighlighterColors.NUMBER);
        applyStyle(DaedalusTypes.FLOAT_LITERAL,   DefaultLanguageHighlighterColors.NUMBER);
        applyStyle(TokenGroups.KEYWORDS,          DefaultLanguageHighlighterColors.KEYWORD);
        applyStyle(TokenGroups.BUILTIN_TYPES,     DefaultLanguageHighlighterColors.KEYWORD);
        applyStyle(DaedalusTypes.STRING_LITERAL,  DefaultLanguageHighlighterColors.STRING);
        applyStyle(DaedalusTypes.BLOCK_COMMENT,   DefaultLanguageHighlighterColors.BLOCK_COMMENT);
        applyStyle(DaedalusTypes.LINE_COMMENT,    DefaultLanguageHighlighterColors.LINE_COMMENT);
        applyStyle(TokenGroups.OPERATORS,         DefaultLanguageHighlighterColors.OPERATION_SIGN);
        applyStyle(DaedalusTypes.LEFT_BRACE,      DefaultLanguageHighlighterColors.BRACES);
        applyStyle(DaedalusTypes.RIGHT_BRACE,     DefaultLanguageHighlighterColors.BRACES);
        applyStyle(DaedalusTypes.DOT,             DefaultLanguageHighlighterColors.DOT);
        applyStyle(DaedalusTypes.SEMICOLON,       DefaultLanguageHighlighterColors.SEMICOLON);
        applyStyle(DaedalusTypes.COMMA,           DefaultLanguageHighlighterColors.COMMA);
        applyStyle(DaedalusTypes.LEFT_PAREN,      DefaultLanguageHighlighterColors.PARENTHESES);
        applyStyle(DaedalusTypes.RIGHT_PAREN,     DefaultLanguageHighlighterColors.PARENTHESES);
        applyStyle(DaedalusTypes.LEFT_BRACKET,    DefaultLanguageHighlighterColors.BRACKETS);
        applyStyle(DaedalusTypes.RIGHT_BRACKET,   DefaultLanguageHighlighterColors.BRACKETS);
        applyStyle(TokenGroups.BUILTIN_TYPES,     DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);
        //TODO: CONSTANT, LOCAL_VARIABLE, FUNCTION_DECLARATION, CLASS_NAME, CLASS_REFERENCE, INSTANCE_FIELD
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new LexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType elementType) {
        TextAttributesKey[] style = styles.get(elementType);
        if (style != null)
            return style;
        else {
            Debug.log("No style found for token " + elementType.getLanguage() + "." + elementType.toString());
            return emptyStyle;
        }
    }

    private void applyStyle(IElementType tokenType, TextAttributesKey style)
    {
        TextAttributesKey[] styleArray = new TextAttributesKey[]{style};
        styles.put(tokenType, styleArray);
    }

    private void applyStyle(TokenSet tokens, TextAttributesKey style)
    {
        TextAttributesKey[] styleArray = new TextAttributesKey[]{style};
        for(IElementType tokenType : tokens.getTypes())
            styles.put(tokenType, styleArray);
    }
}
