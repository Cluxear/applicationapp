package com.tw.application.service;

import com.tw.application.service.dto.ApplicationRecrutementStatusDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tw.application.domain.ApplicationRecrutementStatus}.
 */
public interface ApplicationRecrutementStatusService {

    /**
     * Save a applicationRecrutementStatus.
     *
     * @param applicationRecrutementStatusDTO the entity to save.
     * @return the persisted entity.
     */
    ApplicationRecrutementStatusDTO save(ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO);

    /**
     * Get all the applicationRecrutementStatuses.
     *
     * @return the list of entities.
     */
    List<ApplicationRecrutementStatusDTO> findAll();


    /**
     * Get the "id" applicationRecrutementStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApplicationRecrutementStatusDTO> findOne(Long id);

    /**
     * Delete the "id" applicationRecrutementStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
