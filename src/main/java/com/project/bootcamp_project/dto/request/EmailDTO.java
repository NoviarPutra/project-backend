package com.project.bootcamp_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EmailDTO {

    @NotEmpty
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "email tidak sesuai")
    
    private String email;

    public @NotEmpty @NotNull @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @NotNull @NotBlank String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
