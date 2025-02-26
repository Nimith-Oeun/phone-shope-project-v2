package com.unimal.phone_shope_demo.jpa_spacifiction;

import com.unimal.phone_shope_demo.model.Sale;
import com.unimal.phone_shope_demo.model.SaleDetail;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail> {
    private final FilterSaleDetail filterProductReport;
    @Override
    public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        Join<SaleDetail, Sale> sale = saleDetail.join("sale");//this use for join sale table
        if(Objects.nonNull(filterProductReport.getStartDate())){ // = filterProductReport.getStartDate() != null
            predicates.add(cb.greaterThanOrEqualTo(sale.get("soldDate"), filterProductReport.getStartDate()));
        }
        
        if(Objects.nonNull(filterProductReport.getEndDate())){
            predicates.add(cb.lessThanOrEqualTo(sale.get("soldDate"), filterProductReport.getEndDate()));
        }

        return cb.and(predicates.toArray(Predicate[]::new));

    }
}
