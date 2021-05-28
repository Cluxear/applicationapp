package com.tw.application.service;

import com.tw.application.service.dto.RecrutementStatusDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tw.application.domain.RecrutementStatus}.
 */
public interface RecrutementStatusService {

    /**
     * Save a recrutementStatus.
     *
     * @param recrutementStatusDTO the entity to save.
     * @return the persisted entity.
     */
    RecrutementStatusDTO save(RecrutementStatusDTO recrutementStatusDTO);

    /**
     * Get all the recrutementStatuses.
     *
     * @return the list of entities.
     */
    List<RecrutementStatusDTO> findAll();


    /**
     * Get the "id" recrutementStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RecrutementStatusDTO> findOne(Long id);

    /**
     * Delete the "id" recrutementStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
