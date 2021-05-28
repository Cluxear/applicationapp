package com.tw.application.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tw.application.web.rest.TestUtil;

public class RecrutementStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecrutementStatusDTO.class);
        RecrutementStatusDTO recrutementStatusDTO1 = new RecrutementStatusDTO();
        recrutementStatusDTO1.setId(1L);
        RecrutementStatusDTO recrutementStatusDTO2 = new RecrutementStatusDTO();
        assertThat(recrutementStatusDTO1).isNotEqualTo(recrutementStatusDTO2);
        recrutementStatusDTO2.setId(recrutementStatusDTO1.getId());
        assertThat(recrutementStatusDTO1).isEqualTo(recrutementStatusDTO2);
        recrutementStatusDTO2.setId(2L);
        assertThat(recrutementStatusDTO1).isNotEqualTo(recrutementStatusDTO2);
        recrutementStatusDTO1.setId(null);
        assertThat(recrutementStatusDTO1).isNotEqualTo(recrutementStatusDTO2);
    }
}
