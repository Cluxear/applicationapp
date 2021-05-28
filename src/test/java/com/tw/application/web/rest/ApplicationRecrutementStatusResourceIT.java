package com.tw.application.web.rest;

import com.tw.application.ApplicationappApp;
import com.tw.application.config.TestSecurityConfiguration;
import com.tw.application.domain.ApplicationRecrutementStatus;
import com.tw.application.repository.ApplicationRecrutementStatusRepository;
import com.tw.application.service.ApplicationRecrutementStatusService;
import com.tw.application.service.dto.ApplicationRecrutementStatusDTO;
import com.tw.application.service.mapper.ApplicationRecrutementStatusMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tw.application.domain.enumeration.RecrutementStatus;
/**
 * Integration tests for the {@link ApplicationRecrutementStatusResource} REST controller.
 */
@SpringBootTest(classes = { ApplicationappApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class ApplicationRecrutementStatusResourceIT {

    private static final Instant DEFAULT_ADDED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADDED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final RecrutementStatus DEFAULT_STATUS = RecrutementStatus.PRE_SELECTED;
    private static final RecrutementStatus UPDATED_STATUS = RecrutementStatus.HR_INTERVIEW_FIXED;

    @Autowired
    private ApplicationRecrutementStatusRepository applicationRecrutementStatusRepository;

    @Autowired
    private ApplicationRecrutementStatusMapper applicationRecrutementStatusMapper;

    @Autowired
    private ApplicationRecrutementStatusService applicationRecrutementStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApplicationRecrutementStatusMockMvc;

    private ApplicationRecrutementStatus applicationRecrutementStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApplicationRecrutementStatus createEntity(EntityManager em) {
        ApplicationRecrutementStatus applicationRecrutementStatus = new ApplicationRecrutementStatus()
            .addedAt(DEFAULT_ADDED_AT)
            .status(DEFAULT_STATUS);
        return applicationRecrutementStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApplicationRecrutementStatus createUpdatedEntity(EntityManager em) {
        ApplicationRecrutementStatus applicationRecrutementStatus = new ApplicationRecrutementStatus()
            .addedAt(UPDATED_ADDED_AT)
            .status(UPDATED_STATUS);
        return applicationRecrutementStatus;
    }

    @BeforeEach
    public void initTest() {
        applicationRecrutementStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createApplicationRecrutementStatus() throws Exception {
        int databaseSizeBeforeCreate = applicationRecrutementStatusRepository.findAll().size();
        // Create the ApplicationRecrutementStatus
        ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO = applicationRecrutementStatusMapper.toDto(applicationRecrutementStatus);
        restApplicationRecrutementStatusMockMvc.perform(post("/api/application-recrutement-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationRecrutementStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ApplicationRecrutementStatus in the database
        List<ApplicationRecrutementStatus> applicationRecrutementStatusList = applicationRecrutementStatusRepository.findAll();
        assertThat(applicationRecrutementStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ApplicationRecrutementStatus testApplicationRecrutementStatus = applicationRecrutementStatusList.get(applicationRecrutementStatusList.size() - 1);
        assertThat(testApplicationRecrutementStatus.getAddedAt()).isEqualTo(DEFAULT_ADDED_AT);
        assertThat(testApplicationRecrutementStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createApplicationRecrutementStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = applicationRecrutementStatusRepository.findAll().size();

        // Create the ApplicationRecrutementStatus with an existing ID
        applicationRecrutementStatus.setId(1L);
        ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO = applicationRecrutementStatusMapper.toDto(applicationRecrutementStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApplicationRecrutementStatusMockMvc.perform(post("/api/application-recrutement-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationRecrutementStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApplicationRecrutementStatus in the database
        List<ApplicationRecrutementStatus> applicationRecrutementStatusList = applicationRecrutementStatusRepository.findAll();
        assertThat(applicationRecrutementStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllApplicationRecrutementStatuses() throws Exception {
        // Initialize the database
        applicationRecrutementStatusRepository.saveAndFlush(applicationRecrutementStatus);

        // Get all the applicationRecrutementStatusList
        restApplicationRecrutementStatusMockMvc.perform(get("/api/application-recrutement-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(applicationRecrutementStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].addedAt").value(hasItem(DEFAULT_ADDED_AT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getApplicationRecrutementStatus() throws Exception {
        // Initialize the database
        applicationRecrutementStatusRepository.saveAndFlush(applicationRecrutementStatus);

        // Get the applicationRecrutementStatus
        restApplicationRecrutementStatusMockMvc.perform(get("/api/application-recrutement-statuses/{id}", applicationRecrutementStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(applicationRecrutementStatus.getId().intValue()))
            .andExpect(jsonPath("$.addedAt").value(DEFAULT_ADDED_AT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingApplicationRecrutementStatus() throws Exception {
        // Get the applicationRecrutementStatus
        restApplicationRecrutementStatusMockMvc.perform(get("/api/application-recrutement-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApplicationRecrutementStatus() throws Exception {
        // Initialize the database
        applicationRecrutementStatusRepository.saveAndFlush(applicationRecrutementStatus);

        int databaseSizeBeforeUpdate = applicationRecrutementStatusRepository.findAll().size();

        // Update the applicationRecrutementStatus
        ApplicationRecrutementStatus updatedApplicationRecrutementStatus = applicationRecrutementStatusRepository.findById(applicationRecrutementStatus.getId()).get();
        // Disconnect from session so that the updates on updatedApplicationRecrutementStatus are not directly saved in db
        em.detach(updatedApplicationRecrutementStatus);
        updatedApplicationRecrutementStatus
            .addedAt(UPDATED_ADDED_AT)
            .status(UPDATED_STATUS);
        ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO = applicationRecrutementStatusMapper.toDto(updatedApplicationRecrutementStatus);

        restApplicationRecrutementStatusMockMvc.perform(put("/api/application-recrutement-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationRecrutementStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ApplicationRecrutementStatus in the database
        List<ApplicationRecrutementStatus> applicationRecrutementStatusList = applicationRecrutementStatusRepository.findAll();
        assertThat(applicationRecrutementStatusList).hasSize(databaseSizeBeforeUpdate);
        ApplicationRecrutementStatus testApplicationRecrutementStatus = applicationRecrutementStatusList.get(applicationRecrutementStatusList.size() - 1);
        assertThat(testApplicationRecrutementStatus.getAddedAt()).isEqualTo(UPDATED_ADDED_AT);
        assertThat(testApplicationRecrutementStatus.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingApplicationRecrutementStatus() throws Exception {
        int databaseSizeBeforeUpdate = applicationRecrutementStatusRepository.findAll().size();

        // Create the ApplicationRecrutementStatus
        ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO = applicationRecrutementStatusMapper.toDto(applicationRecrutementStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApplicationRecrutementStatusMockMvc.perform(put("/api/application-recrutement-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationRecrutementStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApplicationRecrutementStatus in the database
        List<ApplicationRecrutementStatus> applicationRecrutementStatusList = applicationRecrutementStatusRepository.findAll();
        assertThat(applicationRecrutementStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApplicationRecrutementStatus() throws Exception {
        // Initialize the database
        applicationRecrutementStatusRepository.saveAndFlush(applicationRecrutementStatus);

        int databaseSizeBeforeDelete = applicationRecrutementStatusRepository.findAll().size();

        // Delete the applicationRecrutementStatus
        restApplicationRecrutementStatusMockMvc.perform(delete("/api/application-recrutement-statuses/{id}", applicationRecrutementStatus.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApplicationRecrutementStatus> applicationRecrutementStatusList = applicationRecrutementStatusRepository.findAll();
        assertThat(applicationRecrutementStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
