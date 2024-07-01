package com.econome.app.service;

import com.econome.app.model.Type;
import com.econome.app.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is a service class for managing types.
 * It provides a method for retrieving a single type by its ID.
 */
@Service
public class TypeService {

    /**
     * The repository that provides CRUD operations for types.
     */
    @Autowired
    private TypeRepository typeRepository;

    /**
     * Retrieves a single type by its ID.
     *
     * @param id The ID of the type to retrieve.
     * @return The retrieved type, or null if no type with the given ID exists.
     */
    public Type get(Long id) {
        Optional<Type> type = typeRepository.findById(id);
        return type.orElse(null);
    }
}