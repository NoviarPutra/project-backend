package com.project.bootcamp_project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.entity.User;

import java.time.LocalDateTime;

public class InterviewScheduleResponseDTO {

    private String id;

    private String candidate;

    @JsonIgnore
    private String user;

    private String interviewer;

    private LocalDateTime scheduleTime;

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

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getUser() {
        return interviewer;
    }

    public void setUser(User user) {
        this.interviewer = user.getEmail();
    }

    public LocalDateTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalDateTime scheduleTime) {
        this.scheduleTime = scheduleTime;
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

}

