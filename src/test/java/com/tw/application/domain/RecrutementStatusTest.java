package com.tw.application.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tw.application.web.rest.TestUtil;

public class RecrutementStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecrutementStatus.class);
        RecrutementStatus recrutementStatus1 = new RecrutementStatus();
        recrutementStatus1.setId(1L);
        RecrutementStatus recrutementStatus2 = new RecrutementStatus();
        recrutementStatus2.setId(recrutementStatus1.getId());
        assertThat(recrutementStatus1).isEqualTo(recrutementStatus2);
        recrutementStatus2.setId(2L);
        assertThat(recrutementStatus1).isNotEqualTo(recrutementStatus2);
        recrutementStatus1.setId(null);
        assertThat(recrutementStatus1).isNotEqualTo(recrutementStatus2);
    }
}
