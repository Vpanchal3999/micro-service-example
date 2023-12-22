package com.synoverge.PaymentService.service;

import com.synoverge.PaymentService.model.dto.PaymentRequest;
import com.synoverge.PaymentService.model.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
