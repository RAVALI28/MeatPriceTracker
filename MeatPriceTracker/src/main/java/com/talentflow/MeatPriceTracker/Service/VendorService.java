package com.talentflow.MeatPriceTracker.Service;

import com.talentflow.MeatPriceTracker.Entity.Vendor;
import com.talentflow.MeatPriceTracker.Repository.UserRepo;
import com.talentflow.MeatPriceTracker.Repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepo vendorRepo;

    @Autowired
    private HistoryLogService historyLogService;

    @Autowired
    private UserRepo userRepo;

    public List<Vendor> getAllVendors(){
        return vendorRepo.findAll();
    }

    public Vendor createVendor(Vendor vendor){

       Vendor createdVendor = vendorRepo.save(vendor);

       //Track who is doing anything here,
        historyLogService.log(getCurrentUserId(), "CREATE", "VENDOR", "Created the vendor : " +createdVendor.getVendorName());

        return createdVendor;
    }

    public Vendor updateVendor(Long id, Vendor vendor){

        Vendor existingVendor = vendorRepo.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));

        existingVendor.setVendorName(vendor.getVendorName());
        existingVendor.setVendorType(vendor.getVendorType());
        existingVendor.setUpdatedAt(java.time.LocalDateTime.now());

        Vendor updatedVendor = vendorRepo.save(existingVendor);

        //Track who is doing anything here,
        historyLogService.log(getCurrentUserId(), "UPDATE", "VENDOR", "Updated the vendor :" +updatedVendor.getVendorName());

        return updatedVendor;

    }

    public void deleteVendor(Long id){

        Vendor vendor = vendorRepo.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));
        vendorRepo.deleteById(id);

        //Track who is doing anything here

        historyLogService.log(getCurrentUserId(), "DELETE", "VENDOR", "Deleted the vendor");
    }

    private long getCurrentUserId() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();

    }
}
