package org.example.eip.properties;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.management.timer.Timer;

@ConfigurationProperties(prefix = "eip.timers")
@Getter
@Setter
@Validated
public class ProcTimerProperties {
    @Min(Timer.ONE_SECOND)
    private long proc;
}
