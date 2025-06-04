package ar.edu.udeci.service;

import ar.edu.udeci.entity.ProductMaster;
import ar.edu.udeci.repository.ProductMasterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductMasterRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testFindAll() {
        List<ProductMaster> mockList = List.of(new ProductMaster(), new ProductMaster());
        when(productRepository.findAll()).thenReturn(mockList);

        List<ProductMaster> result = productService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        ProductMaster product = new ProductMaster();
        product.setProductId("123");

        when(productRepository.findById("123")).thenReturn(Optional.of(product));

        Optional<ProductMaster> result = productService.findById("123");

        assertTrue(result.isPresent());
        assertEquals("123", result.get().getProductId());
    }

    @Test
    void testSave() {
        ProductMaster product = new ProductMaster();
        product.setProductId("123");

        when(productRepository.save(any())).thenReturn(product);

        ProductMaster saved = productService.save(product);

        assertTrue(saved.getActive());
        verify(productRepository).save(product);
    }

    @Test
    void testUpdate() {
        ProductMaster existing = new ProductMaster();
        existing.setProductId("123");

        ProductMaster updated = new ProductMaster();
        updated.setProductName("New");

        when(productRepository.findById("123")).thenReturn(Optional.of(existing));
        when(productRepository.save(any())).thenReturn(existing);

        Optional<ProductMaster> result = productService.update("123", updated);

        assertTrue(result.isPresent());
        assertEquals("New", result.get().getProductName());
    }

    @Test
    void testDeactivate() {
        ProductMaster existing = new ProductMaster();
        existing.setProductId("123");
        existing.setActive(true);

        when(productRepository.findById("123")).thenReturn(Optional.of(existing));

        boolean result = productService.deactivate("123");

        assertTrue(result);
        assertFalse(existing.getActive());
    }
}
