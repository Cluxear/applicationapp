package com.tw.application.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.tw.application.domain.enumeration.RecrutementStatus;

/**
 * A DTO for the {@link com.tw.application.domain.ApplicationRecrutementStatus} entity.
 */
public class ApplicationRecrutementStatusDTO implements Serializable {
    
    private Long id;

    private Instant addedAt;

    private RecrutementStatus status;


    private Long applicationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }

    public RecrutementStatus getStatus() {
        return status;
    }

    public void setStatus(RecrutementStatus status) {
        this.status = status;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationRecrutementStatusDTO)) {
            return false;
        }

        return id != null && id.equals(((ApplicationRecrutementStatusDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationRecrutementStatusDTO{" +
            "id=" + getId() +
            ", addedAt='" + getAddedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", applicationId=" + getApplicationId() +
            "}";
    }
}
