package org.example.eip.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.example.eip.constants.ProcExecutionType;
import org.example.eip.properties.MessagesProperties;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.example.eip.constants.Origin.PROC;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProcService {

    private final ProcCommonService procCommonService;
    private final MessagesProperties messagesProperties;

    public void processMessage(File file) throws Exception {
        procCommonService.checkDiskUsage(List.of(messagesProperties.getRoot()));
        StopWatch watch = new StopWatch();
        watch.start();
        log.info("proc.processing.info");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file and convert it into a Map
            Map<String, Object> jsonData = objectMapper.readValue(file, Map.class);

            // Display the content of the JSON file
            System.out.println("JSON File Content:");
            System.out.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        watch.stop();
        log.info("common.execution_time.info");
    }

    /**
     * Initialise le feed PROC
     */
    public void initProcLog(){
        procCommonService.initProcLogBDD(ProcExecutionType.PROCESSING, PROC);
    }

    public void endProcLog(){
        procCommonService.endProcXLog(PROC);
    }


    public void endProcLogError(Throwable exception){
        procCommonService.endProcLogError(PROC, exception);
    }
}
