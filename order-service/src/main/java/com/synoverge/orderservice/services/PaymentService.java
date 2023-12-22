package com.synoverge.orderservice.services;

import com.synoverge.common.dtos.PaymentRequest;
import com.synoverge.common.dtos.PaymentResponse;
import com.synoverge.common.dtos.BaseResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE", url = "http://localhost:8084/api/v1")
public interface PaymentService {

    @PostMapping("/transaction")
    public ResponseEntity<PaymentResponse> doPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/transaction/order/{orderId}")
    ResponseEntity<BaseResponseEntity> getPaymentDetailsByOrderId(@PathVariable(name = "orderId") long orderId);

}
