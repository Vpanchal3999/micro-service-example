package com.synoverge.productservice.repository;

import com.synoverge.productservice.model.entiry.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
