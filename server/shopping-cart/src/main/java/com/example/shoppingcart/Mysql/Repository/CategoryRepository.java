package com.example.shoppingcart.Mysql.Repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppingcart.Mysql.Model.Category;


@Profile("mysql")
public interface CategoryRepository extends JpaRepository<Category , Integer>{

	Category findByName(String categoryName);
}
