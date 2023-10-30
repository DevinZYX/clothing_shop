package com.cs4125.clothing_shop.Repository;

import com.cs4125.clothing_shop.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {

    Brand findByBrandName(String brandName);
}
