package ar.edu.udeci.service;

import ar.edu.udeci.entity.PredictorStock;
import ar.edu.udeci.repository.PredictorStockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockPredictorServiceTest {

    @Mock
    private PredictorStockRepository predictorRepo;

    @InjectMocks
    private StockPredictorService predictorService;

    @Test
    void testPredictStockDemand_WithData() {
        PredictorStock p1 = new PredictorStock();
        p1.setPredictedQuantity(10);

        PredictorStock p2 = new PredictorStock();
        p2.setPredictedQuantity(20);

        PredictorStock p3 = new PredictorStock();
        p3.setPredictedQuantity(30);

        List<PredictorStock> data = List.of(p1, p2, p3);

        when(predictorRepo.findByIdProductId("123")).thenReturn(data);

        int predicted = predictorService.predictStockDemand("123");

        assertEquals(24, predicted);
    }


    @Test
    void testPredictStockDemand_NoData() {
        when(predictorRepo.findByIdProductId("123")).thenReturn(List.of());

        int predicted = predictorService.predictStockDemand("123");

        assertEquals(0, predicted);
    }
}

