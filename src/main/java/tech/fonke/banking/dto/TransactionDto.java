package tech.fonke.banking.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tech.fonke.banking.models.Transaction;
import tech.fonke.banking.models.TransactionType;
import tech.fonke.banking.models.User;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Integer id;

    private BigDecimal amount;


    private TransactionType type;

    private String destinationIban;

    private Integer userId;

    private static TransactionDto fromEntity(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .userId(transaction.getUser().getId())
                .build();
    }

    private static Transaction toEntity(TransactionDto transactionDto) {
        return Transaction.builder()
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount())
                .type(transactionDto.getType())
                .destinationIban(transactionDto.getDestinationIban())
                .user(
                        User.builder()
                                .id(transactionDto.getUserId())
                                .build()
                )
                .build();
    }
}
