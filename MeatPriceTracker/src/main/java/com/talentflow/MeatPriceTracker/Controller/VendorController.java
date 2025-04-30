package com.talentflow.MeatPriceTracker.Controller;

import com.talentflow.MeatPriceTracker.Entity.Vendor;
import com.talentflow.MeatPriceTracker.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public Vendor createVendor(@RequestBody Vendor vendor){
        return vendorService.createVendor(vendor);
    }

    @GetMapping
    public List<Vendor> getAllvendors(){
        return vendorService.getAllVendors();

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Vendor updateVendor(@PathVariable Long id, @RequestBody Vendor vendor){
        return vendorService.updateVendor(id, vendor);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteVendor(@PathVariable Long id){
        vendorService.deleteVendor(id);
        return "Vendor Successfully deleted";
    }
}
