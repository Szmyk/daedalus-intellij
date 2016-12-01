package org.avallach.daedalus.parser;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.testFramework.LightVirtualFile;
import org.avallach.commons.Debug;
import org.avallach.commons.ResourceUtils;
import org.avallach.daedalus.parser.psi.File;

import java.io.IOException;

public class EngineExternals {
    private static String fileName = "externals.d";
    private static PsiFile definitionsFile = null;
    public static PsiFile getDefinitionsFile(Project project) {
        if (definitionsFile == null) {
            VirtualFile virtualFile = new LightVirtualFile(fileName, FileType.INSTANCE, getCode());
            PsiManager psiManager = PsiManager.getInstance(project);
            FileViewProvider fileViewProvider = new SingleRootFileViewProvider(psiManager, virtualFile);
            definitionsFile = new File(fileViewProvider);
        }
        return definitionsFile;
    }

    private static String getCode() {
        try {
            return ResourceUtils.readFile(EngineExternals.class, fileName);
        } catch (IOException e) {
            Debug.log(e);
            return "";
        }
    }
}
