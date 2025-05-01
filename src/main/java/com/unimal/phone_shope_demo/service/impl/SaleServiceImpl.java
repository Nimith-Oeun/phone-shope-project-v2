package com.unimal.phone_shope_demo.service.impl;

import com.unimal.phone_shope_demo.exception.ApiException;
import com.unimal.phone_shope_demo.exception.ResoureNoteFoundException;
import com.unimal.phone_shope_demo.model.Product;
import com.unimal.phone_shope_demo.model.Sale;
import com.unimal.phone_shope_demo.model.SaleDetail;
import com.unimal.phone_shope_demo.model.dto.ProductSoldDTO;
import com.unimal.phone_shope_demo.model.dto.SaleDTO;
import com.unimal.phone_shope_demo.repositery.ProductRepositery;
import com.unimal.phone_shope_demo.repositery.SaleDetailRepositery;
import com.unimal.phone_shope_demo.repositery.SaleRepositery;
import com.unimal.phone_shope_demo.service.ProductService;
import com.unimal.phone_shope_demo.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SellService {
    private final ProductService productService;
    private final ProductRepositery productRepositery;
    private final SaleRepositery saleRepositery;
    private final SaleDetailRepositery saleDetailRepositery;

    @Override
    public void sellProduct(SaleDTO saleDTO) {
        List<Long> productId = saleDTO.getProductSold()
                .stream().map(ProductSoldDTO::getProductId)
                .toList(); // create list of product id from productSoldDTO
        // validation
        productId.forEach(productService::getById); // check if product exist
        List<Product> products = productRepositery.findAllById(productId);// get all product
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));// this function will convert list to map

        //validate Stock
        saleDTO.getProductSold().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());// get product from map
            if (product.getAvailableUnits() < ps.getQuantity()) { // check if product is available
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "Product %s is out of stock".formatted(product.getName()));
            }
        });

        // save sale
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSoldDate());// set sold date
        saleRepositery.save(sale);

        //sale detail
        saleDTO.getProductSold().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());// get product from map
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());// set amount
            saleDetail.setProduct(product);// set product
            saleDetail.setUnits(ps.getQuantity());// set quantity
            saleDetail.setSale(sale);// set sale
            saleDetailRepositery.save(saleDetail);

            // update stock
            Integer availableUnits = product.getAvailableUnits() - ps.getQuantity();
            product.setAvailableUnits(availableUnits);
            productRepositery.save(product);
        });

    }


//    private void saveSale(SaleDTO saleDTO) {
//        Sale sale = new Sale();
//        sale.setSoldDate(saleDTO.getSoldDate());// set sold date
//        saleRepositery.save(sale);
//
//        //sale detail
//        saleDTO.getProductSold().forEach(ps -> {
//            SaleDetail saleDetail = new SaleDetail();
//            saleDetail.setAmount(null);// set amount
//        });
//    }
//    private void validateSale(SaleDTO saleDTO) {
//        List<Long> productId = saleDTO.getProductSold()
//                        .stream().map(ProductSoldDTO::getProductId)
//                        .toList(); // get all product id from productSoldDTO
//        // validation
//        productId.forEach(productService::getById); // check if product exist
//        List<Product> products = productRepositery.findAllById(productId);// get all product
//        Map<Long , Product> productMap = products.stream()
//                            .collect(Collectors.toMap(Product::getId, Function.identity()));// this function will convert list to map
//
//        //validate Stock
//        saleDTO.getProductSold().forEach(ps -> {
//            Product product = productMap.get(ps.getProductId());// get product from map
//            if (product.getAvailableUnits() < ps.getQuantity()){ // check if product is available
//                throw new ApiException(HttpStatus.BAD_REQUEST,
//                        "Product %s is out of stock".formatted(product.getName()));
//            }
//        });
//
//    }

    @Override
    public Sale getById(Long saleId) {
        return saleRepositery.findById(saleId)
                .orElseThrow(() -> new ResoureNoteFoundException("sale", saleId));
    }

    @Override
    public void cancelSale(Long saleId) {
        //update sale status
        Sale sale = getById(saleId);
        sale.setActive(false);
        saleRepositery.save(sale);

        //update stock
        List<SaleDetail> saleDetail = saleDetailRepositery.findBySaleId(saleId);
        //get all product id from saleDetail
        List<Long> productIds = saleDetail.stream()
                .map(sd -> sd.getProduct().getId())
                .toList();

        //find all product by id from productIds
        List<Product> products = productRepositery.findAllById(productIds);

        //convert list to map
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        //update stock
        saleDetail.forEach(sd -> {
            Product product = productMap.get(sd.getProduct().getId());
            Integer availableUnits = product.getAvailableUnits() + sd.getUnits();
            product.setAvailableUnits(availableUnits);
            productRepositery.save(product);

        });
    }


}
