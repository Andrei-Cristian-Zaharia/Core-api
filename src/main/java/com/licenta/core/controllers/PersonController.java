package com.licenta.core.controllers;

import com.licenta.core.models.*;
import com.licenta.core.models.createRequestDTO.CreatePersonDTO;
import com.licenta.core.models.responseDTO.PersonResponseDTO;
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
    public @ResponseBody ResponseEntity<PersonResponseDTO> createNewPerson(@RequestBody CreatePersonDTO createPersonDTO) {

        PersonResponseDTO person = personService.createNewPerson(createPersonDTO);

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
    public @ResponseBody ResponseEntity<Long> validateAccount(@RequestBody ConfirmAccountDTO confirmAccountDTO) {

        return ResponseEntity.ok().body(
                personService.validatePersonAccount(
                        confirmAccountDTO.getEmailAddress(),
                        confirmAccountDTO.getPassword())
        );
    }

    @PostMapping("/updateRestaurantStatus")
    public void updatePersonHasRestaurantStatus(@RequestBody UpdatePersonHasRestaurantStatusDTO req) {
        personService.updateRestaurantStatus(req);
    }

    @DeleteMapping("/delete")
    public @ResponseBody ResponseEntity<String> deletePerson(@RequestParam Long id) {
        personService.deleteById(id);

        return ResponseEntity.ok().body("Person deleted.");
    }
}
