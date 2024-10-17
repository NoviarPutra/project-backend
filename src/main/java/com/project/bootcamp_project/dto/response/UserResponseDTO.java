package com.project.bootcamp_project.dto.response;

import java.util.Set;

public class UserResponseDTO {

    private String id;
    private String email;
    private String role;
    private Set<String> accessPermissions;

    public UserResponseDTO(String id, String email, String role, Set<String> accessPermissions) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.accessPermissions = accessPermissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<String> getAccessPermissions() {
        return accessPermissions;
    }

    public void setAccessPermissions(Set<String> accessPermissions) {
        this.accessPermissions = accessPermissions;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", accessPermissions=" + accessPermissions +
                '}';
    }
}
