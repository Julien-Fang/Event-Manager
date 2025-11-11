package com.projet.event_manager.services;

import com.projet.event_manager.dto.CategoryRequest;
import com.projet.event_manager.exceptions.categories.CategoryNameAlreadyExistsException;
import com.projet.event_manager.exceptions.categories.CategoryNotFoundByIdException;
import com.projet.event_manager.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> retrieveAllCategories();
    Category retrieveCategoryById(UUID id) throws CategoryNotFoundByIdException;
    Category createCategory(CategoryRequest categoryRequest) throws CategoryNameAlreadyExistsException, CategoryNameAlreadyExistsException;
    Category updateCategory(UUID id, CategoryRequest categoryRequest) throws CategoryNotFoundByIdException, CategoryNameAlreadyExistsException, CategoryNotFoundByIdException;
    void deleteCategoryById(UUID id) throws CategoryNotFoundByIdException;
    List<Category> findAllLikeCategoryName(String categoryName);
}
