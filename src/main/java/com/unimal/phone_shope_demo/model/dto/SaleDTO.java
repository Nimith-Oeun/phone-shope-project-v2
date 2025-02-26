package com.unimal.phone_shope_demo.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class SaleDTO {
    @NotEmpty
    private List<ProductSoldDTO> productSold;
    private LocalDateTime soldDate;
}
