package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BatchServiceTest {

    @Mock
    private BatchRepository repository;

    @MockBean
    private ISection sectionService;

    @MockBean
    private ISeller sellerService;

    private IBatch service;

    @BeforeEach
    void setUp() {
        service = new BatchService(repository, sellerService, sectionService);
    }

    @Test
    void save_returnBatch_whenValid() {
        Batch batch = getBatch();
        when(repository.save(batch)).thenReturn(batch);

        Batch savedBatch = service.save(batch);

        assertThat(savedBatch).isNotNull();
        assertThat(savedBatch.getBatchNumber()).isEqualTo(batch.getBatchNumber());
        assertThat(savedBatch.getDueDate()).isEqualTo(batch.getDueDate());
        assertThat(savedBatch.getManufacturingDate()).isEqualTo(batch.getManufacturingDate());
    }
}
