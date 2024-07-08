package com.pde.turnown.user.dto;

import com.pde.turnown.common.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private int userNo;
    private String userId;
    private String userPass;
    private String userName;
    private String userEmail;
    private UserRole userRole;
    private char userState;

    public UserDTO(String userId, String userPass, String userName, String userEmail, UserRole userRole, char userState) {
        this.userId = userId;
        this.userPass = userPass;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userState = userState;
    }
}
