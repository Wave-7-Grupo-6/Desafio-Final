package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.exceptions.OutOfStockException;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getBatch;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BatchServiceTest {

    @Mock
    private BatchRepository repository;

    private IBatch service;

    @BeforeEach
    void setUp() {
        service = new BatchService(repository);
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
    void updateStock_returnBatch_whenValid() {
        Batch batch = getBatch();
        when(repository.findById(anyLong())).thenReturn(Optional.of(batch));
        when(repository.save(any(Batch.class))).thenReturn(batch);

        Batch updatedBatch = service.updateStock(batch.getBatchNumber(), batch.getAnnouncement().getId(), 5);

        assertThat(updatedBatch).isNotNull();
        assertThat(updatedBatch.getBatchNumber()).isEqualTo(batch.getBatchNumber());
        assertThat(updatedBatch.getProductQuantity()).isEqualTo(batch.getProductQuantity());
    }

    @Test
    void updateStock_throwsNotFoundException_whenBatchNotValid() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> service.updateStock(1L, 1L, 5));
    }

    @Test
    void updateStock_throwsOutOfStockException_whenBatchHasNotEnoughtStock() {
        Batch batch = getBatch();
        when(repository.findById(anyLong())).thenReturn(Optional.of(batch));

        assertThrows(OutOfStockException.class, ()-> service.updateStock(batch.getBatchNumber(), batch.getAnnouncement().getId(), 11));
    }

    @Test
    void findById_throwsNotFoundException_whenBatchDoesntExist() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> service.findById(1L));
    }


    @Test
    void findById_returnBatch_whenBatchExists() {
        Batch batch = getBatch();
        when(repository.findById(anyLong())).thenReturn(Optional.of(batch));

        Batch foundBatch = service.findById(batch.getBatchNumber());

        assertThat(foundBatch).isNotNull();
        assertThat(foundBatch.getBatchNumber()).isEqualTo(batch.getBatchNumber());
        assertThat(foundBatch.getProductQuantity()).isEqualTo(batch.getProductQuantity());
        assertThat(foundBatch.getDueDate()).isEqualTo(batch.getDueDate());
        assertThat(foundBatch.getManufacturingDate()).isEqualTo(batch.getManufacturingDate());
    }
}
