package com.synoverge.productservice.controller;

import com.synoverge.common.dtos.BaseResponseEntity;
import com.synoverge.productservice.model.dto.ProductRequest;
import com.synoverge.productservice.model.dto.ProductResponse;
import com.synoverge.productservice.services.ProductService;
import com.synoverge.productservice.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.baseUrl)
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/add")
    public ResponseEntity<BaseResponseEntity> addProduct(@RequestBody ProductRequest productRequest) {
        long productId = productService.addProduct(productRequest);
        BaseResponseEntity response = new BaseResponseEntity(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), "Product added in your cart!..", productId);
        return new ResponseEntity<BaseResponseEntity>(response, HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<BaseResponseEntity> getProductById(@PathVariable("id") long productId) {

        ProductResponse productResponse
                = productService.getProductById(productId);
        BaseResponseEntity baseResponse = new BaseResponseEntity(HttpStatus.OK.value(), HttpStatus.OK.name(), "Product details by id fetch successfully!!..",productResponse);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/product/reduceQuantity/{id}")
    public ResponseEntity<BaseResponseEntity> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    ) {

        ProductResponse productResponse = productService.reduceQuantity(productId,quantity);
        BaseResponseEntity baseResponse = new BaseResponseEntity(HttpStatus.OK.value(), HttpStatus.OK.name(), "Product Quantity reduced!!..",productResponse);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseEntity> deleteProductById(@PathVariable("id") long productId) {
        productService.deleteProductById(productId);
        BaseResponseEntity baseResponse = new BaseResponseEntity(HttpStatus.OK.value(), HttpStatus.OK.name(), "Product detail delete successfully!!..",null);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
