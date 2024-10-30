package com.project.bootcamp_project.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class AdminUpdateJobOfferDTO {

    @NotNull
    @NotEmpty
    private String candidateId;

    @NotNull
    @NotEmpty
    private String offerDetails;

    @NotNull
    @NotEmpty
    private String status;

    public @NotNull @NotEmpty String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(@NotNull @NotEmpty String candidateId) {
        this.candidateId = candidateId;
    }

    public @NotNull @NotEmpty String getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(@NotNull @NotEmpty String offerDetails) {
        this.offerDetails = offerDetails;
    }

    public @NotNull @NotEmpty String getStatus() {
        return status;
    }

    public void setStatus(@NotNull @NotEmpty String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdminUpdateJobOfferDTO{");
        sb.append("candidateId='").append(candidateId).append('\'');
        sb.append(", offerDetails='").append(offerDetails).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdminUpdateJobOfferDTO that = (AdminUpdateJobOfferDTO) object;
        return Objects.equals(candidateId, that.candidateId) && Objects.equals(offerDetails, that.offerDetails) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId, offerDetails, status);
    }

}
