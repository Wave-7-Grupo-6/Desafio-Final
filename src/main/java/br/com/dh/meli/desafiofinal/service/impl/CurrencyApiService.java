package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.CurrencyApiDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.service.ICurrencyApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CurrencyApiService implements ICurrencyApi {
    @Override
    public Float getCurrencyValue(String currency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://economia.awesomeapi.com.br/%s-BRL/?format=json", currency);
        CurrencyApiDTO[] currencyApiDTO = restTemplate.getForObject(url, CurrencyApiDTO[].class);
        if (currencyApiDTO == null || currency == null) {
            throw new NotFoundException("Currency not found.");
        }
        return currencyApiDTO[0].getBid();
    }
}
