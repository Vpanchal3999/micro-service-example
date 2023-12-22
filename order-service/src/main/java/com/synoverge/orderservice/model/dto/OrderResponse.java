package com.synoverge.orderservice.model.dto;


import java.util.Date;

public class OrderResponse {

    private long orderId;
    private Date orderDate;
    private String orderStatus;
    private long amount;
    private Object productDetails;
    private Object paymentDetails;

    public OrderResponse() {
    }

    public OrderResponse(long orderId, Date orderDate, String orderStatus, long amount, Object productDetails, Object paymentDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.amount = amount;
        this.productDetails = productDetails;
        this.paymentDetails = paymentDetails;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    public Object getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Object productDetails) {
        this.productDetails = productDetails;
    }

    public Object getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Object  paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
