package ar.edu.udeci.service;

import ar.edu.udeci.entity.ProductMaster;
import ar.edu.udeci.repository.ProductMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductMasterRepository productRepository;

    public List<ProductMaster> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductMaster> findById(String id) {
        return productRepository.findById(id);
    }

    public ProductMaster save(ProductMaster product) {
        product.setActive(true); // por defecto activo
        return productRepository.save(product);
    }

    public Optional<ProductMaster> update(String id, ProductMaster updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setProductName(updatedProduct.getProductName());
            product.setSku(updatedProduct.getSku());
            product.setUnitOfMeasure(updatedProduct.getUnitOfMeasure());
            product.setCost(updatedProduct.getCost());
            product.setSalePrice(updatedProduct.getSalePrice());
            product.setCategory(updatedProduct.getCategory());
            product.setLocation(updatedProduct.getLocation());
            product.setActive(updatedProduct.getActive());
            return productRepository.save(product);
        });
    }

    public boolean deactivate(String id) {
        return productRepository.findById(id).map(product -> {
            product.setActive(false);
            productRepository.save(product);
            return true;
        }).orElse(false);
    }
}