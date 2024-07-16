package com.pde.turnown.common;

public enum Authority {
    LV1("LV1"), LV2("LV2"), LV3("LV3"), ADMIN("ADMIN");

    private String role;

    Authority(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}