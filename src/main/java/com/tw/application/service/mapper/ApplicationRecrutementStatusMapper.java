package com.tw.application.service.mapper;


import com.tw.application.domain.*;
import com.tw.application.service.dto.ApplicationRecrutementStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApplicationRecrutementStatus} and its DTO {@link ApplicationRecrutementStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {ApplicationMapper.class})
public interface ApplicationRecrutementStatusMapper extends EntityMapper<ApplicationRecrutementStatusDTO, ApplicationRecrutementStatus> {

    @Mapping(source = "application.id", target = "applicationId")
    ApplicationRecrutementStatusDTO toDto(ApplicationRecrutementStatus applicationRecrutementStatus);

    @Mapping(source = "applicationId", target = "application")
    ApplicationRecrutementStatus toEntity(ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO);

    default ApplicationRecrutementStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApplicationRecrutementStatus applicationRecrutementStatus = new ApplicationRecrutementStatus();
        applicationRecrutementStatus.setId(id);
        return applicationRecrutementStatus;
    }
}
