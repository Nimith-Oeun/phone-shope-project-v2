package com.unimal.phone_shope_demo.repositery;

import com.unimal.phone_shope_demo.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepositery extends JpaRepository<SaleDetail, Long> , JpaSpecificationExecutor<SaleDetail> {
    List<SaleDetail> findBySaleId(Long saleId);

}
