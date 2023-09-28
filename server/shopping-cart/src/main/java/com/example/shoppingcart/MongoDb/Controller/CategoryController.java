package com.example.shoppingcart.MongoDb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingcart.MongoDb.Model.Category;
import com.example.shoppingcart.MongoDb.Service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public ResponseEntity<Category> addCategory(@RequestBody Category categoryData) {
    Category newCategory = categoryService.addCategory(categoryData);
    return ResponseEntity.ok(newCategory);
  }
}