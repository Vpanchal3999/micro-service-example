package com.synoverge.PaymentService.controller;

import com.synoverge.PaymentService.model.dto.PaymentRequest;
import com.synoverge.PaymentService.model.dto.PaymentResponse;
import com.synoverge.PaymentService.service.PaymentService;
import com.synoverge.common.dtos.BaseResponseEntity;
import com.synoverge.common.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.baseUrl)
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/transaction")
    public ResponseEntity<PaymentResponse> doPayment(@RequestBody PaymentRequest paymentRequest) {

        PaymentResponse response =  paymentService.doPayment(paymentRequest);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/transaction/order/{orderId}")
    public ResponseEntity<BaseResponseEntity> getPaymentDetailsByOrderId(@PathVariable long orderId) {
        PaymentResponse paymentResponse = paymentService.getPaymentDetailsByOrderId(orderId);
        BaseResponseEntity response = new BaseResponseEntity(HttpStatus.OK.value(), HttpStatus.OK.name(), "Fetch all Transaction by Order id..", paymentResponse);
        return new ResponseEntity<>(
               response,
                HttpStatus.OK
        );
    }
}
