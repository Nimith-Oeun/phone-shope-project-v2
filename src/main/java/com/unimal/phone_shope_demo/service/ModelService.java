package com.unimal.phone_shope_demo.service;

import com.unimal.phone_shope_demo.model.Model;


import java.util.List;

public interface ModelService {
    Model save(Model model);
    List<Model> getByBrand(Long brandId);
    Model getById(Long id);

}
