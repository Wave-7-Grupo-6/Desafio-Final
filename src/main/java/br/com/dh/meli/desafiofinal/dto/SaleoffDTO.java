package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleoffDTO {
    private String productName;
    private Long daysToExpire;
    private int discountPercentage;

    private BigDecimal original_price;
    private BigDecimal sale_price;
    private BigDecimal price_difference;

    public SaleoffDTO(Batch batch) {
        this.daysToExpire = LocalDate.now().until(batch.getDueDate(), ChronoUnit.DAYS);
        this.productName = batch.getAnnouncement().getDescription();
        this.original_price = batch.getPrice();

        if(daysToExpire >= 14 && daysToExpire < 21){
            this.sale_price = original_price.multiply(new BigDecimal("0.9"));
            this.discountPercentage = 10;
            this.price_difference = this.original_price.subtract(this.sale_price);
        }

        if(daysToExpire < 14 && daysToExpire >= 7){
            this.sale_price = original_price.multiply(new BigDecimal("0.75"));
            this.discountPercentage = 25;
            this.price_difference = this.original_price.subtract(this.sale_price);
        }

        if(daysToExpire < 7 && daysToExpire > 0){
            this.sale_price = original_price.multiply(new BigDecimal("0.5"));
            this.discountPercentage = 50;
            this.price_difference = this.original_price.subtract(this.sale_price);
        }


    }
}
