package ar.edu.udeci.controller;

import ar.edu.udeci.service.StockPredictorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/predictor")
public class StockPredictorController {

    @Autowired
    private StockPredictorService stockPredictorService;

    @GetMapping("/predict/{productId}")
    public ResponseEntity<Integer> predictDemand(@PathVariable String productId) {
        int prediction = stockPredictorService.predictStockDemand(productId);
        return ResponseEntity.ok(prediction);
    }
}

