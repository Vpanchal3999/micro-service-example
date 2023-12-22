package com.synoverge.PaymentService.model.dto;

import com.synoverge.common.enums.PaymentMode;

import java.util.Date;

public class PaymentResponse {

    private long paymentId;
    private String status;
    private PaymentMode paymentMode;
    private long amount;
    private Date paymentDate;
    private long orderId;

    public PaymentResponse() {
    }

    public PaymentResponse(long paymentId, String status, PaymentMode paymentMode, long amount, Date paymentDate, long orderId) {
        this.paymentId = paymentId;
        this.status = status;
        this.paymentMode = paymentMode;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.orderId = orderId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
