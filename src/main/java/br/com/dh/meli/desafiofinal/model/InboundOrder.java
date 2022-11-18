package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated inbound order ID")
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The inbound order date")
    private LocalDate orderDate;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The inbound order number")
    private Long orderNumber;

    @OneToMany(mappedBy = "inboundOrder", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("inboundOrder")
    @JsonManagedReference
    @ApiModelProperty(notes = "The inbound order batches")
    private List<Batch> batchs;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties("inboundOrders")
    @JsonBackReference
    @ApiModelProperty(notes = "The inbound order section")
    private Section section;

    @ManyToOne(optional = true)
    @JoinColumn(name = "discount_coupon_id")
    @JsonIgnoreProperties("inboundOrders")
    @JsonBackReference
    @ApiModelProperty(notes = "The inbound order discount coupon")
    private DiscountCoupon discountCoupon;

    public InboundOrder(InboundOrderDTO inboundOrderDTO, Section section) {
        this.id = inboundOrderDTO.getId();
        this.orderDate = inboundOrderDTO.getOrderDate();
        this.orderNumber = inboundOrderDTO.getOrderNumber();
        this.section = section;
        this.batchs = new ArrayList<>();
    }

    public InboundOrder(Long id, LocalDate orderDate, Long orderNumber, List<Batch> batchs, Section section) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
        this.batchs = batchs;
        this.section = section;
    }
}
