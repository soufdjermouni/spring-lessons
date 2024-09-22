package org.example;

import lombok.RequiredArgsConstructor;
import org.example.eip.constants.Origin;
import org.example.eip.properties.MessagesProperties;
import org.example.eip.properties.ParametersProperties;
import org.example.eip.properties.ProcTimerProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import static org.example.eip.constants.Origin.PROC;

@SpringBootApplication
@EnableConfigurationProperties({MessagesProperties.class, ProcTimerProperties.class, ParametersProperties.class })
@RequiredArgsConstructor
public class EipApplication implements ApplicationRunner {

    private final MessageChannel controlBus;

    public static void main(String[] args) {
        SpringApplication.run(EipApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        runProc(PROC);
    }

    private void runProc(Origin proc) {
        controlBus.send(new GenericMessage<>("@" + proc.name().toLowerCase() + ".start()"));
    }
}