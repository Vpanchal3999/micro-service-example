package com.synoverge.PaymentService.model.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.lang.annotation.Repeatable;
import java.util.Date;

@Entity
@Table(name = "transaction_details")
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "mode")
    private String paymentMode;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Column(name = "STATUS")
    private String paymentStatus;

    @Column(name = "AMOUNT")
    private long amount;

    public TransactionDetails() {
    }

    public TransactionDetails(long orderId, String paymentMode, String referenceNumber, Date paymentDate, String paymentStatus, long amount) {
        this.orderId = orderId;
        this.paymentMode = paymentMode;
        this.referenceNumber = referenceNumber;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
