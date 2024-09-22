package org.example.eip.config.processus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eip.constants.Origin;
import org.example.eip.domain.EipMessageAdvice;
import org.example.eip.domain.EipMessageWithoutLogAdvice;
import org.example.eip.properties.MessagesProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import static org.example.eip.utils.EipUtils.getFileWritingMessageHandler;

/**
 * Classe de configuration commune à l'ensemble des processus DIAG
 *
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class ProcCommonConfig {

    private final MessagesProperties messagesProperties;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    protected EipMessageAdvice createProcAdvice(Origin proc) {
        EipMessageAdvice bean = new EipMessageAdvice();
        bean.setProc(proc);
        return bean;
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    protected EipMessageWithoutLogAdvice createProcWithoutLogAdvice(Origin proc) {
        EipMessageWithoutLogAdvice bean = new EipMessageWithoutLogAdvice();
        bean.setProc(proc);
        return bean;
    }

    @Bean
    public MessageHandler messageConserveDirectory() {
        return getFileWritingMessageHandler(messagesProperties.getConserve(), messageTemporalFileSuffix(),false);
    }

    @Bean
    public MessageHandler messageRejetDirectory() {
        return getFileWritingMessageHandler(messagesProperties.getRejet(), messageTemporalAndUUIDFileSuffix(),false);
    }

    @Bean
    public MessageHandler messageDepotDirectory() {
        return getFileWritingMessageHandler(messagesProperties.getDepot(), null,false);
    }

    @Bean
    @Qualifier("messageTemporalFileSuffix")
    public DefaultFileNameGenerator messageTemporalFileSuffix() {
        DefaultFileNameGenerator suffixFileNameGenerator = new DefaultFileNameGenerator();
        suffixFileNameGenerator.setHeaderName("id");
        suffixFileNameGenerator.setExpression("T(org.example.eip.utils.EipUtils).renameMessageFile(payload.name)");
        return suffixFileNameGenerator;
    }

    @Bean
    @Qualifier("messageTemporalAndUUIDFileSuffix")
    public DefaultFileNameGenerator messageTemporalAndUUIDFileSuffix() {
        DefaultFileNameGenerator suffixFileNameGenerator = new DefaultFileNameGenerator();
        suffixFileNameGenerator.setHeaderName("id");
        suffixFileNameGenerator.setExpression("T(org.example.eip.utils.EipUtils).renameMessageFile(payload.name)");
        return suffixFileNameGenerator;
    }

    @Bean
    public MessageChannel controlBus() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow controlBusFlow() {
        return IntegrationFlow.from("controlBus")
                .controlBus()
                .get();
    }
}
