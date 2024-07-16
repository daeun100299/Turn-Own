package com.pde.turnown.auth.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {
    private String memberID;
    private String memberPW;

    public LoginDTO() {
    }

    public LoginDTO(String memberID, String memberPW) {
        this.memberID = memberID;
        this.memberPW = memberPW;
    }
}
