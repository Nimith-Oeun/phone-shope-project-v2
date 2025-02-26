package com.unimal.phone_shope_demo.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductImportDTO {
    @NotNull(message = "Product ID is required")
    private Long productId;
    @Min(value = 1, message = "Imported units must be greater than 0")
    private Integer importedUnits;
    @DecimalMin(value = "0.01", message = "Price per unit must be greater than 0")
    private BigDecimal pricePerUnit;
    @NotNull(message = "Import date is required")
    private LocalDateTime importDate;
}
