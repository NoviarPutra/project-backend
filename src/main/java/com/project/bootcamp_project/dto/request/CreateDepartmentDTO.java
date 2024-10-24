package com.project.bootcamp_project.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateDepartmentDTO {

    @NotNull
    @NotEmpty
    private String name;

    @Override
    public String toString() {
        return "CreateDepartmentDTO{" +
                "name='" + name + '\'' +
                '}';
    }

    public @NotNull @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotNull @NotEmpty String name) {
        this.name = name.toUpperCase();
    }
}
