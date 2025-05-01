package com.unimal.phone_shope_demo.mapper;

import com.unimal.phone_shope_demo.model.Model;
import com.unimal.phone_shope_demo.model.dto.ModelDTO;
import com.unimal.phone_shope_demo.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",uses = {BrandService.class})
public interface ModelEntityMapper {
    ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);
    @Mapping(target = "brand", source = "brandId")
    Model mapToModel(ModelDTO modelDto);
    @Mapping(target = "brandId", source = "brand.id")
    ModelDTO mapToModelDTO(Model model);

//    default Brand toBrand(Integer brandId) {
//        Brand brand = new Brand();
//        brand.setId(brandId);
//        return brand;
//    }
}
