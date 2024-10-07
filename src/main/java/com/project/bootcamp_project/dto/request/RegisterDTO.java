package com.project.bootcamp_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RegisterDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "email tidak sesuai")
    private String email;

    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "password harus terdiri dari huruf kecil, huruf kapital, angka, dan simbol")
    private String password;

    public @NotNull @NotBlank @NotEmpty @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "email tidak sesuai") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @NotBlank @NotEmpty @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "email tidak sesuai") String email) {
        this.email = email;
    }

    public @NotNull @NotBlank @NotEmpty @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "password harus terdiri dari huruf kecil, huruf kapital, angka, dan simbol") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotBlank @NotEmpty @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "password harus terdiri dari huruf kecil, huruf kapital, angka, dan simbol") String password) {
        this.password = password;
    }
}
