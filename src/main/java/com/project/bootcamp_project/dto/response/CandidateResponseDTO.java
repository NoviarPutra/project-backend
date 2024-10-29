package com.project.bootcamp_project.dto.response;

import com.project.bootcamp_project.dto.CandidateStatus;
import com.project.bootcamp_project.entity.JobPosition;
import com.project.bootcamp_project.entity.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class CandidateResponseDTO {

    private String id;

    private String jobPosition;

    private String user;

    private String resume;

    private CandidateStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition.getTitle();
    }

    public String getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user.getEmail();
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public CandidateStatus getStatus() {
        return status;
    }

    public void setStatus(CandidateStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CandidateResponseDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", jobPosition=").append(jobPosition);
        sb.append(", user=").append(user);
        sb.append(", resume='").append(resume).append('\'');
        sb.append(", status=").append(status);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CandidateResponseDTO that = (CandidateResponseDTO) object;
        return Objects.equals(id, that.id) && Objects.equals(jobPosition, that.jobPosition) && Objects.equals(user, that.user) && Objects.equals(resume, that.resume) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobPosition, user, resume, status, createdAt, updatedAt);
    }
}
