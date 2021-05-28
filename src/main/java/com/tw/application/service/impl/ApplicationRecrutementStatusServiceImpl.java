package com.tw.application.service.impl;

import com.tw.application.service.ApplicationRecrutementStatusService;
import com.tw.application.domain.ApplicationRecrutementStatus;
import com.tw.application.repository.ApplicationRecrutementStatusRepository;
import com.tw.application.service.dto.ApplicationRecrutementStatusDTO;
import com.tw.application.service.mapper.ApplicationRecrutementStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ApplicationRecrutementStatus}.
 */
@Service
@Transactional
public class ApplicationRecrutementStatusServiceImpl implements ApplicationRecrutementStatusService {

    private final Logger log = LoggerFactory.getLogger(ApplicationRecrutementStatusServiceImpl.class);

    private final ApplicationRecrutementStatusRepository applicationRecrutementStatusRepository;

    private final ApplicationRecrutementStatusMapper applicationRecrutementStatusMapper;

    public ApplicationRecrutementStatusServiceImpl(ApplicationRecrutementStatusRepository applicationRecrutementStatusRepository, ApplicationRecrutementStatusMapper applicationRecrutementStatusMapper) {
        this.applicationRecrutementStatusRepository = applicationRecrutementStatusRepository;
        this.applicationRecrutementStatusMapper = applicationRecrutementStatusMapper;
    }

    @Override
    public ApplicationRecrutementStatusDTO save(ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO) {
        log.debug("Request to save ApplicationRecrutementStatus : {}", applicationRecrutementStatusDTO);
        ApplicationRecrutementStatus applicationRecrutementStatus = applicationRecrutementStatusMapper.toEntity(applicationRecrutementStatusDTO);
        applicationRecrutementStatus = applicationRecrutementStatusRepository.save(applicationRecrutementStatus);
        return applicationRecrutementStatusMapper.toDto(applicationRecrutementStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationRecrutementStatusDTO> findAll() {
        log.debug("Request to get all ApplicationRecrutementStatuses");
        return applicationRecrutementStatusRepository.findAll().stream()
            .map(applicationRecrutementStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationRecrutementStatusDTO> findOne(Long id) {
        log.debug("Request to get ApplicationRecrutementStatus : {}", id);
        return applicationRecrutementStatusRepository.findById(id)
            .map(applicationRecrutementStatusMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApplicationRecrutementStatus : {}", id);
        applicationRecrutementStatusRepository.deleteById(id);
    }
}
