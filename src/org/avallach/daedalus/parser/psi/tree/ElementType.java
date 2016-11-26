package org.avallach.daedalus.parser.psi.tree;

import com.intellij.psi.tree.IElementType;
import org.avallach.daedalus.parser.Language;
import org.jetbrains.annotations.*;

public class ElementType extends IElementType {
    public ElementType(@NotNull @NonNls String debugName) {
        super(debugName, Language.INSTANCE);
    }
}