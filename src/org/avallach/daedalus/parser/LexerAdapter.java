package org.avallach.daedalus.parser;

import com.intellij.lexer.FlexAdapter;

public class LexerAdapter extends FlexAdapter
{
    public LexerAdapter()
    {
        super(new _DaedalusLexer());
    }
}
