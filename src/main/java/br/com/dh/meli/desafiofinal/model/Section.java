package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float volumeMax;

    @Transient
    private Float volumeOccupied;

    @Column(nullable = false)
    private Float temperature;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("sections")
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("sections")
    @JsonBackReference
    private Warehouse warehouse;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    @JsonManagedReference
    private List<InboundOrder> inboundOrders;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties("sections")
    @JsonBackReference
    private Seller seller;

    public Section(Long id, String name, Float volumeMax, Float temperature, Category category, Warehouse warehouse, Seller seller) {
        this.id = id;
        this.name = name;
        this.volumeMax = volumeMax;
        this.volumeOccupied = 0F;
        this.temperature = temperature;
        this.category = category;
        this.warehouse = warehouse;
        this.seller = seller;

        calculateVolumeOccupied();
    }

    private void calculateVolumeOccupied(){
        this.volumeOccupied = 0f;
        if(inboundOrders != null){
            for(InboundOrder inboundOrder : inboundOrders){
                for(Batch batch : inboundOrder.getBatchs()){
                    this.volumeOccupied += batch.getVolume();
                }
            }
        }
    }
}