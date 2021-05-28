package com.tw.application.repository;

import com.tw.application.domain.RecrutementStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RecrutementStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecrutementStatusRepository extends JpaRepository<RecrutementStatus, Long> {
}
