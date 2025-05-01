package com.unimal.phone_shope_demo.repositery;

import com.unimal.phone_shope_demo.model.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportRepositery extends JpaRepository<ProductImportHistory, Long> , JpaSpecificationExecutor<ProductImportHistory> {
}
