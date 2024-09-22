package org.example.eip.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.example.eip.constants.ProcExecutionType;
import org.example.eip.properties.MessagesProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
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

            HttpHeaders header = new HttpHeaders();
            header.setContentType( MediaType.MULTIPART_FORM_DATA);
            HttpEntity<Object> httpEntity = new HttpEntity<Object>(jsonData, header);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Resource> responseEntity = null;
            String msg = null;
            File targetFile = null;
            Boolean resultMethod = Boolean.TRUE;
            InputStream responseInputStream = null;

            try {
                responseEntity = restTemplate.exchange(request.getUrlRequest(), request.getMethod(), httpEntity, Resource.class);

                if (responseEntity.getStatusCode() == HttpStatus.OK) {

                    // recuperer la resource du body de la reponse
                    Resource resource = responseEntity.getBody();

                    if (!resource.exists()) {
                        throw new Exception("apiabstract.manageResponse.file.ressourcenotexiste");
                    }

                    // Recuperer le stream de la resource
                    try {
                        responseInputStream = resource.getInputStream();
                    } catch (IOException e) {
                        throw new Exception("apiabstract.manageResponse.file.streamnotexiste");
                    }
                    // Copier la ressource stream dans preuveFile
                    try {
                        Path zipPath = null;
                        Files.copy(responseInputStream, zipPath, StandardCopyOption.REPLACE_EXISTING);
                        responseInputStream.close();
                    } catch (IOException e) {
                        throw new Exception("apiabstract.manageResponse.file.notexitse");
                    }
                } else {
                    throw new IOException("apiabstract.manageResponse.reponsenotok");
                }
            } catch (RestClientException e) {
                throw new Exception("apiabstract.manageResponse.restexception");
            }catch (Exception e) {
                throw e;
            }catch (Exception e) {
                throw new Exception("apiabstract.manageResponse.exception");
            }finally {
                try{
                    if(responseInputStream != null){
                        responseInputStream.close();
                    }
                } catch (IOException e) {
                    throw new Exception("apiabstract.manageResponse.file.streamclose");
                }

            }

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
