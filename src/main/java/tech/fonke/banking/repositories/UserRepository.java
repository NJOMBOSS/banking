package tech.fonke.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fonke.banking.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
