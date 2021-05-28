package com.tw.application.web.rest;

import com.tw.application.service.RecrutementStatusService;
import com.tw.application.web.rest.errors.BadRequestAlertException;
import com.tw.application.service.dto.RecrutementStatusDTO;

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
 * REST controller for managing {@link com.tw.application.domain.RecrutementStatus}.
 */
@RestController
@RequestMapping("/api")
public class RecrutementStatusResource {

    private final Logger log = LoggerFactory.getLogger(RecrutementStatusResource.class);

    private static final String ENTITY_NAME = "applicationappRecrutementStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecrutementStatusService recrutementStatusService;

    public RecrutementStatusResource(RecrutementStatusService recrutementStatusService) {
        this.recrutementStatusService = recrutementStatusService;
    }

    /**
     * {@code POST  /recrutement-statuses} : Create a new recrutementStatus.
     *
     * @param recrutementStatusDTO the recrutementStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recrutementStatusDTO, or with status {@code 400 (Bad Request)} if the recrutementStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/recrutement-statuses")
    public ResponseEntity<RecrutementStatusDTO> createRecrutementStatus(@RequestBody RecrutementStatusDTO recrutementStatusDTO) throws URISyntaxException {
        log.debug("REST request to save RecrutementStatus : {}", recrutementStatusDTO);
        if (recrutementStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new recrutementStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecrutementStatusDTO result = recrutementStatusService.save(recrutementStatusDTO);
        return ResponseEntity.created(new URI("/api/recrutement-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /recrutement-statuses} : Updates an existing recrutementStatus.
     *
     * @param recrutementStatusDTO the recrutementStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recrutementStatusDTO,
     * or with status {@code 400 (Bad Request)} if the recrutementStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recrutementStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/recrutement-statuses")
    public ResponseEntity<RecrutementStatusDTO> updateRecrutementStatus(@RequestBody RecrutementStatusDTO recrutementStatusDTO) throws URISyntaxException {
        log.debug("REST request to update RecrutementStatus : {}", recrutementStatusDTO);
        if (recrutementStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecrutementStatusDTO result = recrutementStatusService.save(recrutementStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recrutementStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /recrutement-statuses} : get all the recrutementStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recrutementStatuses in body.
     */
    @GetMapping("/recrutement-statuses")
    public List<RecrutementStatusDTO> getAllRecrutementStatuses() {
        log.debug("REST request to get all RecrutementStatuses");
        return recrutementStatusService.findAll();
    }

    /**
     * {@code GET  /recrutement-statuses/:id} : get the "id" recrutementStatus.
     *
     * @param id the id of the recrutementStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recrutementStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recrutement-statuses/{id}")
    public ResponseEntity<RecrutementStatusDTO> getRecrutementStatus(@PathVariable Long id) {
        log.debug("REST request to get RecrutementStatus : {}", id);
        Optional<RecrutementStatusDTO> recrutementStatusDTO = recrutementStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recrutementStatusDTO);
    }

    /**
     * {@code DELETE  /recrutement-statuses/:id} : delete the "id" recrutementStatus.
     *
     * @param id the id of the recrutementStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recrutement-statuses/{id}")
    public ResponseEntity<Void> deleteRecrutementStatus(@PathVariable Long id) {
        log.debug("REST request to delete RecrutementStatus : {}", id);
        recrutementStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
