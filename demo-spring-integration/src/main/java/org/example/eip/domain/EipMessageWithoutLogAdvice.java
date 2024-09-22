package org.example.eip.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.eip.constants.Origin;
import org.slf4j.MDC;
import org.springframework.integration.aop.MessageSourceMutator;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@Slf4j
@Getter
@Setter
@Transactional
public class EipMessageWithoutLogAdvice implements MessageSourceMutator {

    private Origin proc;

    @Override
    public final boolean beforeReceive(MessageSource<?> source) {
        MDC.put("proc", proc.name());
        MDC.put("runId", UUID.randomUUID().toString());
        return true;
    }
    @Override
    public final Message<?> afterReceive(Message<?> message, MessageSource<?> messageSource) {
        return message;
    }
}
