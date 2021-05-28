package com.tw.application.repository;

import com.tw.application.domain.ApplicationRecrutementStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ApplicationRecrutementStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationRecrutementStatusRepository extends JpaRepository<ApplicationRecrutementStatus, Long> {
}
