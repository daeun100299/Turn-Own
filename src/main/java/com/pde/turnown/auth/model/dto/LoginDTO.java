package com.pde.turnown.auth.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {
    private String userId;
    private String userPass;

    public LoginDTO(String userId, String userPass) {
        this.userId = userId;
        this.userPass = userPass;
    }
}
