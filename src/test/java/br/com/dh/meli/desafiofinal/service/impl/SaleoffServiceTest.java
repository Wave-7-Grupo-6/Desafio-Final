package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.SaleoffDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaleoffServiceTest {

    @Mock
    private BatchRepository batchRepository;

    private SaleoffService saleoffService;

    @BeforeEach
    void setUp(){ saleoffService = new SaleoffService(batchRepository); }

    @Test
    void getAll_returnSaleoffDTOList_whenSucess(){
        List<Batch> batchList = getBatchList();

        when(batchRepository.findAll()).thenReturn(batchList);

        List<SaleoffDTO> saleOffList = saleoffService.getAll();


        assertThat(saleOffList).isNotNull();
        assertThat(batchList.get(0).getPrice()).isEqualTo(saleOffList.get(0).getOriginal_price());
        assertThat(batchList.get(0).getAnnouncement().getDescription()).isEqualTo(saleOffList.get(0).getProductName());
    }

    @Test
    void getAllBySmallestPrice_returnSmallestPriceFirst_whenSucess(){
        List<Batch> batchList = getBatchList();

        when(batchRepository.findAll()).thenReturn(batchList);

        List<SaleoffDTO> saleOffList = saleoffService.getAllBySmallestPrice();


        assertThat(saleOffList).isNotNull();
        assertThat(batchList.get(0).getPrice()).isEqualTo(saleOffList.get(0).getOriginal_price());
        assertThat(batchList.get(0).getAnnouncement().getDescription()).isEqualTo(saleOffList.get(0).getProductName());
    }

    @Test
    void getAllByBiggerDiscountPercentage_returnBiggerDiscountFirst_whenSucess(){
        List<Batch> batchList = getBatchList();

        when(batchRepository.findAll()).thenReturn(batchList);

        List<SaleoffDTO> saleOffList = saleoffService.getAllByBiggerDiscountPercentage();


        assertThat(saleOffList).isNotNull();
        assertThat(batchList.get(1).getPrice()).isEqualTo(saleOffList.get(0).getOriginal_price());
        assertThat(batchList.get(1).getAnnouncement().getDescription()).isEqualTo(saleOffList.get(0).getProductName());
    }

    @Test
    void getAllByFewDaysToExpire_returnCloserToExpireFirst_whenSucess(){
        List<Batch> batchList = getBatchList();

        when(batchRepository.findAll()).thenReturn(batchList);

        List<SaleoffDTO> saleOffList = saleoffService.getAllByFewDaysToExpire();


        assertThat(saleOffList).isNotNull();
        assertThat(batchList.get(1).getPrice()).isEqualTo(saleOffList.get(0).getOriginal_price());
        assertThat(batchList.get(1).getAnnouncement().getDescription()).isEqualTo(saleOffList.get(0).getProductName());
    }

    @Test
    void getAllByBiggerPriceDifference_returnBiggerPriceDifferenceFirst_whenSucess(){
        List<Batch> batchList = getBatchList();

        when(batchRepository.findAll()).thenReturn(batchList);

        List<SaleoffDTO> saleOffList = saleoffService.getAllByBiggerPriceDifference();


        assertThat(saleOffList).isNotNull();
        assertThat(batchList.get(1).getPrice()).isEqualTo(saleOffList.get(0).getOriginal_price());
        assertThat(batchList.get(1).getAnnouncement().getDescription()).isEqualTo(saleOffList.get(0).getProductName());
    }
}
