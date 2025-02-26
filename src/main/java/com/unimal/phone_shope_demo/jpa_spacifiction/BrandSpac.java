package com.unimal.phone_shope_demo.jpa_spacifiction;

import com.unimal.phone_shope_demo.model.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BrandSpac implements Specification<Brand> {
    private final FilterBrand filterBrand;
    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {

        if (filterBrand.getName() != null) {
//            predicates.add(cb.equal(brand.get("name"), filterBrand.getName())); filter must be much product name
//            predicates.add(cb.like(brand.get("name"), "%" + filterBrand.getName() + "%")); // filter just put 1 letter or 1 word of product name but not case sensitive
            predicates.add(cb.like(cb.upper(brand.get("name")), "%" + filterBrand.getName().toUpperCase() + "%")); // filter just put 1 letter or 1 word of product name but case sensitive,,
        }

        if (filterBrand.getId() != null) {
            predicates.add(cb.equal(brand.get("id"), filterBrand.getId()));

        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
