package com.tw.application.service;

import com.tw.application.service.dto.ApplicationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tw.application.domain.Application}.
 */
public interface ApplicationService {

    /**
     * Save a application.
     *
     * @param applicationDTO the entity to save.
     * @return the persisted entity.
     */
    ApplicationDTO save(ApplicationDTO applicationDTO);

    /**
     * Get all the applications.
     *
     * @return the list of entities.
     */
    List<ApplicationDTO> findAll();


    /**
     * Get the "id" application.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApplicationDTO> findOne(Long id);

    /**
     * Delete the "id" application.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
