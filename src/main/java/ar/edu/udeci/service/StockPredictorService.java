package ar.edu.udeci.service;

import ar.edu.udeci.entity.PredictorStock;
import ar.edu.udeci.repository.PredictorStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockPredictorService {

    @Autowired
    private PredictorStockRepository predictorRepo;

    public int predictStockDemand(String productId) {
        List<PredictorStock> historicalData = predictorRepo.findByIdProductId(productId);
        double avg = historicalData.stream()
                .mapToInt(PredictorStock::getPredictedQuantity)
                .average()
                .orElse(0);
        return (int) Math.ceil(avg * 1.2);
    }
}
