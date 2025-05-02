package com.talentflow.MeatPriceTracker.Service;

import com.talentflow.MeatPriceTracker.Entity.PriceEntry;
import com.talentflow.MeatPriceTracker.Repository.PriceEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportsService {

    @Autowired
    private PriceEntryRepo priceEntryRepo;

    public Map<String, Double> averagePricePerProduct(){
        List<PriceEntry> entries = priceEntryRepo.findAll();

        return entries.stream()
                .collect(Collectors.groupingBy(e -> e.getProduct().getProductName(),
                        Collectors.collectingAndThen(
                                Collectors.averagingDouble(e -> e.getPrice()),
                                Double::valueOf
                        )
                ));

    }

    public Map<String, List<PriceEntry>> priceTrendByProduct(long productId){
        List<PriceEntry> entries = priceEntryRepo.findByProductId(productId);
        return entries.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getPriceDate().getMonth().toString()
                ));

    }

    public List<PriceEntry> pricesBetween(LocalDate start, LocalDate end){
        return priceEntryRepo.findAll().stream()
                .filter(e -> !e.getPriceDate().isBefore(start) && !e.getPriceDate().isAfter(end))
                .toList();
    }
}
