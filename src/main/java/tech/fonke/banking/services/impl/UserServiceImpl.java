package tech.fonke.banking.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fonke.banking.dto.AccountDto;
import tech.fonke.banking.dto.UserDto;
import tech.fonke.banking.models.User;
import tech.fonke.banking.repositories.AccountRepository;
import tech.fonke.banking.repositories.UserRepository;
import tech.fonke.banking.services.AccountService;
import tech.fonke.banking.services.UserService;
import tech.fonke.banking.validators.ObjectsValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .toList();
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID" + id));
    }

    @Override
    public void delete(Integer id) {
        // to check before delete
        userRepository.findById(id);
    }

    @Override
    @Transactional
    public Integer validationAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

        // Vérifier si l'utilisateur a déjà un compte
        boolean userHasAlreadyAnAccount = accountRepository.findByUserId(user.getId()).isPresent();

        // Si l'utilisateur a déjà un compte et qu'il est actif, activer simplement l'utilisateur
        if (userHasAlreadyAnAccount) {
            user.setActive(true);
            userRepository.save(user);
            return user.getId();
        }
        // créer le compte bancaire
        AccountDto accountDto = AccountDto.builder()
                .userDto(UserDto.fromEntity(user))
                .build();
        accountService.save(accountDto);

        user.setActive(true);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidationAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
}
