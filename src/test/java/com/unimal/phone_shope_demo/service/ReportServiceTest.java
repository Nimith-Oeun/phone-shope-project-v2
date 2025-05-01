package com.unimal.phone_shope_demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import com.unimal.phone_shope_demo.jpa_spacifiction.ProductImportSpec;
import com.unimal.phone_shope_demo.model.Product;
import com.unimal.phone_shope_demo.model.ProductImportHistory;
import com.unimal.phone_shope_demo.model.dto.ExpenseReportDTO;
import com.unimal.phone_shope_demo.repositery.ProductImportRepositery;
import com.unimal.phone_shope_demo.repositery.ProductRepositery;
import com.unimal.phone_shope_demo.repositery.SaleDetailRepositery;
import com.unimal.phone_shope_demo.repositery.SaleRepositery;
import com.unimal.phone_shope_demo.service.impl.ReportServiceImpl;
import com.unimal.phone_shope_demo.util.ReportTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	@Mock
	private SaleRepositery saleRepository;
	@Mock
	private SaleDetailRepositery saleDetailRepository;
	@Mock
	private ProductRepositery productRepository;
	@Mock
	private ProductImportRepositery productImportHistoryRepository;

	private ReportService reportService;

	@BeforeEach
	public void setup() {
		reportService = new ReportServiceImpl(saleRepository, saleDetailRepository, productRepository,
				productImportHistoryRepository);
	}

	@Test
	public void testGetExpenseReport() {
		//given
		List<ProductImportHistory> importHistories = ReportTestHelper.getProductImportHistories();
		List<Product> products = ReportTestHelper.getProducts();
		//when
		when(productImportHistoryRepository.findAll(Mockito.any(ProductImportSpec.class)))
			.thenReturn(importHistories);
		
		when(productRepository.findAllById(anySet())).thenReturn(products);
		
		List<ExpenseReportDTO> expenseReports = reportService.getExpenseReport(LocalDate.now().minusMonths(1), LocalDate.now());
		//then
		
		assertEquals(2, expenseReports.size());
		ExpenseReportDTO expense1 = expenseReports.get(0);
		assertEquals(1, expense1.getProductId());
		assertEquals("iphone 14 pro", expense1.getProductName());
		assertEquals(15, expense1.getTotalUnits());
		assertEquals(18250d, expense1.getTotalAmount().doubleValue());

	}
}