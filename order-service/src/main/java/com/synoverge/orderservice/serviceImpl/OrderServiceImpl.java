package com.synoverge.orderservice.serviceImpl;


import com.synoverge.common.dtos.PaymentResponse;
import com.synoverge.common.dtos.BaseResponseEntity;
import com.synoverge.common.dtos.PaymentRequest;
import com.synoverge.orderservice.model.dto.OrderRequest;
import com.synoverge.orderservice.model.dto.OrderResponse;
import com.synoverge.orderservice.model.entity.Order;
import com.synoverge.orderservice.repository.OrderRepository;
import com.synoverge.orderservice.services.OrderService;
import com.synoverge.orderservice.services.PaymentService;
import com.synoverge.orderservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    private RestTemplate restTemplate;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order(orderRequest.getProductId(),
                orderRequest.getQuantity(),
                new Date(),
                "CREATED",
                    orderRequest.getTotalAmount());

        order = orderRepository.save(order);

        PaymentRequest paymentRequest = new PaymentRequest(
                                        order.getId(),
                                        order.getAmount(),
                                        orderRequest.getPaymentMode());


        String orderStatus = null;
        ResponseEntity<PaymentResponse> baseResponseEntityResponseEntity;
        PaymentResponse paymentResponse = null;

        try {
            baseResponseEntityResponseEntity =  paymentService.doPayment(paymentRequest);
            paymentResponse = baseResponseEntityResponseEntity.getBody();
            orderStatus = "PLACED";
        } catch (Exception e) {
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);

        orderRepository.save(order);
        OrderResponse response = new OrderResponse(order.getId(),order.getOrderDate(),order.getOrderStatus(),order.getAmount(),order.getProductId(),paymentResponse);
        return response;
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        Order order
                = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found for the order Id:" + orderId));

        ResponseEntity<BaseResponseEntity> responseProductDetails = productService.getProductById(order.getProductId());
        BaseResponseEntity baseResponseProduct = responseProductDetails.getBody();
        Object productResponse = baseResponseProduct.getData();

        ResponseEntity<BaseResponseEntity> responsePaymentDetails = paymentService.getPaymentDetailsByOrderId(order.getId());
        BaseResponseEntity baseResponsePayment = responsePaymentDetails.getBody();
        Object paymentResponse =  baseResponsePayment.getData();
        OrderResponse orderResponse
                = new OrderResponse(order.getId(),order.getOrderDate(),order.getOrderStatus(),order.getAmount(),productResponse,paymentResponse);

        return orderResponse;
    }
}
