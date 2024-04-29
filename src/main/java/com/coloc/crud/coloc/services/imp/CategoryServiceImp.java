package com.coloc.crud.coloc.services.imp;

import com.coloc.crud.coloc.models.Category;
import com.coloc.crud.coloc.models.FlatShare;
import com.coloc.crud.coloc.repositories.CategoryRepository;
import com.coloc.crud.coloc.repositories.FlatShareRepository;
import com.coloc.crud.coloc.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FlatShareRepository flatShareRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository, FlatShareRepository flatShareRepository) {
        this.categoryRepository = categoryRepository;
        this.flatShareRepository = flatShareRepository;
    }


    public Category createCategory(String name, Long flatShareId) {
        FlatShare flatShare = flatShareRepository.findById(flatShareId).orElse(null);


        Category category = new Category();
        category.setName(name);
        category.setFlatShareCate(flatShare);

        assert flatShare != null;
        flatShare.getCategories().add(category);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getCategoriesByFlatShareId(Long flatShareId) {
        return categoryRepository.findCategoriesByFlatShareId(flatShareId);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
