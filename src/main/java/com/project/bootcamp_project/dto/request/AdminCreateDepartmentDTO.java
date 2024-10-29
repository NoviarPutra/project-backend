package com.project.bootcamp_project.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class AdminCreateDepartmentDTO {

    @NotEmpty
    @NotNull
    private String name;

    public @NotEmpty @NotNull String getName() {
        return name;
    }

    public void setName(@NotEmpty @NotNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdminCreateDepartmentDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdminCreateDepartmentDTO that = (AdminCreateDepartmentDTO) object;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

}
