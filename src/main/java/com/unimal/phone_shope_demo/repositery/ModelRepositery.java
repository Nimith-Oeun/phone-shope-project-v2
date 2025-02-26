package com.unimal.phone_shope_demo.repositery;

import com.unimal.phone_shope_demo.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepositery extends JpaRepository<Model, Long> {
    List<Model> findByBrandId(Long brandId);
}
