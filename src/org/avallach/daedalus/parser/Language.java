package org.avallach.daedalus.parser;

public class Language extends com.intellij.lang.Language {
    public static final Language INSTANCE = new Language();

    private Language() {
        super("Daedalus");
    }
}