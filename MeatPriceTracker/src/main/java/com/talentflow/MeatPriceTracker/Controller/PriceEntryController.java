package com.talentflow.MeatPriceTracker.Controller;

import com.talentflow.MeatPriceTracker.Entity.PriceEntry;
import com.talentflow.MeatPriceTracker.Service.PriceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/prices")
public class PriceEntryController {

    @Autowired
    private PriceEntryService priceEntryService;

    @GetMapping
    public List<PriceEntry> getAllPrices(){
        return priceEntryService.getAllPriceEntry();
    }

    @PostMapping
    public PriceEntry createPrice(@RequestParam long productId,
                                  @RequestParam long vendorId,
                                  @RequestBody PriceEntry priceEntry) {
        return priceEntryService.createPriceEntry(productId, vendorId, priceEntry);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePrice(@PathVariable long id){
        priceEntryService.deletePriceEntry(id);
        return "Price Entry deleted";
    }

    @GetMapping("/product/{productId}")
    public List<PriceEntry> getPriceByProduct(@PathVariable long productId){
       return priceEntryService.getByProduct(productId);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<PriceEntry> getPriceByVendor(@PathVariable long vendorId){
        return priceEntryService.getByVendor(vendorId);
    }
}
