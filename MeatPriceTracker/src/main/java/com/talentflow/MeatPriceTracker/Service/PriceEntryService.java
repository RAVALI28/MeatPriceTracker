package com.talentflow.MeatPriceTracker.Service;

import com.talentflow.MeatPriceTracker.Entity.PriceEntry;
import com.talentflow.MeatPriceTracker.Entity.Product;
import com.talentflow.MeatPriceTracker.Entity.User;
import com.talentflow.MeatPriceTracker.Entity.Vendor;
import com.talentflow.MeatPriceTracker.Repository.PriceEntryRepo;
import com.talentflow.MeatPriceTracker.Repository.ProductRepo;
import com.talentflow.MeatPriceTracker.Repository.UserRepo;
import com.talentflow.MeatPriceTracker.Repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PriceEntryService {

    @Autowired
    private PriceEntryRepo priceEntryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private VendorRepo vendorRepo;

    @Autowired
    private HistoryLogService historyLogService;

    @Autowired
    private UserRepo userRepo;

    public PriceEntry createPriceEntry(long productId, long vendorId, PriceEntry priceEntry){
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        Vendor vendor = vendorRepo.findById(vendorId).orElseThrow(() -> new RuntimeException("Vendor not found"));

        priceEntry.setProduct(product);
        priceEntry.setVendor(vendor);

        PriceEntry createdPriceEntry = priceEntryRepo.save(priceEntry);

        // 📝 Log the deletion
        String detail = "Deleted price entry for product: " + createdPriceEntry.getProduct().getProductName() +
                ", vendor: " + createdPriceEntry.getVendor().getVendorName() +
                ", price: $" + createdPriceEntry.getPrice();

        //Track who is doing anything here,
        historyLogService.log(getCurrentUserId(), "CREATE", "PRICE", detail);

        return createdPriceEntry;

    }


    public List<PriceEntry> getAllPriceEntry(){
        return priceEntryRepo.findAll();
    }



    public void deletePriceEntry(Long id){
        PriceEntry entry = priceEntryRepo.findById(id).orElseThrow(() -> new RuntimeException("Price not found"));

        priceEntryRepo.deleteById(id);

        // 📝 Log the deletion
        String detail = "Deleted price entry for product: " + entry.getProduct().getProductName() +
                ", vendor: " + entry.getVendor().getVendorName() +
                ", price: $" + entry.getPrice();

        //Track who deleted it
        historyLogService.log(getCurrentUserId(), "DELETE", "PRICE", detail);

    }



    public List<PriceEntry> getByProduct(long productId){
        return priceEntryRepo.findByProductId(productId);
    }

    public List<PriceEntry> getByVendor(long vendorId){
        return priceEntryRepo.findByVendorId(vendorId);
    }

    private long getCurrentUserId() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();

    }
}
