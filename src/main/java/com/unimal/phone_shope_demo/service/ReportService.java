package com.unimal.phone_shope_demo.service;

import com.unimal.phone_shope_demo.model.dto.ProductReportDTO;
import com.unimal.phone_shope_demo.projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
//    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate); // this user Raw query for get report
    List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate);
}
