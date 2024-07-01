package com.econome.app.service;

import com.econome.app.model.Type;
import com.econome.app.repository.TypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TypeServiceTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeService typeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTypeById() {
        Type type = new Type();
        type.setId(1L);
        type.setName("Expense");

        when(typeRepository.findById(1L)).thenReturn(Optional.of(type));

        Type result = typeService.get(1L);
        assertEquals("Expense", result.getName());
    }
}
