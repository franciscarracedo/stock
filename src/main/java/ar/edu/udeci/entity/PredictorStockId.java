package ar.edu.udeci.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class PredictorStockId implements Serializable {

    @Column(name = "prediction_date")
    private LocalDate predictionDate;

    @Column(name = "product_id", length = 10)
    private String productId;

    public PredictorStockId() {}

    public PredictorStockId(LocalDate predictionDate, String productId) {
        this.predictionDate = predictionDate;
        this.productId = productId;
    }

    // Getters, setters, equals, hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PredictorStockId)) return false;
        PredictorStockId that = (PredictorStockId) o;
        return Objects.equals(predictionDate, that.predictionDate) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predictionDate, productId);
    }
}
