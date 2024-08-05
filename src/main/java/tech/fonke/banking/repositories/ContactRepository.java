package tech.fonke.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fonke.banking.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
