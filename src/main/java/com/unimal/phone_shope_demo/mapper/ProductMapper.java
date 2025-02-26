package com.unimal.phone_shope_demo.mapper;

import com.unimal.phone_shope_demo.model.Product;
import com.unimal.phone_shope_demo.model.ProductImportHistory;
import com.unimal.phone_shope_demo.model.dto.ProductDTO;
import com.unimal.phone_shope_demo.model.dto.ProductImportDTO;
import com.unimal.phone_shope_demo.service.ColorService;
import com.unimal.phone_shope_demo.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product mapToProduct(ProductDTO productDto);

    @Mapping(target = "importDate", source = "importDTO.importDate")
    @Mapping(target = "pricePerUnit", source = "importDTO.pricePerUnit")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "id", ignore = true)
    ProductImportHistory mapToProductImport(ProductImportDTO importDTO,Product product);
}
