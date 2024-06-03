package com.econome.app.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransactionProjection {

    Long getId();
    String getName();
    BigDecimal getAmount();
    LocalDate getTransactionDate();
    String getCategoryName();
    String getPaymentMethodName();
    String getTypeName();
    String getCurrencyName();
}
