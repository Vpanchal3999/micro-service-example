package com.synoverge.productservice.services;

import com.synoverge.productservice.model.dto.ProductRequest;
import com.synoverge.productservice.model.dto.ProductResponse;
import com.synoverge.productservice.model.entiry.Product;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    ProductResponse reduceQuantity(long productId, long quantity);

    public void deleteProductById(long productId);
}
