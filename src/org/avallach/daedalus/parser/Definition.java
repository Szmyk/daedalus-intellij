package org.avallach.daedalus.parser;

import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.*;
import org.avallach.daedalus.parser.psi.DaedalusTypes;
import org.avallach.daedalus.parser.psi.File;
import org.jetbrains.annotations.NotNull;

public class Definition implements com.intellij.lang.ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(DaedalusTypes.LINE_COMMENT, DaedalusTypes.BLOCK_COMMENT);

    public static final IFileElementType FILE = new IFileElementType(org.avallach.daedalus.parser.Language.findInstance(org.avallach.daedalus.parser.Language.class));

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new LexerAdapter();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.create(DaedalusTypes.STRING_LITERAL);
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new Parser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new File(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return DaedalusTypes.Factory.createElement(node);
    }
}