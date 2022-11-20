package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.CurrencyApiDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.service.ICurrencyApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CurrencyApiService implements ICurrencyApi {
    @Override
    public BigDecimal getValue(String currency) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format("https://economia.awesomeapi.com.br/BRL-%s/?format=json", currency);
            CurrencyApiDTO[] currencyApiDTO = restTemplate.getForObject(url, CurrencyApiDTO[].class);
            if (currencyApiDTO == null) {
                throw new NotFoundException("Currency not found.");
            }
            return currencyApiDTO[0].getBid();
        } catch (HttpClientErrorException e) {
            throw new NotFoundException("Currency not found.");
        }
    }

    @Override
    public Announcement convertAnnouncementCurrency(Announcement announcement, BigDecimal currency) {
        announcement.getCartItems().forEach(cartItem -> {
            cartItem.setValue(cartItem.getValue().multiply(currency));
        });
        announcement.getSeller().getSections().forEach(section -> {
            section.getInboundOrders().forEach(inboundOrder -> {
                inboundOrder.getBatchs().forEach(batch -> {
                    batch.setPrice(batch.getPrice().multiply(currency));
                });
            });
        });
        return announcement;
    }
}
