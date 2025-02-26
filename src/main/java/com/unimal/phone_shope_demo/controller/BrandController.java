package com.unimal.phone_shope_demo.controller;


import com.unimal.phone_shope_demo.mapper.BrandMap;
import com.unimal.phone_shope_demo.mapper.ModelEntityMapper;
import com.unimal.phone_shope_demo.model.Brand;
import com.unimal.phone_shope_demo.model.Model;
import com.unimal.phone_shope_demo.model.dto.BrandDTO;
import com.unimal.phone_shope_demo.model.dto.ModelDTO;
import com.unimal.phone_shope_demo.model.dto.PageDTO;
import com.unimal.phone_shope_demo.service.BrandService;
import com.unimal.phone_shope_demo.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    private final ModelService modelService;
    private final ModelEntityMapper modelMapper;

    @PostMapping("")
    public ResponseEntity<?>create(@RequestBody BrandDTO brandto){
        Brand createBrand = BrandMap.INSTANCE.mapBrandDtoToBrand(brandto);
        Brand brandAfterMap=brandService.create(createBrand);
        return ResponseEntity.ok(BrandMap.INSTANCE.mapToBrandDto(brandAfterMap));
    }
    /*
      +use this functin befor create Jpaspecification,
       we need to create 2 different function for filter & getAllBrand

  - Get all function
    @GetMapping("")
    public ResponseEntity<?>getAll(){
        List<BrandDTO> ListBrandDto = brandService.getAllBrand()
                .stream().map(BrandMap.INSTANCE::mapToBrandDto)
                .toList();
        return ResponseEntity.ok(ListBrandDto);
    }

  - Fillter function
     @GetMapping("/filter")
    public ResponseEntity<?>getBrand(@RequestParam("name") String name){
        List<BrandDTO> ListBrandDto = brandService.getBrand(name)
                .stream().map(BrandMap.INSTANCE::mapToBrandDto)
                .toList();
        return ResponseEntity.ok(ListBrandDto);
    }

     */
    @GetMapping("") // use this for Filter and getAllBrand with pageination in 1 function with JpaSpecification
    public ResponseEntity<?>getBrand(@RequestParam Map<String,String> param){
        /*  this sataement use for filter and getAllBrand without pageination
        List<BrandDTO> ListBrandDto = brandService.getBrand(param)
                .stream().map(BrandMap.INSTANCE::mapToBrandDto)
                .toList();

         */
        Page<Brand> brand = brandService.getBrand(param);
        PageDTO pageDTO = new PageDTO(brand);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable("id") Long id){
        Brand getbrand = brandService.getById(id);
        return ResponseEntity.ok(BrandMap.INSTANCE.mapToBrandDto(getbrand));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>update(@PathVariable("id") Long id , @RequestBody BrandDTO brandto){
        Brand brand = BrandMap.INSTANCE.mapBrandDtoToBrand(brandto);
        Brand updatedBrand = brandService.update(id, brand);
        return ResponseEntity.ok(BrandMap.INSTANCE.mapToBrandDto(updatedBrand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") Long id){
        brandService.delete(id);
        return ResponseEntity.ok("Delete ID: "+ id+ " Success" );
    }

    @GetMapping("/{id}/models")
    public ResponseEntity<?>getModel(@PathVariable("id") Long brandid){
        List<Model> brands = modelService.getByBrand(brandid);
        List<ModelDTO> modelDTOList = brands.stream()
                        .map(modelMapper::mapToModelDTO)
                        .toList();
        return ResponseEntity.ok(modelDTOList);
    }
}
