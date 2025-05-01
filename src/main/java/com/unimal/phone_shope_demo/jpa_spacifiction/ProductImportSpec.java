package com.unimal.phone_shope_demo.jpa_spacifiction;

import com.unimal.phone_shope_demo.model.ProductImportHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Data
public class ProductImportSpec implements Specification<ProductImportHistory> {
    private final FilterProductImport filterProductImport;
    List<Predicate> predicates = new ArrayList<>();
    @Override
    public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(Objects.nonNull(filterProductImport.getStartDate())){
            predicates.add(cb.greaterThanOrEqualTo(importHistory.get("importDate"), filterProductImport.getStartDate()));
        }
        if(Objects.nonNull(filterProductImport.getEndDate())){
            predicates.add(cb.lessThanOrEqualTo(importHistory.get("importDate"), filterProductImport.getEndDate()));
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
