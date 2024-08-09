package tech.fonke.banking.dto;


import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tech.fonke.banking.models.Transaction;
import tech.fonke.banking.models.TransactionType;
import tech.fonke.banking.models.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Integer id;

    @Positive
    private BigDecimal amount;


    private TransactionType type;

    private String destinationIban;

    private LocalDate transactionDate;

    private Integer userId;

    public static TransactionDto fromEntity(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .transactionDate(transaction.getTransactionDate())
                .userId(transaction.getUser().getId())
                .build();
    }

    public static Transaction toEntity(TransactionDto transactionDto) {
        return Transaction.builder()
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount())
                .type(transactionDto.getType())
                .destinationIban(transactionDto.getDestinationIban())
                .transactionDate(LocalDate.now())
                .user(
                        User.builder()
                                .id(transactionDto.getUserId())
                                .build()
                )
                .build();
    }
}
