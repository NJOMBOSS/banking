package tech.fonke.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fonke.banking.models.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByIban(String iban);

    Optional<Account> findByUserId(Integer id);
}
