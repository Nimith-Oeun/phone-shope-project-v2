package com.unimal.phone_shope_demo.service;

import com.unimal.phone_shope_demo.model.Product;
import com.unimal.phone_shope_demo.model.dto.ProductImportDTO;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductService {
    Product create(Product product);
    Product getById(Long id);
    Product getByModelIdAndColorId(Long modelId, Long colorId);
    void importProduct(ProductImportDTO productImportDTO);
    void setPrice(Long broductId, BigDecimal price);
    Map<Integer,String> uploadProduct(MultipartFile file);
}
