package com.synoverge.orderservice.services;

import com.synoverge.common.dtos.BaseResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE", url = "http://localhost:8083/api/v1")
public interface ProductService {

    @PutMapping("/product/reduceQuantity/{id}")
    ResponseEntity<BaseResponseEntity> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    );

    @GetMapping("/product/{id}")
    ResponseEntity<BaseResponseEntity> getProductById(@PathVariable(name = "id") long id);
}
