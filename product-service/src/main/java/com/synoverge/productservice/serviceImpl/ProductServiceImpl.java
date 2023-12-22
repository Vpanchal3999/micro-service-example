package com.synoverge.productservice.serviceImpl;

import com.synoverge.productservice.model.dto.ProductRequest;
import com.synoverge.productservice.model.dto.ProductResponse;
import com.synoverge.productservice.model.entiry.Product;
import com.synoverge.productservice.repository.ProductRepository;
import com.synoverge.productservice.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public long addProduct(ProductRequest productRequest) {
        Product product = new Product(productRequest.getName(),productRequest.getPrice(),productRequest.getQuantity());
        productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        Product product
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new NoSuchElementException("Product with given Id not found"));

        ProductResponse productResponse
                = new ProductResponse();

        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

    @Override
    public ProductResponse reduceQuantity(long productId, long quantity) {
        Product product
                = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Product with given Id not found"
                ));

        if(product.getQuantity() < quantity) {
            throw new IllegalArgumentException(
                    "Product does not have sufficient Quantity"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        ProductResponse productResponse = new ProductResponse(product.getProductName(),product.getProductId(),product.getQuantity(),product.getQuantity());
        return productResponse;
    }

    @Override
    public void deleteProductById(long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NoSuchElementException(
                    "Product with given with Id: " + productId + " not found:");
        }
        productRepository.deleteById(productId);
    }
}
