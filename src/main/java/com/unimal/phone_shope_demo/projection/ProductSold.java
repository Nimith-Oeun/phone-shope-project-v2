package com.unimal.phone_shope_demo.projection;

import java.math.BigDecimal;

public interface ProductSold {
    Long getProductId();
    String getProductName();
    Integer getUnit();
    BigDecimal getTotalAmount();
}
