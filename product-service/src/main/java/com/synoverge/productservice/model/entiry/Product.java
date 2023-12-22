package com.synoverge.productservice.model.entiry;

import jakarta.persistence.*;

@Entity
@Table(name="product_details")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    @Column(name = "name")
    private String productName;

    @Column(name = "price")
    private long price;

    @Column(name = "quantity")
    private long quantity;

    public Product() {
    }

    public Product(String productName, long price, long quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
