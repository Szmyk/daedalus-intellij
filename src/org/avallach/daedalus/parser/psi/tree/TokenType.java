package org.avallach.daedalus.parser.psi.tree;

import com.intellij.psi.tree.IElementType;
import org.avallach.daedalus.parser.Language;
import org.jetbrains.annotations.*;

public class TokenType extends IElementType {
    public TokenType(@NotNull @NonNls String debugName) {
        super(debugName, Language.INSTANCE);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "." + super.toString();
    }
}