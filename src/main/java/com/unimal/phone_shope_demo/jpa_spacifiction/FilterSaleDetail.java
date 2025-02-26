package com.unimal.phone_shope_demo.jpa_spacifiction;

import lombok.Data;

import java.time.LocalDate;
@Data
public class FilterSaleDetail {
    private LocalDate startDate;
    private LocalDate endDate;
}
