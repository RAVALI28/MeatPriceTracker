package com.talentflow.MeatPriceTracker.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PriceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private double price;
    private LocalDate priceDate;

    private LocalDateTime createdAt = LocalDateTime.now();

    //Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(LocalDate priceDate) {
        this.priceDate = priceDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    //Args-Constructor
    public PriceEntry(Long id, Product product, Vendor vendor, double price, LocalDate priceDate, LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.vendor = vendor;
        this.price = price;
        this.priceDate = priceDate;
        this.createdAt = createdAt;
    }

    //NoArgs-Constructor
    public PriceEntry() {
    }
}
