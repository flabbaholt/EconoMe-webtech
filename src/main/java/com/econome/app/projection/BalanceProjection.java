package com.econome.app.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BalanceProjection {
    BigDecimal getAmount();
    String getCurrency();
    LocalDate getTransactionDate();
}
