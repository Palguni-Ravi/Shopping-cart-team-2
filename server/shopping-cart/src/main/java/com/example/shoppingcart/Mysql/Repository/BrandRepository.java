package com.example.shoppingcart.Mysql.Repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppingcart.Mysql.Model.Brands;

@Profile("mysql")
public interface BrandRepository extends JpaRepository<Brands , Integer>{

	Brands findByName(String brandName);
}
