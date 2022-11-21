package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.CurrencyApiDTO;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CurrencyApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private CurrencyApiService currencyApiService;

    @BeforeEach
    void setup() {
        currencyApiService = new CurrencyApiService();
    }

    @Test
    void getValue_returnBigDecimal_whenCurrencyExists() {
        when(restTemplate.getForObject("https://economia.awesomeapi.com.br/BRL-USD/?format=json", CurrencyApiDTO[].class))
                .thenReturn(List.of(getCurrencyApiDTO()).toArray(new CurrencyApiDTO[0]));

        BigDecimal currency = currencyApiService.getValue("USD");

        assertThat(currency).isNotNull();
    }

    @Test
    void convertAnnouncementCurrency_returnAnnouncement_whenSuccess() {
        Announcement announcement = getAnnouncement();

        Announcement announcementConverted = currencyApiService.convertAnnouncementCurrency(announcement, BigDecimal.ONE, "USD");

        assertThat(announcementConverted).isNotNull();
    }

}
