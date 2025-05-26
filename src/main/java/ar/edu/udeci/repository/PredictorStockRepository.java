package ar.edu.udeci.repository;

import ar.edu.udeci.entity.PredictorStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictorStockRepository extends JpaRepository<PredictorStock, String> {
    List<PredictorStock> findByIdProductId(String productId);
}
