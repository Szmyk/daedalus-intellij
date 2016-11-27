package org.avallach.daedalus.ide;

import com.intellij.psi.tree.TokenSet;

import static org.avallach.daedalus.parser.psi.DaedalusTypes.*;

public class TokenGroups {
    public static TokenSet KEYWORDS = TokenSet.create(
            CLASS,
            PROTOTYPE,
            INSTANCE,
            CONST,
            VAR,
            FUNC,
            IF,
            ELSE,
            RETURN
    );
    public static TokenSet BUILTIN_TYPES = TokenSet.create(
            VOID,
            INT,
            FLOAT,
            STRING
    );
    public static TokenSet BLOCK_BOUNDARIES = TokenSet.create(
            LEFT_BRACE,
            RIGHT_BRACE,
            LEFT_PAREN,
            RIGHT_PAREN,
            LEFT_BRACKET,
            RIGHT_BRACKET
    );
    public static TokenSet OPERATORS = TokenSet.create(
            PLUS_OP,
            MINUS_OP,
            MUL_OP,
            DIV_OP,
            REM_OP,
            ASSIGNMENT_OP,
            ASSIGNMENT_PLUS_OP,
            ASSIGNMENT_MINUS_OP,
            ASSIGNMENT_MUL_OP,
            ASSIGNMENT_DIV_OP,
            LESS_THAN,
            GREATER_THAN,
            EQUALS_OP,
            NOT_EQUALS_OP,
            LESS_THAN_OR_EQUAL_OP,
            GREATER_THAN_OR_EQUAL_OP,
            BIT_SHIFT_LEFT_OP,
            BIT_SHIFT_RIGHT_OP,
            BIT_OR_OP,
            BIT_AND_OP,
            BIT_NEGATION_OP,
            AND_OP,
            OR_OP,
            NEGATION_OP
    );
    public static TokenSet MISC = TokenSet.create(
            DOT,
            COMMA,
            SEMICOLON
    );
    public static TokenSet IDENTIFIERS = TokenSet.create(
            IDENTIFIER_TOKEN
    );
    public static TokenSet LITERALS = TokenSet.create(
            STRING_LITERAL,
            INTEGER_LITERAL,
            FLOAT_LITERAL
    );
    public static TokenSet COMMENTS = TokenSet.create(
            LINE_COMMENT,
            BLOCK_COMMENT
    );
}
