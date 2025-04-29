package com.talentflow.MeatPriceTracker.Repository;

import com.talentflow.MeatPriceTracker.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
