package com.licenta.core.services;

import com.google.common.hash.Hashing;
import com.licenta.core.enums.AccountType;
import com.licenta.core.enums.ObjectType;
import com.licenta.core.exceptionHandlers.NotFoundException;
import com.licenta.core.exceptionHandlers.authExceptios.PersonAlreadyExists;
import com.licenta.core.models.Person;
import com.licenta.core.models.createRequestDTO.CreatePersonDTO;
import com.licenta.core.models.responseDTO.PersoneResponseDTO;
import com.licenta.core.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class PersonService {

    private final ModelMapper modelMapper;
    private final PersonRepository personRepository;

    public PersonService(ModelMapper modelMapper, PersonRepository personRepository) {
        this.modelMapper = modelMapper;
        this.personRepository = personRepository;
    }

    @Transactional
    public PersoneResponseDTO createNewPerson(CreatePersonDTO createPersonDTO) {

        if (personRepository.findByEmailAddress(createPersonDTO.getEmailAddress()).isPresent()) {
            throw new PersonAlreadyExists();
        }

        Person person = new Person();
        person.setUsername(createPersonDTO.getUsername());
        person.setEmailAddress(createPersonDTO.getEmailAddress());
        person.setAccountType(AccountType.REGULAR_USER.toString());
        person.setPassword(
                Hashing.
                        sha256().
                        hashString(createPersonDTO.getPassword(), StandardCharsets.UTF_8).toString()
        );

        return modelMapper.map(personRepository.save(person), PersoneResponseDTO.class);
    }

    public Person getPersonByName(String name) {
        Optional<Person> person = personRepository.findByUsername(name);

        return person.orElseThrow(() -> new NotFoundException(ObjectType.PERSON, name));
    }

    public Person getPersonByEmailAddressString(String emailAddress) {
        Optional<Person> person = personRepository.findByEmailAddress(emailAddress);

        return person.orElseThrow(() -> new NotFoundException(ObjectType.PERSON, emailAddress));
    }

    public Boolean findExistingAccount(String emailAddress, String password) {
        return personRepository.existsByEmailAddressAndPassword(
                emailAddress,
                Hashing.
                        sha256().
                        hashString(password, StandardCharsets.UTF_8).toString()
        );
    }
}
