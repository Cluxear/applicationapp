package com.tw.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.tw.application.domain.enumeration.RecrutementStatus;

/**
 * A ApplicationRecrutementStatus.
 */
@Entity
@Table(name = "application_recrutement_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApplicationRecrutementStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "added_at")
    private Instant addedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RecrutementStatus status;

    @ManyToOne
    @JsonIgnoreProperties(value = "statuses", allowSetters = true)
    private Application application;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getAddedAt() {
        return addedAt;
    }

    public ApplicationRecrutementStatus addedAt(Instant addedAt) {
        this.addedAt = addedAt;
        return this;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }

    public RecrutementStatus getStatus() {
        return status;
    }

    public ApplicationRecrutementStatus status(RecrutementStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RecrutementStatus status) {
        this.status = status;
    }

    public Application getApplication() {
        return application;
    }

    public ApplicationRecrutementStatus application(Application application) {
        this.application = application;
        return this;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationRecrutementStatus)) {
            return false;
        }
        return id != null && id.equals(((ApplicationRecrutementStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationRecrutementStatus{" +
            "id=" + getId() +
            ", addedAt='" + getAddedAt() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
