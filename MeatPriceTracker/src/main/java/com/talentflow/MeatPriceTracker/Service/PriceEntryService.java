package com.talentflow.MeatPriceTracker.Service;

import com.talentflow.MeatPriceTracker.Entity.PriceEntry;
import com.talentflow.MeatPriceTracker.Entity.Product;
import com.talentflow.MeatPriceTracker.Entity.Vendor;
import com.talentflow.MeatPriceTracker.Repository.PriceEntryRepo;
import com.talentflow.MeatPriceTracker.Repository.ProductRepo;
import com.talentflow.MeatPriceTracker.Repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public PriceEntry createPriceEntry(long productId, long vendorId, PriceEntry priceEntry){
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        Vendor vendor = vendorRepo.findById(vendorId).orElseThrow(() -> new RuntimeException("Vendor not found"));

        priceEntry.setProduct(product);
        priceEntry.setVendor(vendor);

        return priceEntryRepo.save(priceEntry);
    }

    public List<PriceEntry> getAllPriceEntry(){
        return priceEntryRepo.findAll();
    }

    public void deletePriceEntry(Long id){
        priceEntryRepo.deleteById(id);
    }

    public List<PriceEntry> getByProduct(long productId){
        return priceEntryRepo.findByProductId(productId);
    }

    public List<PriceEntry> getByVendor(long vendorId){
        return priceEntryRepo.findByVendorId(vendorId);
    }

}
