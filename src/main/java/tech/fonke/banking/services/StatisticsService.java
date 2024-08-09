package tech.fonke.banking.services;

import tech.fonke.banking.dto.TansactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticsService {

    List<TansactionSumDetails> findSumTransactionByDate(LocalDate stratDate, LocalDate endDate, Integer userId);
    BigDecimal getAccountBalance(Integer userId);
    BigDecimal highestTransfer(Integer userId);
    BigDecimal highestDeposit(Integer userId);


}
