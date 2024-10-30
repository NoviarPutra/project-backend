package com.project.bootcamp_project.dto.response;

import com.project.bootcamp_project.dto.SelectionStatus;
import com.project.bootcamp_project.entity.Candidate;

import java.time.LocalDateTime;
import java.util.Objects;

public class SelectionProcessResponseDTO {

    private String id;

    private String candidate;

    private Integer round;

    private SelectionStatus status;

    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate.getUser().getEmail();
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public SelectionStatus getStatus() {
        return status;
    }

    public void setStatus(SelectionStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        final StringBuffer sb = new StringBuffer("SelectionProcessResponseDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", candidate=").append(candidate);
        sb.append(", round=").append(round);
        sb.append(", status=").append(status);
        sb.append(", notes='").append(notes).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SelectionProcessResponseDTO that = (SelectionProcessResponseDTO) object;
        return Objects.equals(id, that.id) && Objects.equals(candidate, that.candidate) && Objects.equals(round, that.round) && status == that.status && Objects.equals(notes, that.notes) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, candidate, round, status, notes, createdAt, updatedAt);
    }

}
