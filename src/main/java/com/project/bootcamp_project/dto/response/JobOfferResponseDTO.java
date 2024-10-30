package com.project.bootcamp_project.dto.response;

import com.project.bootcamp_project.dto.OfferStatus;
import com.project.bootcamp_project.entity.Candidate;

import java.time.LocalDateTime;
import java.util.Objects;


public class JobOfferResponseDTO {

    private String id;

    private String candidate;

    private String offerDetails;

    private OfferStatus status;

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

    public String getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(String offerDetails) {
        this.offerDetails = offerDetails;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
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
        final StringBuffer sb = new StringBuffer("JobOfferResponseDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", candidate=").append(candidate);
        sb.append(", offerDetails='").append(offerDetails).append('\'');
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
        JobOfferResponseDTO that = (JobOfferResponseDTO) object;
        return Objects.equals(id, that.id) && Objects.equals(candidate, that.candidate) && Objects.equals(offerDetails, that.offerDetails) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, candidate, offerDetails, status, createdAt, updatedAt);
    }

}
