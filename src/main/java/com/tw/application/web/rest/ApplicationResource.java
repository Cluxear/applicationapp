package com.tw.application.web.rest;

import com.tw.application.domain.User;
import com.tw.application.service.ApplicationService;
import com.tw.application.service.UserService;
import com.tw.application.service.dto.UserDTO;
import com.tw.application.web.rest.errors.BadRequestAlertException;
import com.tw.application.service.dto.ApplicationDTO;

import com.tw.application.web.rest.feignClients.DataappService;
import com.tw.application.web.rest.feignClients.UserappService;
import com.tw.application.web.rest.models.UserApplicationDTO;
import com.tw.application.web.rest.models.UserApplications;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link com.tw.application.domain.Application}.
 */
@RestController
@RequestMapping("/api")
public class ApplicationResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationResource.class);

    private static final String ENTITY_NAME = "applicationappApplication";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApplicationService applicationService;

    private final DataappService dataAppService;

    private final UserappService userService;


    public ApplicationResource(ApplicationService applicationService, DataappService dataAppService, UserappService userService) {
        this.applicationService = applicationService;
        this.dataAppService = dataAppService;
        this.userService = userService;
    }

    /**
     * {@code POST  /applications} : Create a new application.
     *
     * @param applicationDTO the applicationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new applicationDTO, or with status {@code 400 (Bad Request)} if the application has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/applications")
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationDTO applicationDTO) throws URISyntaxException {
        log.debug("REST request to save Application : {}", applicationDTO);
        if (applicationDTO.getId() != null) {
            throw new BadRequestAlertException("A new application cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApplicationDTO result = applicationService.save(applicationDTO);
        return ResponseEntity.created(new URI("/api/applications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /applications} : Updates an existing application.
     *
     * @param applicationDTO the applicationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated applicationDTO,
     * or with status {@code 400 (Bad Request)} if the applicationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the applicationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/applications")
    public ResponseEntity<ApplicationDTO> updateApplication(@RequestBody ApplicationDTO applicationDTO) throws URISyntaxException {
        log.debug("REST request to update Application : {}", applicationDTO);
        if (applicationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApplicationDTO result = applicationService.save(applicationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applicationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /applications} : get all the applications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of applications in body.
     */
    @GetMapping("/applications")
    public List<ApplicationDTO> getAllApplications() {
        log.debug("REST request to get all Applications");
        //
        // getting details about the
        List<ApplicationDTO> apps = applicationService.findAll();

        for(ApplicationDTO app : apps) {
            // get userId for app
           UserApplicationDTO userApp =  dataAppService.getUserApplicationByAppId(app.getId());
           UserDTO userDTO = userService.getCandidateFromId(userApp.getUserId()).getBody();
           if(userDTO != null) {
               app.setCandidateId(userDTO.getId());
               app.setFullName(userDTO.getFirstName() + " " + userDTO.getLastName());
               app.setEmail(userDTO.getEmail());
               app.setExperienceDurationName(userDTO.getExperienceDurationName());
           }


                   }
        return apps;
    }

    @GetMapping("/applications/jobpost/{id}")
    public List<ApplicationDTO> getApplicationsWithJobPostId(@PathVariable long id) {
        log.debug("REST request to get all Applications");
        List<UserApplicationDTO> userApplications = dataAppService.getUserApplicationsByJpId(id).getUserApplications();
     /*   userApplications = userApplications.stream()
            .filter(val -> val.getJobPostId().equals(id))
            .collect(Collectors.toList());*/
        List<ApplicationDTO> applications = new ArrayList<>();
        for( UserApplicationDTO userApplication: userApplications) {

            Optional<ApplicationDTO> application = applicationService.findOne(userApplication.getApplicationId());
            if(application.isPresent()) {
                UserDTO user = userService.getUserFromId(userApplication.getUserId()).getBody();
                log.debug("the user's id {}", user.getId());
                application.get().setCandidateId(userApplication.getUserId());
                if(user != null) {
                    application.get().setFullName(user.getFirstName() + " " + user.getLastName());
                    application.get().setEmail(user.getEmail());

                }
                application.ifPresent(applications::add);
            }


        }
        return applications;
    }

    @GetMapping("/applications/userId/{userId}")
    public List<ApplicationDTO> getAllUserApplications(@PathVariable String userId) {
        log.debug("REST request to get all Applications for user Id {}:", userId);

        //
      UserDTO user = userService.getUserFromLogin(userId).getBody();
        UserApplications userApplications = dataAppService.getListOfUserApplications(user.getId());
        List<ApplicationDTO> applications = new ArrayList<>();
        for( UserApplicationDTO userApplication: userApplications.getUserApplications()) {

            Optional<ApplicationDTO> application = applicationService.findOne(userApplication.getApplicationId());
            if(application.isPresent()) {
                applications.add(application.get());
                application.get().setJobpostId(userApplication.getJobPostId());
            }

        }


        return applications;
    }

    /**
     * {@code GET  /applications/:id} : get the "id" application.
     *
     * @param id the id of the applicationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the applicationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/applications/{id}")
    public ResponseEntity<ApplicationDTO> getApplication(@PathVariable Long id) {
        log.debug("REST request to get Application : {}", id);
        Optional<ApplicationDTO> applicationDTO = applicationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationDTO);
    }

    /**
     * {@code DELETE  /applications/:id} : delete the "id" application.
     *
     * @param id the id of the applicationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        log.debug("REST request to delete Application : {}", id);
        applicationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
