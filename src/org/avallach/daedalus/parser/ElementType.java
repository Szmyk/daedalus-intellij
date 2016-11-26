package org.avallach.daedalus.parser;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.*;

public class ElementType extends IElementType {
    public ElementType(@NotNull @NonNls String debugName) {
        super(debugName, Language.INSTANCE);
    }
}