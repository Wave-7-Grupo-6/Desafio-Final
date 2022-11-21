package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Announcement;

import java.math.BigDecimal;

public interface ICurrencyApi {
    BigDecimal getValue(String currency);
    Announcement convertAnnouncementCurrency(Announcement announcement, BigDecimal currency, String currencyName);
}
