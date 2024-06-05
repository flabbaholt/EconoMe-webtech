package com.econome.app.controller;

import com.econome.app.model.Category;
import com.econome.app.model.Currency;
import com.econome.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.get(id);
    }

    @GetMapping
    public List<Category> getCategory() {
        return categoryService.getAll();
    }
}