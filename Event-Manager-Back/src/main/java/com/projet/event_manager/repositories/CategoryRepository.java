package com.projet.event_manager.repositories;

import com.projet.event_manager.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository  extends JpaRepository<Category, UUID> {
    @Query("""
    SELECT category
    FROM Category category
    WHERE UPPER(category.categoryName) LIKE UPPER(CONCAT('%' ,:categoryName,'%'))
    """)
    List<Category> findAllLikeCategoryName(@Param("categoryName") String categoryName);

    @Query("""
    SELECT category
    FROM Category category
    WHERE UPPER(trim( category.categoryName)) LIKE UPPER(trim(:categoryName))
    """)
    Category findCategoryName(@Param("categoryName") String categoryName);
}
