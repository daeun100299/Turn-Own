package com.pde.turnown.common;

public enum UserRole {
    USER("USER"), ADMIN("ADMIN"), ALL("USER,ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}