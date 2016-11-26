package org.avallach.daedalus.ide;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;

public class FileTypeFactory extends com.intellij.openapi.fileTypes.FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(org.avallach.daedalus.parser.FileType.INSTANCE, org.avallach.daedalus.parser.FileType.INSTANCE.getDefaultExtension());
    }
}