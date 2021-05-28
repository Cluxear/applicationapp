package com.tw.application.service.mapper;


import com.tw.application.domain.*;
import com.tw.application.service.dto.RecrutementStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RecrutementStatus} and its DTO {@link RecrutementStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecrutementStatusMapper extends EntityMapper<RecrutementStatusDTO, RecrutementStatus> {



    default RecrutementStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        RecrutementStatus recrutementStatus = new RecrutementStatus();
        recrutementStatus.setId(id);
        return recrutementStatus;
    }
}
