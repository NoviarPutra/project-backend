package com.project.bootcamp_project.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class AdminUpdateSelectionProcessDTO {

    @NotNull
    @NotEmpty
    private String candidateId;

    @NotNull
    private Integer round;

    @NotNull
    @NotEmpty
    private String status;

    @NotNull
    @NotEmpty
    private String notes;

    public @NotNull @NotEmpty String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(@NotNull @NotEmpty String candidateId) {
        this.candidateId = candidateId;
    }

    public @NotNull Integer getRound() {
        return round;
    }

    public void setRound(@NotNull Integer round) {
        this.round = round;
    }

    public @NotNull @NotEmpty String getStatus() {
        return status;
    }

    public void setStatus(@NotNull @NotEmpty String status) {
        this.status = status;
    }

    public @NotNull @NotEmpty String getNotes() {
        return notes;
    }

    public void setNotes(@NotNull @NotEmpty String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdminUpdateSelectionProcessDTO{");
        sb.append("candidateId='").append(candidateId).append('\'');
        sb.append(", round=").append(round);
        sb.append(", status='").append(status).append('\'');
        sb.append(", notes='").append(notes).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdminUpdateSelectionProcessDTO that = (AdminUpdateSelectionProcessDTO) object;
        return Objects.equals(candidateId, that.candidateId) && Objects.equals(round, that.round) && Objects.equals(status, that.status) && Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId, round, status, notes);
    }

}
