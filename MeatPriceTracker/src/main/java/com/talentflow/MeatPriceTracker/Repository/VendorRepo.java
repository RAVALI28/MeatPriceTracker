package com.talentflow.MeatPriceTracker.Repository;

import com.talentflow.MeatPriceTracker.Entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {
}
