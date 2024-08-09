package tech.fonke.banking.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;
import tech.fonke.banking.dto.AccountDto;
import tech.fonke.banking.exceptions.OperationNonPermittedException;
import tech.fonke.banking.models.Account;
import tech.fonke.banking.repositories.AccountRepository;
import tech.fonke.banking.services.AccountService;
import tech.fonke.banking.validators.ObjectsValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAnAccount && account.getUser().isActive()) {
            throw new OperationNonPermittedException(
                    "The selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        if (dto.getId() == null) {
            account.setIban(generateRandomIban());
        }
        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .toList();
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with the ID:" + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete account
        accountRepository.deleteById(id);
    }

    private String generateRandomIban() {
        //Générer un iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();
        //Vérifier si l'iban existe déjà
        boolean ibanExists = accountRepository.findByIban(iban).isPresent();
        //S'il existe alors générer un nouveau iban
        if (ibanExists) {
            generateRandomIban();
        }
        // S'il n'existe pas, alros retourner l'iban
        return iban;
    }
}
