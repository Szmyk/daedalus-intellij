package org.avallach.daedalus.ide;

public class Language extends com.intellij.lang.Language {
    public static final Language INSTANCE = new Language();

    private Language() {
        super("Daedalus");
    }
}