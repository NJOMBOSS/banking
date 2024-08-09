package tech.fonke.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TansactionSumDetails {

    LocalDate getTransactionDate();

    BigDecimal getAmount();
}
