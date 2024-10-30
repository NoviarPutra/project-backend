package com.project.bootcamp_project.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class AdminUpdateInterviewScheduleDTO {

    @NotNull
    @NotEmpty
    private String candidateId;

    @NotNull
    @NotEmpty
    private String interviewerId;

    @NotNull
    private LocalDateTime scheduleTime;

    public @NotNull @NotEmpty String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(@NotNull @NotEmpty String candidateId) {
        this.candidateId = candidateId;
    }

    public @NotNull @NotEmpty String getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(@NotNull @NotEmpty String interviewerId) {
        this.interviewerId = interviewerId;
    }

    public @NotNull LocalDateTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(@NotNull LocalDateTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdminUpdateInterviewScheduleDTO{");
        sb.append("candidateId='").append(candidateId).append('\'');
        sb.append(", interviewerId='").append(interviewerId).append('\'');
        sb.append(", scheduleTime=").append(scheduleTime);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdminUpdateInterviewScheduleDTO that = (AdminUpdateInterviewScheduleDTO) object;
        return Objects.equals(candidateId, that.candidateId) && Objects.equals(interviewerId, that.interviewerId) && Objects.equals(scheduleTime, that.scheduleTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId, interviewerId, scheduleTime);
    }
}
