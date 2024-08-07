package tech.fonke.banking.services;

import tech.fonke.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {

    List<ContactDto> findAllByUserId(Integer userId);
}
