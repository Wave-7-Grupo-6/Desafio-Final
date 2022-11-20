package br.com.dh.meli.desafiofinal.service;

import java.math.BigDecimal;

public interface ICurrencyApi {
    BigDecimal getValue(String currency);
}
