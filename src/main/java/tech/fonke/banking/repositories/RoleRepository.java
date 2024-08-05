package tech.fonke.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fonke.banking.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
