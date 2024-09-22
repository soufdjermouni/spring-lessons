package org.example.eip.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Origin {
    PROC(1),

    ;

    private Integer code;
}
