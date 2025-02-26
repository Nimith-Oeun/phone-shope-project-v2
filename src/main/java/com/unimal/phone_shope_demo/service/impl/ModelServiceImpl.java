package com.unimal.phone_shope_demo.service.impl;

import com.unimal.phone_shope_demo.exception.ResoureNoteFoundException;
import com.unimal.phone_shope_demo.model.Brand;
import com.unimal.phone_shope_demo.model.Model;
import com.unimal.phone_shope_demo.repositery.ModelRepositery;
import com.unimal.phone_shope_demo.service.BrandService;
import com.unimal.phone_shope_demo.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepositery modelRepositery;
    @Override
    public Model save(Model model) {
        return modelRepositery.save(model);
    }

    @Override
    public List<Model> getByBrand(Long brandId) {
        return modelRepositery.findByBrandId(brandId);
    }

    @Override
    public Model getById(Long id) {
        return modelRepositery.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Model",id));
    }
}
