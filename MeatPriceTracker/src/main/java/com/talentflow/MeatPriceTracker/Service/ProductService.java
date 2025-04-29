package com.talentflow.MeatPriceTracker.Service;

import com.talentflow.MeatPriceTracker.Entity.Product;
import com.talentflow.MeatPriceTracker.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product createProduct(Product product){
        return productRepo.save(product);
    }

    public Product updateProduct(long id, Product updateProduct){
       Product existingProduct = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

       existingProduct.setProductName(updateProduct.getProductName());
       existingProduct.setCategory(updateProduct.getCategory());
       existingProduct.setProductType(updateProduct.getProductType());
       existingProduct.setUpdatedAt(java.time.LocalDateTime.now());

       return productRepo.save(existingProduct);
    }

    public void deleteProduct(long id){
        productRepo.deleteById(id);
    }
}
