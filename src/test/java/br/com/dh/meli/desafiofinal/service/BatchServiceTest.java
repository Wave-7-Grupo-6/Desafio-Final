package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchDTO;
import br.com.dh.meli.desafiofinal.dto.SellerDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BatchServiceTest {

    @Mock
    private BatchRepository repository;

    @Mock
    private ISection sectionService;

    @Mock
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

    @Test
    void findByDueDateIsBefore_whenSuccess(){
        Batch batch = getBatch();
        when(repository.findByDueDateIsBefore(any())).thenReturn(Collections.singletonList(batch));
        when(sectionService.findById(anyLong())).thenReturn(getSection());
        when(sellerService.findById(anyLong())).thenReturn(getSeller());

        List<BatchDTO> batches = service.findByDueDateIsBefore(10, batch.getSection().getId(), batch.getAnnouncement().getSeller().getId());

        assertThat(batches).isNotNull();
        assertThat(batches).hasSize(1);
        assertThat(batches.get(0).getDueDate()).isEqualTo(batch.getDueDate());
    }
}
