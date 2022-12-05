package com.licenta.core.controllers;

import com.licenta.core.services.RouteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private final RouteService routeService;

    public MainController(RouteService routeService) {
        this.routeService = routeService;
    }

    @CrossOrigin(origins = {"*"}, methods = {
            RequestMethod.GET,
            RequestMethod.POST,
            RequestMethod.DELETE
    })
    @RequestMapping(value = {"/**"})
    public ResponseEntity<byte[]> routeTo(HttpServletRequest request, HttpServletResponse response) {
        return routeService.routeRequest(request, response);
    }
}
