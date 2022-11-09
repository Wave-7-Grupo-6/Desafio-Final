package br.com.dh.meli.desafiofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {

    private Long id;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private Long sellerId;
}
