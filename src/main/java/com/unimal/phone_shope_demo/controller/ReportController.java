package com.unimal.phone_shope_demo.controller;

import com.unimal.phone_shope_demo.model.dto.ExpenseReportDTO;
import com.unimal.phone_shope_demo.model.dto.ProductReportDTO;
import com.unimal.phone_shope_demo.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> getProductSold(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
        List<ProductReportDTO> productSold = reportService.getProductReport(startDate, endDate);
        return ResponseEntity.ok(productSold);
    }

    @GetMapping("expense/{startDate}/{endDate}")
    public ResponseEntity<?> expenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
        List<ExpenseReportDTO> exReport = reportService.getExpenseReport(startDate, endDate);
        return ResponseEntity.ok(exReport);
    }
}
