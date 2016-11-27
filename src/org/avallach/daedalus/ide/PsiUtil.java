package org.avallach.daedalus.ide;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;

public class PsiUtil
{
    public static int getLineNumber(PsiElement element)
    {
        Project project = element.getProject();
        PsiFile file = element.getContainingFile();
        Document document = PsiDocumentManager.getInstance(project).getDocument(file);
        if (document == null)
            throw new RuntimeException("Cannot get document associated with node");
        return document.getLineNumber(element.getTextOffset());
    }

    public static PsiFile[] findFile(Project project, String fileName)
    {
        return FilenameIndex.getFilesByName(project, fileName, GlobalSearchScope.allScope(project));
    }
}
