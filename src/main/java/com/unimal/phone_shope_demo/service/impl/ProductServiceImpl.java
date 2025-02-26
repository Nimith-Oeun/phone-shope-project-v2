package com.unimal.phone_shope_demo.service.impl;

import com.unimal.phone_shope_demo.exception.ApiException;
import com.unimal.phone_shope_demo.exception.ResoureNoteFoundException;
import com.unimal.phone_shope_demo.mapper.ProductMapper;
import com.unimal.phone_shope_demo.model.Product;
import com.unimal.phone_shope_demo.model.ProductImportHistory;
import com.unimal.phone_shope_demo.model.dto.ProductImportDTO;
import com.unimal.phone_shope_demo.repositery.ProductImportRepositery;
import com.unimal.phone_shope_demo.repositery.ProductRepositery;
import com.unimal.phone_shope_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepositery productRepositery;
    private final ProductImportRepositery productImportRepositery;
    private final ProductMapper productMapper;
    @Override
    public Product create(Product product) {
        String name = "%s %s"
                    .formatted(product.getModel().getName(),product.getColor().getColorName());
        product.setName(name);
        return productRepositery.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepositery.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Product", id));
    }

    @Override
    public void importProduct(ProductImportDTO importDTO) {
        // update avilableunit to product
        Product product = getById(importDTO.getProductId());
        Integer availableUnits = 0;
        if (product.getAvailableUnits() !=null){
            availableUnits = product.getAvailableUnits();
        }
        product.setAvailableUnits(availableUnits + importDTO.getImportedUnits());
        productRepositery.save(product);
        // save product import history
        ProductImportHistory productImportHistory = productMapper.mapToProductImport(importDTO, product);
        productImportRepositery.save(productImportHistory);
    }

    @Override
    public void setPrice(Long broductId, BigDecimal price) {
        Product product = getById(broductId);
        product.setSalePrice(price);
        productRepositery.save(product);
    }

    @Override
    public Map<Integer,String> uploadProduct(MultipartFile file) {
        Map<Integer,String> map = new HashMap<>();

        try {
            Workbook workbok = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbok.getSheet("Book1");// get sheet
            Iterator<Row> rowIterator = sheet.iterator();// get all row

            rowIterator.next();// skip header

            while (rowIterator.hasNext()){ // loop all row
                Integer rowNumber = 0;

                try{
                    Row row = rowIterator.next();// skip header
                    int cellIndex = 0;

                    Cell cellNo = row.getCell(cellIndex++);// get cell 1
                    rowNumber = (int) cellNo.getNumericCellValue();

                    Cell cellModelId = row.getCell(cellIndex++);// get cell 1
                    Long modelId = (long) cellModelId.getNumericCellValue();
                    Cell cellColor = row.getCell(cellIndex++);// get cell 2
                    Long colorId = (long) cellColor.getNumericCellValue();

                    Cell cellImportUnits = row.getCell(cellIndex++);// get cell 3
                    Integer importUnit = (int) cellImportUnits.getNumericCellValue();

                    Cell cellPrice = row.getCell(cellIndex++);// get cell 4
                    double price = cellPrice.getNumericCellValue();

                    Cell cellDate = row.getCell(cellIndex++);// get cell 5
                    LocalDateTime importDate = cellDate.getLocalDateTimeCellValue();

                    Product product = getByModelIdAndColorId(modelId, colorId);
                    Integer availableUnit = 0;
                    if (product.getAvailableUnits() != null) {
                        availableUnit = product.getAvailableUnits();
                    }
                    product.setAvailableUnits(availableUnit + importUnit);
                    productRepositery.save(product);
                    // save product import history
                    ProductImportHistory importHistory = new ProductImportHistory();
                    importHistory.setImportDate(importDate);
                    importHistory.setImportedUnits(importUnit);
                    importHistory.setPricePerUnit(BigDecimal.valueOf(price));
                    importHistory.setProduct(product);
                    productImportRepositery.save(importHistory);
                }catch (Exception e){
                    map.put(rowNumber, e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        String name = "Product with model id %s and color id = %d was not found";
        return productRepositery.findByModelIdAndColorId(modelId, colorId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST,name.formatted(modelId, colorId)));
    }
}
