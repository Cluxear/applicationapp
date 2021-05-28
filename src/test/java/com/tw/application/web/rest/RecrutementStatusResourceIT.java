package com.tw.application.web.rest;

import com.tw.application.ApplicationappApp;
import com.tw.application.config.TestSecurityConfiguration;
import com.tw.application.domain.RecrutementStatus;
import com.tw.application.repository.RecrutementStatusRepository;
import com.tw.application.service.RecrutementStatusService;
import com.tw.application.service.dto.RecrutementStatusDTO;
import com.tw.application.service.mapper.RecrutementStatusMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RecrutementStatusResource} REST controller.
 */
@SpringBootTest(classes = { ApplicationappApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class RecrutementStatusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private RecrutementStatusRepository recrutementStatusRepository;

    @Autowired
    private RecrutementStatusMapper recrutementStatusMapper;

    @Autowired
    private RecrutementStatusService recrutementStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRecrutementStatusMockMvc;

    private RecrutementStatus recrutementStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecrutementStatus createEntity(EntityManager em) {
        RecrutementStatus recrutementStatus = new RecrutementStatus()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return recrutementStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecrutementStatus createUpdatedEntity(EntityManager em) {
        RecrutementStatus recrutementStatus = new RecrutementStatus()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return recrutementStatus;
    }

    @BeforeEach
    public void initTest() {
        recrutementStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecrutementStatus() throws Exception {
        int databaseSizeBeforeCreate = recrutementStatusRepository.findAll().size();
        // Create the RecrutementStatus
        RecrutementStatusDTO recrutementStatusDTO = recrutementStatusMapper.toDto(recrutementStatus);
        restRecrutementStatusMockMvc.perform(post("/api/recrutement-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recrutementStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the RecrutementStatus in the database
        List<RecrutementStatus> recrutementStatusList = recrutementStatusRepository.findAll();
        assertThat(recrutementStatusList).hasSize(databaseSizeBeforeCreate + 1);
        RecrutementStatus testRecrutementStatus = recrutementStatusList.get(recrutementStatusList.size() - 1);
        assertThat(testRecrutementStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRecrutementStatus.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createRecrutementStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recrutementStatusRepository.findAll().size();

        // Create the RecrutementStatus with an existing ID
        recrutementStatus.setId(1L);
        RecrutementStatusDTO recrutementStatusDTO = recrutementStatusMapper.toDto(recrutementStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecrutementStatusMockMvc.perform(post("/api/recrutement-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recrutementStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecrutementStatus in the database
        List<RecrutementStatus> recrutementStatusList = recrutementStatusRepository.findAll();
        assertThat(recrutementStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRecrutementStatuses() throws Exception {
        // Initialize the database
        recrutementStatusRepository.saveAndFlush(recrutementStatus);

        // Get all the recrutementStatusList
        restRecrutementStatusMockMvc.perform(get("/api/recrutement-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recrutementStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getRecrutementStatus() throws Exception {
        // Initialize the database
        recrutementStatusRepository.saveAndFlush(recrutementStatus);

        // Get the recrutementStatus
        restRecrutementStatusMockMvc.perform(get("/api/recrutement-statuses/{id}", recrutementStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(recrutementStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingRecrutementStatus() throws Exception {
        // Get the recrutementStatus
        restRecrutementStatusMockMvc.perform(get("/api/recrutement-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecrutementStatus() throws Exception {
        // Initialize the database
        recrutementStatusRepository.saveAndFlush(recrutementStatus);

        int databaseSizeBeforeUpdate = recrutementStatusRepository.findAll().size();

        // Update the recrutementStatus
        RecrutementStatus updatedRecrutementStatus = recrutementStatusRepository.findById(recrutementStatus.getId()).get();
        // Disconnect from session so that the updates on updatedRecrutementStatus are not directly saved in db
        em.detach(updatedRecrutementStatus);
        updatedRecrutementStatus
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        RecrutementStatusDTO recrutementStatusDTO = recrutementStatusMapper.toDto(updatedRecrutementStatus);

        restRecrutementStatusMockMvc.perform(put("/api/recrutement-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recrutementStatusDTO)))
            .andExpect(status().isOk());

        // Validate the RecrutementStatus in the database
        List<RecrutementStatus> recrutementStatusList = recrutementStatusRepository.findAll();
        assertThat(recrutementStatusList).hasSize(databaseSizeBeforeUpdate);
        RecrutementStatus testRecrutementStatus = recrutementStatusList.get(recrutementStatusList.size() - 1);
        assertThat(testRecrutementStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRecrutementStatus.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingRecrutementStatus() throws Exception {
        int databaseSizeBeforeUpdate = recrutementStatusRepository.findAll().size();

        // Create the RecrutementStatus
        RecrutementStatusDTO recrutementStatusDTO = recrutementStatusMapper.toDto(recrutementStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecrutementStatusMockMvc.perform(put("/api/recrutement-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recrutementStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecrutementStatus in the database
        List<RecrutementStatus> recrutementStatusList = recrutementStatusRepository.findAll();
        assertThat(recrutementStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecrutementStatus() throws Exception {
        // Initialize the database
        recrutementStatusRepository.saveAndFlush(recrutementStatus);

        int databaseSizeBeforeDelete = recrutementStatusRepository.findAll().size();

        // Delete the recrutementStatus
        restRecrutementStatusMockMvc.perform(delete("/api/recrutement-statuses/{id}", recrutementStatus.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RecrutementStatus> recrutementStatusList = recrutementStatusRepository.findAll();
        assertThat(recrutementStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
