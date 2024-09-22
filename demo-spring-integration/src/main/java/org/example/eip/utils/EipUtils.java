package org.example.eip.utils;

import org.slf4j.MDC;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

import static org.example.eip.constants.Constants.UNDERSCORE_SEPARATOR;


public final class EipUtils {

    private EipUtils(){ }

    public static FileWritingMessageHandler getFileWritingMessageHandler(
            String directory,
            FileNameGenerator fileNameGenerator,
            boolean autoCreateDirectory) {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(directory));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        handler.setDeleteSourceFiles(true);
        if(fileNameGenerator!=null){
            handler.setFileNameGenerator(fileNameGenerator);
        }
        handler.setAutoCreateDirectory(autoCreateDirectory);
        return handler;
    }

    public static String renameMessageFile(File sondeFile){
        String newName = sondeFile.getName();
        newName += UNDERSCORE_SEPARATOR + DateUtils.currentTimeStamp() + UNDERSCORE_SEPARATOR + MDC.get("runId");
        return newName;
    }
}
