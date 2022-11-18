package br.com.dh.meli.desafiofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyApiDTO implements Serializable {
    private String code;
    private String codein;
    private String name;
    private Float high;
    private Float low;
    private Float varBid;
    private Float pctChange;
    private Float bid;
    private Float ask;
    private String timestamp;
    private String create_date;
}
