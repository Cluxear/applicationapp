package com.tw.application.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RecrutementStatusMapperTest {

    private RecrutementStatusMapper recrutementStatusMapper;

    @BeforeEach
    public void setUp() {
        recrutementStatusMapper = new RecrutementStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(recrutementStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(recrutementStatusMapper.fromId(null)).isNull();
    }
}
