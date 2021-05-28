package com.tw.application.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.tw.application.domain.enumeration.ConclusionType;

/**
 * A DTO for the {@link com.tw.application.domain.Application} entity.
 */
public class ApplicationDTO implements Serializable {

    private Long id;

    private Boolean shortListed;

    private Instant creationDate;

    private ConclusionType conclusion;

    private Long jobpostId;

    private String fullName;

    private String experienceDurationName;


    private String candidateId;

    private String email;

    private long phone;

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public Boolean getShortListed() {
        return shortListed;
    }

    public Long getJobpostId() {
        return jobpostId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setJobpostId(Long jobpostId) {
        this.jobpostId = jobpostId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExperienceDurationName() {
        return experienceDurationName;
    }

    public void setExperienceDurationName(String experienceYears) {

        this.experienceDurationName = experienceYears;
    }
    public Boolean isShortListed() {
        return shortListed;
    }

    public void setShortListed(Boolean shortListed) {
        this.shortListed = shortListed;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public ConclusionType getConclusion() {
        return conclusion;
    }

    public void setConclusion(ConclusionType conclusion) {
        this.conclusion = conclusion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationDTO)) {
            return false;
        }

        return id != null && id.equals(((ApplicationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationDTO{" +
            "id=" + getId() +
            ", shortListed='" + isShortListed() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", conclusion='" + getConclusion() + "'" +
            ", name='" + getFullName() + "'" +
            ",candidateId'" + getCandidateId() + "'" +
            "}";
    }
}
