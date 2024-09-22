package org.example.eip.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "eip.directory.messages")
@Getter
@Setter
@Validated
public class MessagesProperties {
    @NotBlank
    private String root;
    @NotBlank
    private String depot;
    @NotBlank
    private String conserve;
    @NotBlank
    private String rejet;
}
