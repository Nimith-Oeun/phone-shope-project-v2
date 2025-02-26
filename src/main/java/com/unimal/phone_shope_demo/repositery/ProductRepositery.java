package com.unimal.phone_shope_demo.repositery;

import com.unimal.phone_shope_demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepositery extends JpaRepository<Product,Long> {
    Optional<Product>findByModelIdAndColorId(Long modelId, Long colorId);
}
