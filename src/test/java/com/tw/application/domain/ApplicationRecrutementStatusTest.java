package com.tw.application.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tw.application.web.rest.TestUtil;

public class ApplicationRecrutementStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApplicationRecrutementStatus.class);
        ApplicationRecrutementStatus applicationRecrutementStatus1 = new ApplicationRecrutementStatus();
        applicationRecrutementStatus1.setId(1L);
        ApplicationRecrutementStatus applicationRecrutementStatus2 = new ApplicationRecrutementStatus();
        applicationRecrutementStatus2.setId(applicationRecrutementStatus1.getId());
        assertThat(applicationRecrutementStatus1).isEqualTo(applicationRecrutementStatus2);
        applicationRecrutementStatus2.setId(2L);
        assertThat(applicationRecrutementStatus1).isNotEqualTo(applicationRecrutementStatus2);
        applicationRecrutementStatus1.setId(null);
        assertThat(applicationRecrutementStatus1).isNotEqualTo(applicationRecrutementStatus2);
    }
}
