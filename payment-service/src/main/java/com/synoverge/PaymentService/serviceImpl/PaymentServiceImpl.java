package com.synoverge.PaymentService.serviceImpl;

import com.synoverge.PaymentService.model.dto.PaymentRequest;
import com.synoverge.PaymentService.model.dto.PaymentResponse;
import com.synoverge.PaymentService.model.entity.TransactionDetails;
import com.synoverge.PaymentService.repository.TransactionDetailsRepository;
import com.synoverge.PaymentService.service.PaymentService;
import com.synoverge.common.enums.PaymentMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public PaymentResponse doPayment(PaymentRequest paymentRequest) {
        TransactionDetails transactionDetails =  new TransactionDetails(
                paymentRequest.getOrderId(),
                paymentRequest.getPaymentMode().getModeType(),
                paymentRequest.getReferenceNumber(),
                new Date(),
                "SUCCESS",
                paymentRequest.getAmount());
        transactionDetails = transactionDetailsRepository.save(transactionDetails);
        PaymentResponse paymentResponse = new PaymentResponse(transactionDetails.getId(),transactionDetails.getPaymentStatus(), PaymentMode.valueOf(transactionDetails.getPaymentMode()),transactionDetails.getAmount(),transactionDetails.getPaymentDate(),transactionDetails.getOrderId());
        return paymentResponse;
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        TransactionDetails transactionDetails
                = transactionDetailsRepository.findByOrderId(orderId)
                .orElseThrow(() -> new NoSuchElementException(
                        "TransactionDetails with given id not found"));

        PaymentResponse paymentResponse = new PaymentResponse (transactionDetails.getId(),
                transactionDetails.getPaymentStatus(),
                PaymentMode.valueOf(transactionDetails.getPaymentMode()),
                transactionDetails.getAmount(),
                transactionDetails.getPaymentDate(),
                transactionDetails.getOrderId());

        return paymentResponse;
    }
}
