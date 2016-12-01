package org.avallach.daedalus.parser;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.avallach.commons.Debug;
import org.avallach.daedalus.parser.psi.ClassDef;
import org.avallach.daedalus.parser.psi.DaedalusTypes;
import org.avallach.daedalus.parser.psi.File;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReferenceResolver  implements ResolveCache.Resolver {
    private ReferenceResolverCache cache = new ReferenceResolverCache();
    public static final ReferenceResolver INSTANCE = new ReferenceResolver();

    @Override
    public PsiElement resolve(@NotNull PsiReference reference, boolean incompleteCode) {
        Debug.log(reference.getCanonicalText());
        String name = reference.getCanonicalText();
        if (name.isEmpty())
            return null;
        PsiElement cached = cache.get(reference);
        if (cached != null && isReference(reference, cached))
            return cached;
        final PsiElement[] definition = new PsiElement[1];
        if (definition[0] == null)
        {
            PsiFile localFile = reference.getElement().getContainingFile();
            definition[0] = findInFile(reference, localFile);
        }
        Project project = reference.getElement().getProject();
        if (definition[0] == null)
        {
            PsiFile externalsFile = EngineExternals.getDefinitionsFile(project);
            definition[0] = findInFile(reference, externalsFile);
        }
        if (definition[0] == null)
        {
            PsiManager psiManager = PsiManager.getInstance(project);
            ProjectRootManager projectRootManager = ProjectRootManager.getInstance(project);
            //TODO: iterate backwards on files listed before this one in .src file
            projectRootManager.getFileIndex().iterateContent(projectFile ->
            {
                PsiFile psiFile = psiManager.findFile(projectFile);
                if (psiFile == null)
                    return true;
                definition[0] = findInFile(reference, psiFile);
                return definition[0] == null;
            });
        }
        PsiElement result = definition[0];
        cache.set(reference, result);
        if (result != null)
            Debug.log("resolved", result.getText()/*, Debug.getStackTraceElement(5, false)*/);
        return result;
    }

    @Nullable
    private PsiElement findInFile(PsiReference reference, PsiFile file) {
        final PsiElement[] definition = new PsiElement[1];
        PsiTreeUtil.processElements(file, psiElement ->
        {
            if (isReference(reference, psiElement))
            {
                definition[0] = psiElement;
                return false;

            }
            return true;
        });
        return definition[0];
    }

    private List<PsiElement> collectAncestors(PsiElement element) {
        List<PsiElement> ancestors = new ArrayList<>();
        PsiElement ancestor = element;
        while (ancestor != null && !(ancestor instanceof PsiDirectory))
        {
            ancestors.add(ancestor);
            ancestor = ancestor.getParent();
        }
        return ancestors;
    }

    public boolean isReference(PsiReference reference, PsiElement definition) {
        if (!(definition instanceof PsiNamedElement))
            return false;
        PsiNamedElement nameNode = (PsiNamedElement) definition;
        String definitionName = nameNode.getName();
        PsiElement referenceNode = reference.getElement();
        if (!namesMatch(definitionName, referenceNode))
            return false;
        if (isGloballyScoped(nameNode))
            return true;
        if (isReferenceToMember(referenceNode, nameNode))
            return true; //TODO: check order
        if (!isDefinedEarlierInFile(nameNode, referenceNode))
            return false;
        return isDefinedInHigherOrEqualScope(nameNode, referenceNode);
    }

    private boolean isReferenceToMember(PsiElement referenceNode, PsiNamedElement nameNode) {
        PsiElement prevSibling = referenceNode.getPrevSibling();
        if (!(prevSibling instanceof LeafPsiElement))
            return false;
        IElementType prevTokenType = ((LeafPsiElement) referenceNode.getPrevSibling()).getElementType();
        boolean isReferenceToSomeMember = prevTokenType == DaedalusTypes.DOT;
        boolean isMemberDefinition = nameNode.getParent().getParent() instanceof ClassDef;
        boolean typesMatch = true; //TODO
        return isReferenceToSomeMember && isMemberDefinition && typesMatch;
    }

    private boolean isDefinedInHigherOrEqualScope(PsiNamedElement nameNode, PsiElement referenceNode) {
        List<PsiElement> referenceAncestors = collectAncestors(referenceNode);
        PsiElement definitionScope = nameNode.getParent().getParent();
        return referenceAncestors.contains(definitionScope);
    }

    private boolean isDefinedEarlierInFile(PsiNamedElement nameNode, PsiElement referenceNode) {
        boolean pathsAreEqual = Objects.equals(
                nameNode.getContainingFile().getVirtualFile().getPath(),
                referenceNode.getContainingFile().getVirtualFile().getPath());
        boolean isDefinedEarlier = nameNode.getTextOffset() < referenceNode.getTextOffset();
        return pathsAreEqual && isDefinedEarlier;
    }

    private boolean namesMatch(String definitionName, PsiElement referenceNode) {
        return referenceNode.getText().toLowerCase().equals(definitionName.toLowerCase());
    }

    private boolean isGloballyScoped(PsiNamedElement nameNode) {
        PsiElement grandParent = nameNode.getParent().getParent();
        return grandParent instanceof File;
    }
}
