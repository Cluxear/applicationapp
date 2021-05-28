package com.tw.application.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationRecrutementStatusMapperTest {

    private ApplicationRecrutementStatusMapper applicationRecrutementStatusMapper;

    @BeforeEach
    public void setUp() {
        applicationRecrutementStatusMapper = new ApplicationRecrutementStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(applicationRecrutementStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(applicationRecrutementStatusMapper.fromId(null)).isNull();
    }
}
