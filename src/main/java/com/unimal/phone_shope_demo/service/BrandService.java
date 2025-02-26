package com.unimal.phone_shope_demo.service;


import com.unimal.phone_shope_demo.model.Brand;
import org.springframework.data.domain.Page;


import java.util.Map;
public interface BrandService {
        Brand create(Brand brand);
        Brand getById(Long id);
//        List<Brand> getBrand(Map<String,String> param); //filter and getAllBrand without pageination
        Page<Brand> getBrand(Map<String,String> param);
        Brand update(Long id ,Brand brandUpdate);
        void delete(Long id); // if user call Brand delete it must be error ambigous call



}
