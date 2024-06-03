package com.econome.app.controller;

import com.econome.app.model.Type;
import com.econome.app.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/{id}")
    public Type getType(@PathVariable Long id) {
        return typeService.get(id);
    }
}