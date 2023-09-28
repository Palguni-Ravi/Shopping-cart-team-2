package com.example.shoppingcart.MongoDb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingcart.MongoDb.Model.Category;
import com.example.shoppingcart.MongoDb.Repository.CategoryRepository;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public Category addCategory(Category categoryData) {
    // You can add any necessary validation or processing here
    return categoryRepository.save(categoryData);
  }
}