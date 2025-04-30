package com.talentflow.MeatPriceTracker.Service;

import com.talentflow.MeatPriceTracker.Entity.Vendor;
import com.talentflow.MeatPriceTracker.Repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepo vendorRepo;

    public List<Vendor> getAllVendors(){
        return vendorRepo.findAll();
    }

    public Vendor createVendor(Vendor vendor){
       return vendorRepo.save(vendor);
    }

    public Vendor updateVendor(Long id, Vendor vendor){

        Vendor existingVendor = vendorRepo.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));

        existingVendor.setVendorName(vendor.getVendorName());
        existingVendor.setVendorType(vendor.getVendorType());
        existingVendor.setUpdatedAt(java.time.LocalDateTime.now());

        return vendorRepo.save(existingVendor);

    }

    public void deleteVendor(Long id){
        vendorRepo.deleteById(id);
    }
}
