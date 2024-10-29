package com.project.bootcamp_project.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class CreateCandidateDTO {

    @NotNull
    @NotEmpty
    private String jobPositionId;

    @NotNull
    @NotEmpty
    private String resume;

    public @NotNull @NotEmpty String getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(@NotNull @NotEmpty String jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public @NotNull @NotEmpty String getResume() {
        return resume;
    }

    public void setResume(@NotNull @NotEmpty String resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CreateCandidateDTO{");
        sb.append("jobPositionId='").append(jobPositionId).append('\'');
        sb.append(", resume='").append(resume).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CreateCandidateDTO that = (CreateCandidateDTO) object;
        return Objects.equals(jobPositionId, that.jobPositionId) && Objects.equals(resume, that.resume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobPositionId, resume);
    }

}
