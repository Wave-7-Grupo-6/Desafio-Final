package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Batch.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Batch {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long batchNumber;

    @Column(nullable = false)
    private Float currentTemperature;

    @Column(nullable = false)
    private int productQuantity;

    @Column(nullable = false)
    private LocalDate manufacturingDate;

    @Column(nullable = false)
    private LocalTime manufacturingTime;

    @Column(nullable = false)
    private Float volume;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "varchar(3) default 'BRL'")
    private String currency;

    @ManyToOne
    @JoinColumn(name = "annoucement_id")
    @JsonIgnoreProperties("batchs")
    @JsonBackReference
    private Announcement announcement;

    @ManyToOne
    @JoinColumn(name = "inboundOrder")
    @JsonIgnoreProperties("batchs")
    @JsonBackReference
    private InboundOrder inboundOrder;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties("batchs")
    @JsonBackReference
    private Section section;
}
