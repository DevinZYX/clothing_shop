package com.cs4125.clothing_shop.Service;
import com.cs4125.clothing_shop.Model.Category;
import com.cs4125.clothing_shop.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> listCategories() {
        return categoryRepo.findAll();
    }

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public Category readCategory(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return categoryRepo.findById(categoryId);
    }

    public void updateCategory(Integer categoryID, Category newCategory) {
        Category category = categoryRepo.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepo.save(category);
    }
}
