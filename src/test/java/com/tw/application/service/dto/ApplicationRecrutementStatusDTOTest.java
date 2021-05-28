package com.tw.application.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tw.application.web.rest.TestUtil;

public class ApplicationRecrutementStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApplicationRecrutementStatusDTO.class);
        ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO1 = new ApplicationRecrutementStatusDTO();
        applicationRecrutementStatusDTO1.setId(1L);
        ApplicationRecrutementStatusDTO applicationRecrutementStatusDTO2 = new ApplicationRecrutementStatusDTO();
        assertThat(applicationRecrutementStatusDTO1).isNotEqualTo(applicationRecrutementStatusDTO2);
        applicationRecrutementStatusDTO2.setId(applicationRecrutementStatusDTO1.getId());
        assertThat(applicationRecrutementStatusDTO1).isEqualTo(applicationRecrutementStatusDTO2);
        applicationRecrutementStatusDTO2.setId(2L);
        assertThat(applicationRecrutementStatusDTO1).isNotEqualTo(applicationRecrutementStatusDTO2);
        applicationRecrutementStatusDTO1.setId(null);
        assertThat(applicationRecrutementStatusDTO1).isNotEqualTo(applicationRecrutementStatusDTO2);
    }
}
