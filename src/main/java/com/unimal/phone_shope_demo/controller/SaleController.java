package com.unimal.phone_shope_demo.controller;

import com.unimal.phone_shope_demo.model.dto.SaleDTO;
import com.unimal.phone_shope_demo.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SellService sellService;

    @PostMapping("")
    public ResponseEntity<?> createSale(@RequestBody SaleDTO saleDTO) {
        sellService.sellProduct(saleDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("{saleId}/cancel")
    public ResponseEntity<?> cancelSale(@PathVariable Long saleId) {
        sellService.cancelSale(saleId);
        return ResponseEntity.ok("Cancel sale successfully");
    }
}
