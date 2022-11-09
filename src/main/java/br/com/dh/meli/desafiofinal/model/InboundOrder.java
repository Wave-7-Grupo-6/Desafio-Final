package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false, unique = true)
    private Long orderNumber;

    @OneToMany(mappedBy = "inboundOrder")
    @JsonIgnoreProperties("inboundOrder")
    @JsonManagedReference
    private List<Batch> batchs;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties("inboundOrders")
    @JsonBackReference
    private Section section;


    public InboundOrder(InboundOrderDTO inboundOrderDTO, Section section, Annoucement  annoucement) {
        this.orderDate = inboundOrderDTO.getOrderDate();
        this.orderNumber = inboundOrderDTO.getOrderNumber();
        this.section = section;
        this.batchs = inboundOrderDTO.getBatchStockDTOList().stream().map(batchDTO->batchDTO.createBatch(annoucement,this)).collect(Collectors.toList());
    }
}
