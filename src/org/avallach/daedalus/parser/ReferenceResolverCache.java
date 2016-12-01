package org.avallach.daedalus.parser;

import com.intellij.history.core.Paths;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import org.avallach.commons.Debug;
import org.avallach.daedalus.ide.PsiUtil;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.*;

/*
* I'm not gonna like this tomorrow.
* It was created only because until adding builtin definitions, there is a lot of
* unresolved references. They painfully slow down the IDE, so I needed some heavy cache
* for resolver.
* */
public class ReferenceResolverCache {
    class Entry {
        public WeakReference<PsiElement> element;
        public String path;
        public int offset;
    }

    Map<String, Entry> entries = new HashMap<>();
    private Map<String, Integer> missCounters = new HashMap<>();
    private int missBlacklistLevel = 0;
    Random random = new Random();

    @Nullable
    public PsiElement get(PsiReference reference) {
        String name = reference.getCanonicalText();
        if (name.isEmpty())
            return null;
        Integer misses = missCounters.get(name);
        //if (misses != null && misses > missBlacklistLevel && random.nextFloat() > 0.2)
        //{
        //    Debug.log("cache ignored", reference.getCanonicalText(), misses, missBlacklistLevel);
        //    return null;
        //}
        Entry entry = entries.get(name);
        if (entry == null)
        {
            Debug.log("cache miss: " + name);
            return null;
        }
        PsiElement oldElement = entry.element.get();
        if (oldElement != null && oldElement.isValid())
        {
            //Debug.log("cache hit", oldElement.getText());
            return oldElement;
        }
        PsiFile[] files = PsiUtil.findFile(
                reference.getElement().getProject(),
                Paths.getNameOf(entry.path));
        Optional<PsiFile> file = Arrays.stream(files)
                .filter(f -> Objects.equals(f.getVirtualFile().getPath(), entry.path))
                .findFirst();
        if (!file.isPresent())
            return null;
        PsiElement foundElement = file.get().findElementAt(entry.offset);
        if (foundElement != null)
            foundElement = foundElement.getParent();
        if (foundElement == null || !foundElement.getText().equals(name))
        {
            Debug.log("cache invalidated", name);
            entries.remove(name);
            return null;
        }
        Debug.log("cache hit after revalidation", foundElement.getText());
        return foundElement;
    }

    public void set(PsiReference reference, PsiElement result) {
        if (result == null)
        {
            //Debug.log("cache blacklisting", reference.getCanonicalText());
            countMiss(reference);
        }
        else
            entries.put(
                    reference.getCanonicalText(),
                    new Entry()
                    {{
                        element = new WeakReference<>(result);
                        path = result.getContainingFile().getVirtualFile().getPath();
                        offset = result.getTextOffset();
                    }});
    }

    private void countMiss(PsiReference reference) {
        String name = reference.getCanonicalText();
        Integer entry = missCounters.get(name);
        if(entry == null)
            entry = 0;
        if (entry < missBlacklistLevel)
            missCounters.put(name, entry + 50);
        missBlacklistLevel++;
        Debug.log(entry, missBlacklistLevel, reference.getCanonicalText());
    }
}
