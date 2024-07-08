package com.pde.turnown.user.entity;

import com.pde.turnown.common.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tbl_user")
public class User {
    @Id
    @Column(name = "USER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_PASS")
    private String userPass;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_ROLE")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "USER_STATE")
    private char userState;

    public List<String> getRoleList(){
        if(this.userRole.getRole().length() > 0){
            return Arrays.asList(this.userRole.getRole().split(","));
        }
        return new ArrayList<>();
    }

    public User() {
    }

    @Builder
    public User(String userId, String userPass, String userName, String userEmail, UserRole userRole, char userState) {
        this.userId = userId;
        this.userPass = userPass;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userState = userState;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRole=" + userRole +
                ", userState='" + userState + '\'' +
                '}';
    }
}