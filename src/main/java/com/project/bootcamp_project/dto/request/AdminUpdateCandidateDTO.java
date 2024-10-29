package com.project.bootcamp_project.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class AdminUpdateCandidateDTO {

    @NotEmpty
    @NotNull
    private String status;

    public @NotEmpty @NotNull String getStatus() {
        return status;
    }

    public void setStatus(@NotEmpty @NotNull String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdminUpdateCandidateDTO{");
        sb.append("status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdminUpdateCandidateDTO that = (AdminUpdateCandidateDTO) object;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }

}
