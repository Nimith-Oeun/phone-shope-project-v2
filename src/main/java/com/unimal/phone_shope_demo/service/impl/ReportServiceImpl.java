package com.unimal.phone_shope_demo.service.impl;

import com.unimal.phone_shope_demo.jpa_spacifiction.FilterSaleDetail;
import com.unimal.phone_shope_demo.jpa_spacifiction.SaleDetailSpec;
import com.unimal.phone_shope_demo.model.Product;
import com.unimal.phone_shope_demo.model.SaleDetail;
import com.unimal.phone_shope_demo.model.dto.ProductReportDTO;
import com.unimal.phone_shope_demo.projection.ProductSold;
import com.unimal.phone_shope_demo.repositery.ProductRepositery;
import com.unimal.phone_shope_demo.repositery.SaleDetailRepositery;
import com.unimal.phone_shope_demo.repositery.SaleRepositery;
import com.unimal.phone_shope_demo.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final SaleRepositery saleRepositery;
    private final SaleDetailRepositery saleDetailRepositery;
    private final ProductRepositery productRepositery;

    /* this function for get report using Raw query
    @Override
    public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) { // this use Raw query for get report
        return saleRepositery.findProductSold(startDate, endDate);
    }

     */

    @Override
    public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
        List<ProductReportDTO> list = new ArrayList<>();// create list for store report
        FilterSaleDetail filterSaleDetail = new FilterSaleDetail(); //call object filter for throw start date and end date
        filterSaleDetail.setStartDate(startDate);
        filterSaleDetail.setEndDate(endDate);
        Specification<SaleDetail> spec = new SaleDetailSpec(filterSaleDetail); // call object spec for throw filter
        List<SaleDetail> saleDetails = saleDetailRepositery.findAll(spec); // get all data from sale detail

        // this use for get product id
        List<Long> productIds = saleDetails.stream()
                .map(SaleDetail::getProduct)
                .map(Product::getId)
                .collect(Collectors.toList());

        // this use for get product id and product name and store in map
        Map<Long, Product> productMap = productRepositery.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        // this use for grouping product and sale detail
        Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
                .collect(Collectors.groupingBy(SaleDetail::getProduct));//

        // this loop for get product id and product name and store in list
        for (var entry : saleDetailMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getId()); // get product id and name from product map
            List<SaleDetail> sdList = entry.getValue();// get data from sale detail map

            // this use for get total unit of product
            Integer unite = sdList.stream().map(SaleDetail::getUnits)
                    .reduce(0, (a, b) -> a + b);

            // this use for get total amount of product
            Double totalAmount = sdList.stream()
                    .map(sd -> sd.getUnits() * sd.getAmount().doubleValue())
                    .reduce(0.0, (a, b) -> a + b);

            ProductReportDTO reportDTO = new ProductReportDTO();
            reportDTO.setProductId(product.getId());
            reportDTO.setProductName(product.getName());
            reportDTO.setUnit(unite);
            reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
            list.add(reportDTO);
        }

        return list;
    }
}
