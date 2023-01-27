package com.licenta.core.services;

import com.licenta.core.ApiConfig;
import com.licenta.core.models.Restaurant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class RestaurantRestTemplateService {

    private static final String RESTAURANT_ROUTE = "/restaurant/";

    private final RestTemplate restTemplate;

    public RestaurantRestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Restaurant getRestaurantById(Long id) {
        URI uri = ApiConfig.restaurantApiPath()
                .path(RESTAURANT_ROUTE)
                .path("id/{id}")
                .build(id);

        HttpEntity<String> entityCredentials = new HttpEntity<>(null, createHeaderBody());

        return restTemplate.exchange(uri, HttpMethod.GET, entityCredentials, Restaurant.class).getBody();
    }

    private static HttpHeaders createHeaderBody() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.add("authorities", "[PERMIT]");

        return httpHeaders;
    }
}
