package com.licenta.core.services;

import com.google.common.hash.Hashing;
import com.licenta.core.enums.AccountType;
import com.licenta.core.enums.ObjectType;
import com.licenta.core.exceptionHandlers.NotFoundException;
import com.licenta.core.exceptionHandlers.authExceptios.FailedAuth;
import com.licenta.core.exceptionHandlers.authExceptios.PersonAlreadyExists;
import com.licenta.core.models.ChangePasswordDTO;
import com.licenta.core.models.DeleteFormDTO;
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

    public Person getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);

        return person.orElseThrow(() -> new NotFoundException(ObjectType.PERSON, id));
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

    public void resetPassword(ChangePasswordDTO changePasswordDTO) {

        if (Boolean.FALSE.equals(
                validatePersonAccount(changePasswordDTO.getEmailAddress(), changePasswordDTO.getOldPassword()))) {
            return;
        }

        Person person = getPersonByEmailAddressString(changePasswordDTO.getEmailAddress());
        person.setPassword(
                Hashing.
                        sha256().
                        hashString(changePasswordDTO.getNewPassword(), StandardCharsets.UTF_8).toString()
        );

        personRepository.save(person);
    }

    public void deleteSelfPerson(DeleteFormDTO deleteFormDTO) {

        if (Boolean.FALSE.equals(
                validatePersonAccount(deleteFormDTO.getEmailAddress(), deleteFormDTO.getPassword()))) {
            return;
        }

        Person person = getPersonByEmailAddressString(deleteFormDTO.getEmailAddress());
        personRepository.delete(person);
    }

    public Boolean updatePersonStatus(String status) {

        return true;
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public Boolean validatePersonAccount(String email, String password) {

        if (Boolean.TRUE.equals(findExistingAccount(email, password))) {
            return true;
        } else {
            throw new FailedAuth();
        }
    }
}
