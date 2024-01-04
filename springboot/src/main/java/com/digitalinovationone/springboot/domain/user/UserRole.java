package com.digitalinovationone.springboot.domain.user;

//Enumera em ordem de prioridade a partir de 0 as roles de usuário e envia para User.java
public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
