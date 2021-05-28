package com.tw.application.service.impl;

import com.tw.application.service.RecrutementStatusService;
import com.tw.application.domain.RecrutementStatus;
import com.tw.application.repository.RecrutementStatusRepository;
import com.tw.application.service.dto.RecrutementStatusDTO;
import com.tw.application.service.mapper.RecrutementStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link RecrutementStatus}.
 */
@Service
@Transactional
public class RecrutementStatusServiceImpl implements RecrutementStatusService {

    private final Logger log = LoggerFactory.getLogger(RecrutementStatusServiceImpl.class);

    private final RecrutementStatusRepository recrutementStatusRepository;

    private final RecrutementStatusMapper recrutementStatusMapper;

    public RecrutementStatusServiceImpl(RecrutementStatusRepository recrutementStatusRepository, RecrutementStatusMapper recrutementStatusMapper) {
        this.recrutementStatusRepository = recrutementStatusRepository;
        this.recrutementStatusMapper = recrutementStatusMapper;
    }

    @Override
    public RecrutementStatusDTO save(RecrutementStatusDTO recrutementStatusDTO) {
        log.debug("Request to save RecrutementStatus : {}", recrutementStatusDTO);
        RecrutementStatus recrutementStatus = recrutementStatusMapper.toEntity(recrutementStatusDTO);
        recrutementStatus = recrutementStatusRepository.save(recrutementStatus);
        return recrutementStatusMapper.toDto(recrutementStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecrutementStatusDTO> findAll() {
        log.debug("Request to get all RecrutementStatuses");
        return recrutementStatusRepository.findAll().stream()
            .map(recrutementStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RecrutementStatusDTO> findOne(Long id) {
        log.debug("Request to get RecrutementStatus : {}", id);
        return recrutementStatusRepository.findById(id)
            .map(recrutementStatusMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RecrutementStatus : {}", id);
        recrutementStatusRepository.deleteById(id);
    }
}
