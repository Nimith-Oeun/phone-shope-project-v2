package com.unimal.phone_shope_demo.repositery;


import com.unimal.phone_shope_demo.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepoSitery extends JpaRepository<Brand, Long> , JpaSpecificationExecutor<Brand> {
    /*
    * JpaSpecificationExecutor is used to execute the specification
    * if you want to find by name or something else you can use this
    * */
    List<Brand> findByNameIgnoreCase(String name);
    List<Brand> findByNameLike(String name);
    Brand findBrandById(Long id);
}
