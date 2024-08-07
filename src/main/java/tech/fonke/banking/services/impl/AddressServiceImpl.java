package tech.fonke.banking.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fonke.banking.dto.AddressDto;
import tech.fonke.banking.models.Address;
import tech.fonke.banking.repositories.AddressRepository;
import tech.fonke.banking.services.AddressService;
import tech.fonke.banking.validators.ObjectsValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address= AddressDto.toEntity(dto);
        return addressRepository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .toList();
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No address was found with the ID: " + id));
    }

    @Override
    public void delete(Integer id) {
         // todo check delete
        addressRepository.deleteById(id);
    }
}
