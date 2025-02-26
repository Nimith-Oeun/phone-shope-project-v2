package com.unimal.phone_shope_demo.mapper;

import com.unimal.phone_shope_demo.model.Brand;
import com.unimal.phone_shope_demo.model.dto.BrandDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMap {
    BrandMap INSTANCE = Mappers.getMapper(BrandMap.class);
    Brand mapBrandDtoToBrand(BrandDTO brandDto);
    BrandDTO mapToBrandDto(Brand brand);
}
