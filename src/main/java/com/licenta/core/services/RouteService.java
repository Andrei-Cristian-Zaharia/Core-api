package com.licenta.core.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class RouteService {

    public ResponseEntity<byte[]> routeRequest(HttpServletRequest request, HttpServletResponse response) {

    }
}
