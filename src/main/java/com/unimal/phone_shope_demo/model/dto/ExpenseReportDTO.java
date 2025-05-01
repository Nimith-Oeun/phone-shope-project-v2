package com.unimal.phone_shope_demo.model.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ExpenseReportDTO {
    private Long productId;
    private String productName;
    private Integer totalUnits;
    private BigDecimal totalAmount;
}
