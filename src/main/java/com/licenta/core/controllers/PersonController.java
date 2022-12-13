package com.licenta.core.controllers;

import com.licenta.core.models.ChangePasswordDTO;
import com.licenta.core.models.ConfirmAccountDTO;
import com.licenta.core.models.DeleteFormDTO;
import com.licenta.core.models.Person;
import com.licenta.core.models.createRequestDTO.CreatePersonDTO;
import com.licenta.core.models.responseDTO.PersoneResponseDTO;
import com.licenta.core.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<PersoneResponseDTO> createNewPerson(@RequestBody CreatePersonDTO createPersonDTO) {

        PersoneResponseDTO person = personService.createNewPerson(createPersonDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @GetMapping("/email")
    public @ResponseBody ResponseEntity<Person> getPersonByEmailAddress(@RequestParam String emailAddress) {
        Person person = personService.getPersonByEmailAddressString(emailAddress);

        return ResponseEntity.ok().body(person);
    }

    @GetMapping("/id/{id}")
    public @ResponseBody ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);

        return ResponseEntity.ok().body(person);
    }

    @GetMapping("/name/{name}")
    public @ResponseBody ResponseEntity<Person> getPersonByName(@PathVariable String name) {
        Person person = personService.getPersonByName(name);

        return ResponseEntity.ok().body(person);
    }


    @PostMapping("/reset")
    public @ResponseBody ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {

        personService.resetPassword(changePasswordDTO);

        return ResponseEntity.ok().body("Password changed !");
    }

    @DeleteMapping("/deleteSelf")
    public @ResponseBody ResponseEntity<String> deleteSelfPerson(@RequestBody DeleteFormDTO deleteFormDTO) {
        personService.deleteSelfPerson(deleteFormDTO);

        return ResponseEntity.ok().body("Person deleted.");
    }

    @PostMapping("/validate")
    public @ResponseBody ResponseEntity<Boolean> validateAccount(@RequestBody ConfirmAccountDTO confirmAccountDTO) {

        return ResponseEntity.ok().body(
                personService.validatePersonAccount(
                        confirmAccountDTO.getEmailAddress(),
                        confirmAccountDTO.getPassword())
        );
    }

    @PostMapping("/updatePerson")
    public @ResponseBody ResponseEntity<Boolean> updatePersonStatus(@RequestBody ConfirmAccountDTO confirmAccountDTO) {

        return ResponseEntity.ok().body(
                personService.validatePersonAccount(
                        confirmAccountDTO.getEmailAddress(),
                        confirmAccountDTO.getPassword())
        );
    }

    @DeleteMapping("/delete")
    public @ResponseBody ResponseEntity<String> deletePerson(@RequestParam Long id) {
        personService.deleteById(id);

        return ResponseEntity.ok().body("Person deleted.");
    }
}
