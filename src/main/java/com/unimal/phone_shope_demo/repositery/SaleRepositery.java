package com.unimal.phone_shope_demo.repositery;

import com.unimal.phone_shope_demo.model.Sale;
import com.unimal.phone_shope_demo.projection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepositery extends JpaRepository<Sale, Long> {
   /* this is the query for get report using raw query
    *
   @Query(value = "select p.id as productId, p.name as productName, sum(sd.unit) as unit, sum(sd.unit * sd.sold_amount) as totalAmount\r\n"
            + "from sale_details sd \r\n"
            + "inner join sales s on sd.sale_id = s.sale_id\r\n"
            + "inner join products p on p.id = sd.product_id\r\n"
            + "where date(s.sold_date) >= :startDate and date(s.sold_date) <= :endDate\r\n"
            + "group by p.id, p.name\r\n"
            + "", nativeQuery = true)
    List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);

    */
}
