package ar.edu.udeci.controller;

import ar.edu.udeci.entity.ProductMaster;
import ar.edu.udeci.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Listar todos
    @GetMapping
    public List<ProductMaster> getAllProducts() {
        return productService.findAll();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<ProductMaster> getProductById(@PathVariable String id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo producto
    @PostMapping
    public ResponseEntity<ProductMaster> createProduct(@RequestBody ProductMaster product) {
        ProductMaster createdProduct = productService.save(product);
        return ResponseEntity.ok(createdProduct);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductMaster> updateProduct(@PathVariable String id, @RequestBody ProductMaster product) {
        return productService.update(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Desactivar producto (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateProduct(@PathVariable String id) {
        boolean deactivated = productService.deactivate(id);
        if (deactivated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}