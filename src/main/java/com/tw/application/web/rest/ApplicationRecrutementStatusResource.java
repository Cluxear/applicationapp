package com.tw.application.web.rest;

import com.tw.application.service.ApplicationRecrutementStatusService;
import com.tw.application.web.rest.errors.BadRequestAlertException;
import com.tw.application.service.dto.ApplicationRecrutementStatusDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.tw.application.domain.ApplicationRecrutementStatus}.
 */
@RestController
@RequestMapping("/api")
public class ApplicationRecrutementStatusResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationRecrutementStatusResource.class);

    private static final String ENTITY_NAME = "applicationappApplicationRecrutementStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApplicationRecrutementStatusService applicationRecrutementStatusService;

    public ApplicationRecrutementStatusResource(ApplicationRecrutementStatusService applicationRecrutementStatusService) {
        this.applicationRecrutementStatusService = applicationRecrutementStatusService;
    }

    /**
     * {@code POST  /application-recrutement-statuses} : Create a new applicationRecrutementStatus.
     *
     * @param applicationRecrutementStatusDTO the applicationRecrutementStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new applicationRecrutementStatusDTO, or with status {@code 400 (Bad Request)} if the applicationRecrutementStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/application-recrutement-statuses")
    public ResponseEntity<ApplicationRecrutementStatusDTO> createApplicationRecrutementStatus(@RequestBody ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ApplicationRecrutementStatus : {}", applicationRecrutementStatusDTO);
        if (applicationRecrutementStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new applicationRecrutementStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApplicationRecrutementStatusDTO result = applicationRecrutementStatusService.save(applicationRecrutementStatusDTO);
        return ResponseEntity.created(new URI("/api/application-recrutement-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /application-recrutement-statuses} : Updates an existing applicationRecrutementStatus.
     *
     * @param applicationRecrutementStatusDTO the applicationRecrutementStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated applicationRecrutementStatusDTO,
     * or with status {@code 400 (Bad Request)} if the applicationRecrutementStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the applicationRecrutementStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/application-recrutement-statuses")
    public ResponseEntity<ApplicationRecrutementStatusDTO> updateApplicationRecrutementStatus(@RequestBody ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ApplicationRecrutementStatus : {}", applicationRecrutementStatusDTO);
        if (applicationRecrutementStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApplicationRecrutementStatusDTO result = applicationRecrutementStatusService.save(applicationRecrutementStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applicationRecrutementStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /application-recrutement-statuses} : get all the applicationRecrutementStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of applicationRecrutementStatuses in body.
     */
    @GetMapping("/application-recrutement-statuses")
    public List<ApplicationRecrutementStatusDTO> getAllApplicationRecrutementStatuses() {
        log.debug("REST request to get all ApplicationRecrutementStatuses");
        return applicationRecrutementStatusService.findAll();
    }

    /**
     * {@code GET  /application-recrutement-statuses/:id} : get the "id" applicationRecrutementStatus.
     *
     * @param id the id of the applicationRecrutementStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the applicationRecrutementStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/application-recrutement-statuses/{id}")
    public ResponseEntity<ApplicationRecrutementStatusDTO> getApplicationRecrutementStatus(@PathVariable Long id) {
        log.debug("REST request to get ApplicationRecrutementStatus : {}", id);
        Optional<ApplicationRecrutementStatusDTO> applicationRecrutementStatusDTO = applicationRecrutementStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationRecrutementStatusDTO);
    }

    /**
     * {@code DELETE  /application-recrutement-statuses/:id} : delete the "id" applicationRecrutementStatus.
     *
     * @param id the id of the applicationRecrutementStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/application-recrutement-statuses/{id}")
    public ResponseEntity<Void> deleteApplicationRecrutementStatus(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationRecrutementStatus : {}", id);
        applicationRecrutementStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
