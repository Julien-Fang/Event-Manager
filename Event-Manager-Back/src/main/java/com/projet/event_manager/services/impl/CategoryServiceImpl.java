package com.projet.event_manager.services.impl;

import com.projet.event_manager.dto.CategoryRequest;
import com.projet.event_manager.exceptions.categories.CategoryNameAlreadyExistsException;
import com.projet.event_manager.exceptions.categories.CategoryNotFoundByIdException;
import com.projet.event_manager.models.Category;
import com.projet.event_manager.repositories.CategoryRepository;
import com.projet.event_manager.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> retrieveAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category retrieveCategoryById(UUID id) throws CategoryNotFoundByIdException {

        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundByIdException(id));
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) throws CategoryNameAlreadyExistsException {
        String categoryName = categoryRequest.getCategoryName();

        if(categoryRepository.findCategoryName(categoryName)!=null){
            throw new CategoryNameAlreadyExistsException(categoryName);
        }

        Category category = new Category(categoryName, categoryRequest.getImagePath());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UUID id, CategoryRequest categoryRequest) throws CategoryNotFoundByIdException, CategoryNameAlreadyExistsException {
        Category category = retrieveCategoryById(id);
        String categoryName = categoryRequest.getCategoryName();

        if(categoryRepository.findCategoryName(categoryName)!=null){
            throw new CategoryNameAlreadyExistsException(categoryName);
        }

        category.setCategoryName(categoryRequest.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(UUID id) throws CategoryNotFoundByIdException {
        retrieveCategoryById(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAllLikeCategoryName(String categoryName) {
        return categoryRepository.findAllLikeCategoryName(categoryName);
    }
}
