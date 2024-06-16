package com.econome.app.service;

import com.econome.app.model.Currency;
import com.econome.app.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCurrencyById() {
        Currency currency = new Currency();
        currency.setId(1L);
        currency.setName("USD");

        when(currencyRepository.findById(1L)).thenReturn(Optional.of(currency));

        Currency result = currencyService.get(1L);
        assertEquals("USD", result.getName());
    }
}
