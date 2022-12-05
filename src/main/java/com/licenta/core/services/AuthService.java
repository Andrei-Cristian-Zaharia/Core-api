package com.licenta.core.services;

import com.licenta.core.exceptionHandlers.authExceptios.FailedAuth;
import com.licenta.core.models.LoginDTO;
import com.licenta.core.models.Person;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AuthService {

    private final PersonService personService;

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public AuthService(PersonService personService) {
        this.personService = personService;
    }

    public String createToken(LoginDTO loginDTO) {

        if (Boolean.TRUE.equals(personService.findExistingAccount(loginDTO.getEmailAddress(), loginDTO.getPassword()))) {

            Person person = personService.getPersonByEmailAddressString(loginDTO.getEmailAddress());

            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(person.getAccountType());

            return Jwts.builder()
                    .setIssuer(loginDTO.getEmailAddress())
                    .setSubject(loginDTO.getEmailAddress() + loginDTO.getPassword())
                    .setIssuedAt(new Date())
                    .claim("authorities",
                            grantedAuthorities.stream()
                                    .map(GrantedAuthority::getAuthority).toList())
                    .signWith(key)
                    .compact();
        } else {
            throw new FailedAuth();
        }
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            return true;
        } catch (JwtException e) {

            log.error(e.getMessage());
        }

        return false;
    }

    public Key getKey() {
        return key;
    }
}
