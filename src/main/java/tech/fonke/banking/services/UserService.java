package tech.fonke.banking.services;

import tech.fonke.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {

    Integer validationAccount(Integer id);

    Integer invalidationAccount(Integer id);
}
