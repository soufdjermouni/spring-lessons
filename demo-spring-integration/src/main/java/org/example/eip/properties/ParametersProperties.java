package org.example.eip.properties;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "eip.parameters")
@Getter
@Setter
@Validated
public class ParametersProperties {
    @Min(1)
    private int nbThreadsForPROC;
    @Max(100)
    @Value("${eip.parameters.usedDiskSpacePercentageMax.messages}")
    private int usedDiskSpacemessages;
}
