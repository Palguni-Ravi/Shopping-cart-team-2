package com.example.shoppingcart.MongoDb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.shoppingcart.MongoDb.Model.Brand;
import com.example.shoppingcart.MongoDb.Service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<Brand> addBrand(@RequestBody Brand brand) {
        Brand newBrand = brandService.addBrand(brand);
        return new ResponseEntity<>(newBrand, HttpStatus.CREATED);
    }

}

