package org.avallach.daedalus.parser.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.psi.FileViewProvider;
import org.avallach.daedalus.parser.FileType;
import org.avallach.daedalus.parser.Language;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class File extends PsiFileBase {
    public File(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, Language.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return FileType.INSTANCE;
    }

    @Override
    public String toString() {
        return Language.INSTANCE.getDisplayName() + " File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}