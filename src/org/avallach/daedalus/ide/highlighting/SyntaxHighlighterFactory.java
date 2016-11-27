package org.avallach.daedalus.ide.highlighting;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.avallach.daedalus.ide.TokenHighlighter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SyntaxHighlighterFactory extends com.intellij.openapi.fileTypes.SyntaxHighlighterFactory {
    @NotNull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile) {
        return new TokenHighlighter();
    }
}
