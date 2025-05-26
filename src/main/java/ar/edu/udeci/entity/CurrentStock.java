package ar.edu.udeci.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "current_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentStock {

    @Id
    @Column(name = "product_id", length = 10)
    private String productId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private ProductMaster product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    @Column(name = "total_inventory_cost", precision = 10, scale = 2)
    private BigDecimal totalInventoryCost;

}
