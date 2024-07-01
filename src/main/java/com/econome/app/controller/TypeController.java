package com.econome.app.controller;

import com.econome.app.model.Type;
import com.econome.app.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This is a REST controller for managing types.
 * It provides an endpoint for retrieving a single type by its ID.
 */
@RestController
@RequestMapping("/types")
public class TypeController {

    /**
     * The service that provides operations for types.
     */
    @Autowired
    private TypeService typeService;

    /**
     * Retrieves a single type by its ID.
     *
     * @param id The ID of the type to retrieve.
     * @return The retrieved type.
     */
    @GetMapping("/{id}")
    public Type getType(@PathVariable Long id) {
        return typeService.get(id);
    }
}