package com.synoverge.orderservice.controller;

import com.synoverge.common.dtos.BaseResponseEntity;
import com.synoverge.common.utility.Constants;
import com.synoverge.orderservice.model.dto.OrderRequest;
import com.synoverge.orderservice.model.dto.OrderResponse;
import com.synoverge.orderservice.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.baseUrl)
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/place")
    public ResponseEntity<BaseResponseEntity> placeOrder(@RequestBody OrderRequest orderRequest) {

        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        BaseResponseEntity baseResponse = new BaseResponseEntity(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), "Order Place successfully!!",orderResponse);
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }

//    @GetMapping("order/{orderId}")
//    public ResponseEntity<BaseResponseEntity> getOrderDetails(@PathVariable long orderId) {
//
//        OrderResponse orderResponse
//                = orderService.getOrderDetails(orderId);
//        BaseResponseEntity baseResponse = new BaseResponseEntity(HttpStatus.OK.value(), HttpStatus.OK.name(), "Order Details fetch successfully!!",orderResponse);
//
//        return new ResponseEntity<BaseResponseEntity>(baseResponse,
//                HttpStatus.OK);
//    }
}
