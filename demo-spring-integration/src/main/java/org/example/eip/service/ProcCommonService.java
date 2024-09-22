package org.example.eip.service;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eip.constants.Origin;
import org.example.eip.constants.ProcExecutionType;
import org.example.eip.exception.NotEnoughDiskSpaceException;
import org.example.eip.exception.ProcRunningException;
import org.example.eip.properties.MessagesProperties;
import org.example.eip.properties.ParametersProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class ProcCommonService {

    private final MessagesProperties messagesProperties;
    private final ParametersProperties parametersProperties;

    public void setRights(Path sourcePath, String chmod, String groupName) {
        try {
            PosixFileAttributeView posixView = Files.getFileAttributeView(sourcePath, PosixFileAttributeView.class);
            if (posixView != null) {
                log.info("common.changing_attribute_file.info");
                Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(chmod);
                Files.setPosixFilePermissions(sourcePath, permissions);
                if (groupName != null) {
                    GroupPrincipal groupPrincipal = sourcePath.getFileSystem().getUserPrincipalLookupService()
                            .lookupPrincipalByGroupName(groupName);
                    Files.getFileAttributeView(sourcePath, PosixFileAttributeView.class).setGroup(groupPrincipal);
                }
            }
        } catch (Exception e) {
            log.error("common.setting_right_file.error");
        }
    }

    public void initProcLogBDD(ProcExecutionType executionType, Origin proc) {
        System.out.println("STEP1 -------------- Init");
    }

    public void endProcLogError(Exception exception) {
        System.out.println("endProcLogError" + exception.getMessage());
    }

    public void endProcXLog(Origin proc) {
        System.out.println("endProcXLogBDD" + proc);
    }

    public void endProcLogError(Origin proc, Throwable exception) {
        String errorMessage;
        if (exception.getCause() != null) {
            errorMessage = exception.getCause().getMessage();
        } else {
            errorMessage = exception.getMessage();
        }

        if (exception.getCause() instanceof ProcRunningException) {
            log.warn(errorMessage);
        } else {
            log.error(errorMessage);
        }
    }

    public void checkDiskUsage(List<String> paths) throws NotEnoughDiskSpaceException {
        try {
            for (String path : paths) {
                FileStore store = Files.getFileStore(Path.of(path));

                final float usedSpacePercentage =
                        ((float) (store.getTotalSpace() - store.getUnallocatedSpace()) / store.getTotalSpace()) * 100;

                if (usedSpacePercentage > getUsedDiskSpacePercentageMax(path)) {
                    throw new NotEnoughDiskSpaceException("common.no_enough_disk_space.error");
                }
            }
        } catch (IOException e) {
            log.error("common.io.exception");
        }
    }

    public int getUsedDiskSpacePercentageMax(String path) {
        if (messagesProperties.getRoot().equals(path)) {
            return parametersProperties.getUsedDiskSpacemessages();
        } else {
            return 100;
        }
    }
}
