package com.licenta.core.repositories;

import com.licenta.core.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);
    Optional<Person> findByEmailAddress(String emailAddress);
    Boolean existsByEmailAddressAndPassword(String emailAddress, String password);
}
