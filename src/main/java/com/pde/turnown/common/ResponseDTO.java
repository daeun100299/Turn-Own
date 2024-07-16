package com.pde.turnown.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ResponseDTO {
    private HttpStatus status;
    private String message;
    private Object data;

    public ResponseDTO(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

