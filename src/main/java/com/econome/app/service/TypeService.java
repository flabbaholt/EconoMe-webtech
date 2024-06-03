package com.econome.app.service;

import com.econome.app.model.Type;
import com.econome.app.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Type get(Long id) {
        Optional<Type> type = typeRepository.findById(id);
        return type.orElse(null);
    }
}