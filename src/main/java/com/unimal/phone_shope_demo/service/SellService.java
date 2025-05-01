package com.unimal.phone_shope_demo.service;

import com.unimal.phone_shope_demo.model.Sale;
import com.unimal.phone_shope_demo.model.dto.SaleDTO;

public interface SellService {
    void sellProduct(SaleDTO saleDTO);
    void cancelSale(Long saleId);
    Sale getById(Long saleId);
}
