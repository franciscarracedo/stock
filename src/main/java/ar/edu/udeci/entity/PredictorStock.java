package ar.edu.udeci.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "predictor_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictorStock {

    @EmbeddedId
    private PredictorStockId id;

    @Column(name = "predicted_quantity")
    private Integer predictedQuantity;

    @Column(name = "predicted_sales")
    private BigDecimal predictedSales;

    @Column(name = "is_holiday")
    private Boolean isHoliday;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @JsonBackReference
    private ProductMaster product;

}

