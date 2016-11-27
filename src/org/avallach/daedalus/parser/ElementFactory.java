package org.avallach.daedalus.parser;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.avallach.daedalus.parser.psi.NameNode;
import org.avallach.daedalus.parser.psi.ReferenceNode;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ElementFactory {
    private static com.intellij.openapi.fileTypes.FileType fileType = FileType.INSTANCE;
    private static String fileName = "__." + fileType.getDefaultExtension();
    private static Map<Class<? extends PsiElement>, String> templateCodes = new HashMap<>();
    private static String namePlaceholder = "{NAME}";
    static
    {
        templateCodes.put(ReferenceNode.class, "const int x = " + namePlaceholder + ";");
        templateCodes.put(NameNode.class, "var int " + namePlaceholder + ";");
    }

    public static <T extends PsiElement> T createElement(Project project, Class<T> type, String name)
    {
        PsiFile fromText = PsiFileFactory.getInstance(project).createFileFromText(fileName, fileType, templateCodes.get(type).replace(namePlaceholder, name));
        return PsiTreeUtil.findChildOfType(fromText, type);
    }

    public static PsiElement renameElement(PsiElement element, String newName)
    {
        Class<? extends PsiElement> type = getType(element);
        if (type != null)
            return element.replace(createElement(element.getProject(), type, newName));
        else
            throw new IncorrectOperationException();
    }

    @Nullable
    private static Class<? extends PsiElement> getType(PsiElement element) {
        for (Class<? extends PsiElement> type : templateCodes.keySet())
            if (type.isInstance(element))
                return type;
        return null;
    }
}
