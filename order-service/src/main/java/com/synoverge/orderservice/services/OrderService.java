package com.synoverge.orderservice.services;

import com.synoverge.orderservice.model.dto.OrderRequest;
import com.synoverge.orderservice.model.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
