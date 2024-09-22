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
public class EipMessageAdvice implements MessageSourceMutator {

    //@Autowired
   // private ProcCommonService procCommonService;

    private Origin proc;

      @Override
    public final boolean beforeReceive(MessageSource<?> source) {
        //procCommonService.initProcLogBDD(ProcExecutionType.POLLING, proc);
        MDC.put("proc", proc.name());
        MDC.put("runId", UUID.randomUUID().toString());
        log.info("common.launching_proc_poller.debug");
        return true;
    }
    @Override
    public final Message<?> afterReceive(Message<?> message, MessageSource<?> messageSource) {
        if(message == null){
            log.info("common.no_result_poller.debug");
        }
        //procCommonService.endProcLogBDD(feedProc, comment);
        log.info("common.ending_proc_poller.debug");
        return message;
    }
}
