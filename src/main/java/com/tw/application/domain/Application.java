package com.tw.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.tw.application.domain.enumeration.ConclusionType;

/**
 * A Application.
 */
@Entity
@Table(name = "application")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_listed")
    private Boolean shortListed;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "conclusion")
    private ConclusionType conclusion;

    @OneToMany(mappedBy = "application")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ApplicationRecrutementStatus> statuses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isShortListed() {
        return shortListed;
    }

    public Application shortListed(Boolean shortListed) {
        this.shortListed = shortListed;
        return this;
    }

    public void setShortListed(Boolean shortListed) {
        this.shortListed = shortListed;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Application creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public ConclusionType getConclusion() {
        return conclusion;
    }

    public Application conclusion(ConclusionType conclusion) {
        this.conclusion = conclusion;
        return this;
    }

    public void setConclusion(ConclusionType conclusion) {
        this.conclusion = conclusion;
    }

    public Set<ApplicationRecrutementStatus> getStatuses() {
        return statuses;
    }

    public Application statuses(Set<ApplicationRecrutementStatus> applicationRecrutementStatuses) {
        this.statuses = applicationRecrutementStatuses;
        return this;
    }

    public Application addStatus(ApplicationRecrutementStatus applicationRecrutementStatus) {
        this.statuses.add(applicationRecrutementStatus);
        applicationRecrutementStatus.setApplication(this);
        return this;
    }

    public Application removeStatus(ApplicationRecrutementStatus applicationRecrutementStatus) {
        this.statuses.remove(applicationRecrutementStatus);
        applicationRecrutementStatus.setApplication(null);
        return this;
    }

    public void setStatuses(Set<ApplicationRecrutementStatus> applicationRecrutementStatuses) {
        this.statuses = applicationRecrutementStatuses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Application)) {
            return false;
        }
        return id != null && id.equals(((Application) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Application{" +
            "id=" + getId() +
            ", shortListed='" + isShortListed() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", conclusion='" + getConclusion() + "'" +
            "}";
    }
}
