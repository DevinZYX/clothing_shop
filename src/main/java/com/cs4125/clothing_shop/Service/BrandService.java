package com.cs4125.clothing_shop.Service;


import com.cs4125.clothing_shop.Model.Brand;
import com.cs4125.clothing_shop.Repository.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepo brandRepo;

    public List<Brand> listBrands(){
        return brandRepo.findAll();
    }

    public void createBrand(Brand brand){
        brandRepo.save(brand);
    }

    public Brand readBrand(String brandName){
        return brandRepo.findByBrandName(brandName);
    }

    public Optional<Brand> readBrand(Integer brandId){
        return brandRepo.findById(brandId);
    }

    public void updateBrand(Integer brandId, Brand newBrand){
        Brand brand = brandRepo.findById(brandId).get();
        brand.setBrandName(newBrand.getBrandName());
        brand.setDescription(newBrand.getDescription());
        brand.setImageUrl(newBrand.getImageUrl());
        brandRepo.save(brand);
    }


}
