package ar.edu.udeci.controller;

import ar.edu.udeci.entity.ProductMaster;
import ar.edu.udeci.repository.ProductMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductMasterRepository productRepository;

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        jdbcTemplate.execute("DELETE FROM predictor_stock");
        jdbcTemplate.execute("DELETE FROM inventory_movement");
        jdbcTemplate.execute("DELETE FROM current_stock");
        jdbcTemplate.execute("DELETE FROM product_master");

        ProductMaster product = new ProductMaster();
        product.setProductId("TEST001");
        product.setProductName("Producto Test");
        product.setSku("SKU-TEST-01");
        product.setUnitOfMeasure("Unidad");
        product.setCost(new BigDecimal(1800.0));
        product.setSalePrice(new BigDecimal(1500.0));
        product.setCategory("Electrónica");
        product.setLocation("A1");
        product.setActive(true);

        productRepository.save(product);
    }

    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetProductById() throws Exception {
        mockMvc.perform(get("/api/products/TEST001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Producto Test"));
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductMaster newProduct = new ProductMaster();
        newProduct.setProductId("TEST002");
        newProduct.setProductName("Nuevo Producto");
        newProduct.setSku("SKU-NEW-01");
        newProduct.setUnitOfMeasure("Unidad");
        newProduct.setCost(new BigDecimal(1800.0));
        newProduct.setSalePrice(new BigDecimal(1500.0));
        newProduct.setCategory("Informática");
        newProduct.setLocation("B2");
        newProduct.setActive(true);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Nuevo Producto"));
    }


    @Test
    @Transactional
    void testUpdateProduct() throws Exception {
        ProductMaster updated = productRepository.findById("TEST001").get();

        // Forzamos la carga de relaciones perezosas
        updated.getInventoryMovements().size();

        updated.setProductName("Producto Modificado");

        mockMvc.perform(put("/api/products/TEST001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Producto Modificado"));
    }


    @Test
    void testDeactivateProduct() throws Exception {
        mockMvc.perform(delete("/api/products/TEST001"))
                .andExpect(status().isNoContent());

        Optional<ProductMaster> product = productRepository.findById("TEST001");
        assertThat(product).isPresent();
        assertThat(product.get().getActive()).isFalse();
    }
}