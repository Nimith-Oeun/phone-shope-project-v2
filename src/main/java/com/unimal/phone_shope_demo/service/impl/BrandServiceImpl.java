package com.unimal.phone_shope_demo.service.impl;


import com.unimal.phone_shope_demo.exception.ResoureNoteFoundException;
import com.unimal.phone_shope_demo.jpa_spacifiction.BrandSpac;
import com.unimal.phone_shope_demo.jpa_spacifiction.FilterBrand;
import com.unimal.phone_shope_demo.model.Brand;
import com.unimal.phone_shope_demo.repositery.BrandRepoSitery;
import com.unimal.phone_shope_demo.service.BrandService;
import com.unimal.phone_shope_demo.service.utill.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepoSitery brandRepository;
//    Create Brand
    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

//    Get Brand
    @Override
    public Brand getById(Long id) {
//        return brandRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with Id:"+ id+"Brand not found"));//function java8 after create Globle exception
        return brandRepository.findById(id).orElseThrow(()-> new ResoureNoteFoundException("Brand",id)); //function java8 after create Globle NotFoundException for call
    }
    /*
    + use this functin befor create Jpaspecification,
      we need to create 2 different function for filter & getAllBrand

        @Override
        public List<Brand> getAllBrand() {
            return brandRepository.findAll();
        }
        @Override
        public List<Brand> getBrand(String name) {
            return brandRepository.findByNameIgnoreCase(name);
        }

     */

    /*
    @Override
    public List<Brand> getBrand(Map<String, String> param) { // use this for Filter and getAllBrand without pageination in 1 function with JpaSpecification
        FilterBrand filterBrand = new FilterBrand();
        if(param.containsKey("name")){
            String name = param.get("name");
            filterBrand.setName(name);
        }
        if (param.containsKey("id")) {
            String id = param.get("id");
            filterBrand.setId(Long.parseInt(id));
        }
        BrandSpac brandSpac = new BrandSpac(filterBrand);
        return brandRepository.findAll(brandSpac);
    }
     */
    @Override
    public Page<Brand> getBrand(Map<String, String> param) { // use this for Filter,getAllBrand,pageination in 1 function with JpaSpecification
        FilterBrand filterBrand = new FilterBrand();
        int pageNumber = Pagination.DEFAULT_PAGE_NUMBER;
        int pageLimit = Pagination.DEFAULT_PAGE_LIMIT;
        if (param.containsKey("name")) {
            String name = param.get("name");
            filterBrand.setName(name);
        }
        if (param.containsKey("id")) {
            String id = param.get("id");
            filterBrand.setId(Integer.parseInt(id));
        }

        //Pagination @TODO: need to emprove code to add new function for pageable
        if (param.containsKey(Pagination.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(param.get(Pagination.PAGE_NUMBER));
        }
        if (param.containsKey(Pagination.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(param.get(Pagination.PAGE_LIMIT));
        }

        BrandSpac brandSpac = new BrandSpac(filterBrand);
        Pageable pageable = Pagination.getPageable(pageNumber,pageLimit);
        return brandRepository.findAll(brandSpac , pageable);
    }


//    Update Brand
    @Override
    public Brand update(Long id, Brand brandUpdate) {
        Brand brand = getById(id);
        brand.setName(brandUpdate.getName());
        return brandRepository.save(brand);
    }


//    Delete Brand
    @Override
    public void delete(Long id) {
        Brand brand = getById(id);
        brandRepository.delete(brand);
    }


}
