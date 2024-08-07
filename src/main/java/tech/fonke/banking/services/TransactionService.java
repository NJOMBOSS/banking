package tech.fonke.banking.services;

import tech.fonke.banking.dto.TransactionDto;


import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto> {

    List<TransactionDto> findAllByUserId(Integer userId);
}
