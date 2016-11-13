package org.avallach.daedalus.ide;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class FileType extends LanguageFileType {
    public static final FileType INSTANCE = new FileType();

    private FileType() {
        super(Language.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return Language.INSTANCE.getDisplayName() + " file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return Language.INSTANCE.getDisplayName() + " language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "d";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return Icons.FILE;
    }
}
