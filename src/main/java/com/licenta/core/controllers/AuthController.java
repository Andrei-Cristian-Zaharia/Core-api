package com.licenta.core.controllers;

import com.licenta.core.models.LoginDTO;
import com.licenta.core.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/test")
    public @ResponseBody ResponseEntity<Boolean> validateToken(@RequestBody String token) {
        Boolean result = authService.validateToken(token);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<String> requestToken(@RequestBody LoginDTO loginDTO) {
        String result = authService.createToken(loginDTO);

        return ResponseEntity.ok().body(result);
    }
}
