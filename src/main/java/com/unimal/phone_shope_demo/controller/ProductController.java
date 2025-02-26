package com.unimal.phone_shope_demo.controller;

import com.unimal.phone_shope_demo.mapper.ProductMapper;
import com.unimal.phone_shope_demo.model.Product;
import com.unimal.phone_shope_demo.model.dto.PriceDTO;
import com.unimal.phone_shope_demo.model.dto.ProductDTO;
import com.unimal.phone_shope_demo.model.dto.ProductImportDTO;
import com.unimal.phone_shope_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        Product productMap = productMapper.mapToProduct(productDTO);
        Product productCreate = productService.create(productMap);
        return ResponseEntity.ok(productCreate);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importProduct(@Valid @RequestBody ProductImportDTO importDTO) {
        productService.importProduct(importDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{productId}/set-price")
    public ResponseEntity<?> setPrice(@PathVariable Long productId  , @RequestBody PriceDTO priceDTO) {
        productService.setPrice(productId, priceDTO.getPrice());
        return ResponseEntity.ok().build();
    }

    @PostMapping("uploadProduct")
    public ResponseEntity<?> uploadProduct(@RequestParam("file") MultipartFile file) {
        Map<Integer,String>mapError = productService.uploadProduct(file);
        return ResponseEntity.ok(mapError);
    }
}