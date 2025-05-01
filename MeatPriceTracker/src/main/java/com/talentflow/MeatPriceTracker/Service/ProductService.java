package com.talentflow.MeatPriceTracker.Service;

import com.talentflow.MeatPriceTracker.Entity.Product;
import com.talentflow.MeatPriceTracker.Entity.User;
import com.talentflow.MeatPriceTracker.Repository.ProductRepo;
import com.talentflow.MeatPriceTracker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private HistoryLogService historyLogService;

    @Autowired
    private UserRepo userRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product createProduct(Product product){

        Product createdProduct = productRepo.save(product);

        //Track who is doing anything here
        historyLogService.log(getCurrentUserId(), "CREATE", "PRODUCT", "Created Product Chicken : " +createdProduct.getProductName());

        return createdProduct;

    }

    public Product updateProduct(long id, Product updateProduct){
       Product existingProduct = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

       existingProduct.setProductName(updateProduct.getProductName());
       existingProduct.setCategory(updateProduct.getCategory());
       existingProduct.setProductType(updateProduct.getProductType());
       existingProduct.setUpdatedAt(java.time.LocalDateTime.now());

       Product updatedNewProduct = productRepo.save(existingProduct);


        //Track who is doing anything here
        historyLogService.log(getCurrentUserId(), "UPDATE", "PRODUCT", "Updated Product Chicken : " +updatedNewProduct.getProductName());

        return updatedNewProduct;
    }

    public void deleteProduct(long id){

        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        productRepo.deleteById(id);

        //Track who is doing anything here
        historyLogService.log(getCurrentUserId(), "DELETE", "PRODUCT", "Deleted Product Chicken");
    }

    private long getCurrentUserId() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();

    }
}
