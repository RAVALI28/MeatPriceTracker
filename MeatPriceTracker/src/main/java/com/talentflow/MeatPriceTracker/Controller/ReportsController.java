package com.talentflow.MeatPriceTracker.Controller;

import com.talentflow.MeatPriceTracker.Entity.PriceEntry;
import com.talentflow.MeatPriceTracker.Service.ReportsService;
import org.springframework.aot.generate.GeneratedTypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/average-price")
    public Map<String, Double> averagePricePerProduct(){
        return reportsService.averagePricePerProduct();

    }

    @GetMapping("/trend/{productId}")
    public Map<String, List<PriceEntry>> priceTrendByProduct(@PathVariable long productId){
        return reportsService.priceTrendByProduct(productId);

    }

    @GetMapping("/range")
    public List<PriceEntry> byDataRange(@RequestParam String start, @RequestParam String end){
        return reportsService.pricesBetween(LocalDate.parse(start), LocalDate.parse(end));

    }


}
