package com.econome.app.service;

import com.econome.app.model.Category;
import com.econome.app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a service class for managing categories.
 * It provides methods for saving, retrieving a single category by its ID, and retrieving all categories.
 */
@Service
public class CategoryService {

    /**
     * The repository that provides CRUD operations for categories.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Saves a category to the database.
     *
     * @param category The category to save.
     * @return The saved category.
     */
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Retrieves a single category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The retrieved category, or null if no category with the given ID exists.
     */
    public Category get(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return A list of all categories.
     */
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}