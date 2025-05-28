package ar.edu.udeci.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMaster {

    @Id
    @Column(name = "product_id", length = 10)
    private String productId;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "sku", length = 20)
    private String sku;

    @Column(name = "unit_of_measure", length = 10)
    private String unitOfMeasure;

    @Column(name = "cost", precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal salePrice;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "location", length = 10)
    private String location;

    @Column(name = "active")
    private Boolean active;

    // Relaciones bidireccionales (opcionales)

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<InventoryMovement> inventoryMovements;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private CurrentStock currentStock;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<PredictorStock> predictorStocks;
}