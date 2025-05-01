package com.talentflow.MeatPriceTracker.Repository;

import com.talentflow.MeatPriceTracker.Entity.PriceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PriceEntryRepo extends JpaRepository<PriceEntry, Long> {

    List<PriceEntry> findByProductId(Long productId);
    List<PriceEntry> findByVendorId(Long vendorId);
}
