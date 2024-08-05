package tech.fonke.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fonke.banking.models.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
