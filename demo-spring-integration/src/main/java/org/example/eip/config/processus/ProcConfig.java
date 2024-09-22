package org.example.eip.config.processus;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.example.eip.domain.EipMessageWithoutLogAdvice;
import org.example.eip.exception.NotEnoughDiskSpaceException;
import org.example.eip.properties.MessagesProperties;
import org.example.eip.properties.ProcTimerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.RegexPatternFileListFilter;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.io.File;

import static org.example.eip.constants.Origin.PROC;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ProcConfig {

    public static final String MESSAGE_FILE_PATTERN = "^ES_.*";
    private static final String PROC_SERVICE_NAME = "procService";

    private final MessagesProperties messagesProperties;
    private final ProcTimerProperties timerProperties;
    private final MessageHandler messageRejetDirectory;
    private final MessageHandler messageConserveDirectory;
    private final MessageHandler messageDepotDirectory;

    private final ApplicationContext ctx;

    @Bean
    public MessageChannel errorMessageProcChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageSource<File> messageProcDirectory() {
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new File(messagesProperties.getDepot()));
        sourceReader.setFilter(new RegexPatternFileListFilter(MESSAGE_FILE_PATTERN));
        sourceReader.setAutoCreateDirectory(false);

        return sourceReader;
    }

    @Bean
    public IntegrationFlow processMessageProcError() {
        return IntegrationFlow.from(errorMessageProcChannel())
                .publishSubscribeChannel(s -> s
                        .applySequence(true)
                        .subscribe(f -> f.handle(PROC_SERVICE_NAME, "endProcLogError"))
                        .subscribe(f -> f
                                .filter(MessagingException.class, m -> m.getCause() instanceof NotEnoughDiskSpaceException)
                                .transform(MessagingException::getFailedMessage)
                                .handle(messageDepotDirectory))
                        .subscribe(f -> f
                                .filter(MessagingException.class, m -> m.getCause() instanceof Exception
                                        && !(m.getCause() instanceof NotEnoughDiskSpaceException))
                                .transform(MessagingException::getFailedMessage)
                                .handle(messageRejetDirectory))
                        .subscribe(f ->  f
                                .filter(MessagingException.class, m -> !(m.getCause() instanceof Exception))
                                .log(LoggingHandler.Level.ERROR, m -> ExceptionUtils.getStackTrace(((MessagingException)m.getPayload())))
                                .transform(MessagingException::getFailedMessage)
                                .handle(messageRejetDirectory))
                )
                .get();
    }

   @Bean
    public IntegrationFlow processMessage() {
        return IntegrationFlow
                .from(messageProcDirectory(), c -> c
                        .id("proc")
                        .autoStartup(false)
                        .poller(Pollers.fixedDelay(timerProperties.getProc())
                        .advice((EipMessageWithoutLogAdvice) ctx.getBean("createProcWithoutLogAdvice", PROC))
                        .errorChannel(errorMessageProcChannel())))
                .publishSubscribeChannel(s -> s
                        .subscribe(f -> f.handle(PROC_SERVICE_NAME, "initProcLog"))
                        .subscribe(f -> f.handle(PROC_SERVICE_NAME, "processMessage"))
                        .subscribe(f -> f.handle(PROC_SERVICE_NAME, "endProcLog"))
                        .subscribe(f -> f.handle(messageConserveDirectory)))
                .get();
    }
}
