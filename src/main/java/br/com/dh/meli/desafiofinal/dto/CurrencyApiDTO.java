package br.com.dh.meli.desafiofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyApiDTO implements Serializable {
    private String code;
    private String codein;
    private String name;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal varBid;
    private BigDecimal pctChange;
    private BigDecimal bid;
    private BigDecimal ask;
    private String timestamp;
    private String create_date;
}
