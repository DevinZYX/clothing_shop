package com.cs4125.clothing_shop.Controller;

import com.cs4125.clothing_shop.Config.ApiResponse;
import com.cs4125.clothing_shop.Model.Brand;
import com.cs4125.clothing_shop.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/list")
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> list = brandService.listBrands();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBrand(@Valid @RequestBody Brand brand) {

        //check if the brand name is already existed
        if (Objects.nonNull(brandService.readBrand(brand.getBrandName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "brand already exists"), HttpStatus.CONFLICT);
        }
        brandService.createBrand(brand);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created new brand"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{brandId}")
    public ResponseEntity<ApiResponse> updateBrand(@PathVariable("brandId") Integer brandId, @Valid @RequestBody Brand brand) {

        //check if the brand exists
        if (Objects.nonNull(brandService.readBrand(brandId))){
            brandService.updateBrand(brandId, brand);
        }

        //return a error message if not exist
        return new ResponseEntity<ApiResponse>(new ApiResponse(false, "brand does not exist"), HttpStatus.NOT_FOUND);
    }
}
