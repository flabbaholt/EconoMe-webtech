package com.econome.app.controller;

import com.econome.app.model.Category;
import com.econome.app.model.Currency;
import com.econome.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a REST controller for managing categories.
 * It provides endpoints for creating, retrieving a single category and retrieving all categories.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    /**
     * The service that provides operations for categories.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Creates a new category.
     *
     * @param category The category to create.
     * @return The created category.
     */
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    /**
     * Retrieves a single category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The retrieved category.
     */
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.get(id);
    }

    /**
     * Retrieves all categories.
     *
     * @return A list of all categories.
     */
    @GetMapping
    public List<Category> getCategory() {
        return categoryService.getAll();
    }
}