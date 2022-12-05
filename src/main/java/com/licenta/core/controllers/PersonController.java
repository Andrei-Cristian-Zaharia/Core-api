package com.licenta.core.controllers;

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

    @GetMapping
    public @ResponseBody ResponseEntity<Person> getPersonByEmailAddress(@RequestParam String emailAddress) {
        Person person = personService.getPersonByEmailAddressString(emailAddress);

        return ResponseEntity.ok().body(person);
    }
}
