package com.coloc.crud.coloc.services;

import com.coloc.crud.coloc.models.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(String name, Long flatShareId);
    Category getCategoryById(Long id);
    List<Category> getCategoriesByFlatShareId(Long flatShareId);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
