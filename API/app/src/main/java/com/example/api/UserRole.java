package com.example.api;

public class UserRole {

    private Integer idUserRole;
    private String role;

    public UserRole(Integer idUserRole, String role) {
        this.idUserRole = idUserRole;
        this.role = role;
    }

    public Integer getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
