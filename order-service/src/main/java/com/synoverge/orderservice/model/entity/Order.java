package com.synoverge.orderservice.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "order_details")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "status")
    private String orderStatus;

    @Column(name = "totalAmount")
    private long amount;

    public Order() {
    }

    public Order(long productId, long quantity, Date orderDate, String orderStatus, long amount) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
