package tech.fonke.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fonke.banking.models.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
