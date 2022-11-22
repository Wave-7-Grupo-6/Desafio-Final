package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.SaleoffDTO;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.dh.meli.desafiofinal.service.impl.SaleoffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Comparator;
import java.util.List;

@WebMvcTest(SaleoffController.class)
public class SaleoffControllerTest {
    @Autowired
    private MockMvc mocker;
    @MockBean
    private SaleoffService saleoffService;

    @Test
    void getAll_returnSaleoffDTOList_whenSucess() throws Exception{
        List<SaleoffDTO> mockList = getSaleoffDTOList();

        when(saleoffService.getAll()).thenReturn(mockList);

        mocker.perform(
                        get("/api/v1/saleoff")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getAllBySmallestPrice_returnSmallestPriceFirst_whenSucess() throws Exception{
        List<SaleoffDTO> mockList = getSaleoffDTOList();

        when(saleoffService.getAllBySmallestPrice()).thenReturn(mockList);

        mocker.perform(
                        get("/api/v1/saleoff/smallest_price")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].daysToExpire").value(mockList.get(0).getDaysToExpire()))
                .andExpect(jsonPath("$[0].discountPercentage").value(mockList.get(0).getDiscountPercentage()))
                .andExpect(jsonPath("$[0].original_price").value(mockList.get(0).getOriginal_price()))
                .andExpect(jsonPath("$[0].sale_price").value(mockList.get(0).getSale_price()))
                .andExpect(jsonPath("$[0].price_difference").value(mockList.get(0).getPrice_difference()));
    }

    @Test
    void getAllByBiggerDiscountPercentage_returnBiggerDiscountFirst_whenSucess() throws Exception{
        List<SaleoffDTO> mockList = getSaleoffDTOList();
        mockList.sort(Comparator.comparing(SaleoffDTO::getDiscountPercentage).reversed());

        when(saleoffService.getAllByBiggerDiscountPercentage()).thenReturn(mockList);

        mocker.perform(
                        get("/api/v1/saleoff/bigger_discount")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].daysToExpire").value(mockList.get(0).getDaysToExpire()))
                .andExpect(jsonPath("$[0].discountPercentage").value(mockList.get(0).getDiscountPercentage()))
                .andExpect(jsonPath("$[0].original_price").value(mockList.get(0).getOriginal_price()))
                .andExpect(jsonPath("$[0].sale_price").value(mockList.get(0).getSale_price()))
                .andExpect(jsonPath("$[0].price_difference").value(mockList.get(0).getPrice_difference()));
    }

    @Test
    void getAllByFewDaysToExpire_returnCloserToExpireFirst_whenSucess() throws Exception{
        List<SaleoffDTO> mockList = getSaleoffDTOList();
        mockList.sort(Comparator.comparing(SaleoffDTO::getDaysToExpire));

        when(saleoffService.getAllByFewDaysToExpire()).thenReturn(mockList);

        mocker.perform(
                        get("/api/v1/saleoff/closer_to_expire")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].daysToExpire").value(mockList.get(0).getDaysToExpire()))
                .andExpect(jsonPath("$[0].discountPercentage").value(mockList.get(0).getDiscountPercentage()))
                .andExpect(jsonPath("$[0].original_price").value(mockList.get(0).getOriginal_price()))
                .andExpect(jsonPath("$[0].sale_price").value(mockList.get(0).getSale_price()))
                .andExpect(jsonPath("$[0].price_difference").value(mockList.get(0).getPrice_difference()));
    }

    @Test
    void getAllByBiggerPriceDifference_returnBiggerPriceDifferenceFirst_whenSucess() throws Exception{
        List<SaleoffDTO> mockList = getSaleoffDTOList();
        mockList.sort(Comparator.comparing(SaleoffDTO::getPrice_difference).reversed());


        when(saleoffService.getAllByBiggerPriceDifference()).thenReturn(mockList);

        mocker.perform(
                        get("/api/v1/saleoff/bigger_price_difference")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].daysToExpire").value(mockList.get(0).getDaysToExpire()))
                .andExpect(jsonPath("$[0].discountPercentage").value(mockList.get(0).getDiscountPercentage()))
                .andExpect(jsonPath("$[0].original_price").value(mockList.get(0).getOriginal_price()))
                .andExpect(jsonPath("$[0].sale_price").value(mockList.get(0).getSale_price()))
                .andExpect(jsonPath("$[0].price_difference").value(mockList.get(0).getPrice_difference()));
    }
}
