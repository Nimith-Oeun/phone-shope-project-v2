package com.unimal.phone_shope_demo.util;

import com.unimal.phone_shope_demo.model.Product;
import com.unimal.phone_shope_demo.model.ProductImportHistory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



public class ReportTestHelper {

	private static Product product1 = Product.builder()
			.id(1L)
			.name("iphone 14 pro")
			.build();

	private static Product product2 = Product.builder()
			.id(2L)
			.name("iphone 13 pro max")
			.build();

	private static Product product3 = Product.builder()
			.id(3L)
			.name("samsung galaxy s10")
			.build();

	public static List<Product> getProducts(){
		return List.of(product1,product2);
	}

	public static List<ProductImportHistory> getProductImportHistories(){
		ProductImportHistory history1 = ProductImportHistory.builder()
				.product(product1)
				.importedUnits(10)
				.pricePerUnit(BigDecimal.valueOf(1200))
				.importDate(LocalDateTime.of(2023, 1, 1, 8, 50))
				.build();

		ProductImportHistory history2 = ProductImportHistory.builder()
				.product(product2)
				.importedUnits(15)
				.pricePerUnit(BigDecimal.valueOf(1100))
				.importDate(LocalDateTime.of(2023, 1, 5, 10, 20))
				.build();

		ProductImportHistory history3 = ProductImportHistory.builder()
				.product(product1)
				.importedUnits(5)
				.pricePerUnit(BigDecimal.valueOf(1250))
				.importDate(LocalDateTime.of(2023, 1, 6, 11, 30))
				.build();
		return List.of(history1, history2, history3);
	}
}