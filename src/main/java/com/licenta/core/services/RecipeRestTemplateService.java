package com.licenta.core.services;

import com.licenta.core.ApiConfig;
import com.licenta.core.models.Recipe;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class RecipeRestTemplateService {

    private static final String RECIPE_ROUTE = "/recipe/";

    private final RestTemplate restTemplate;

    public RecipeRestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Recipe getRecipeById(Long id) {
        URI uri = ApiConfig.foodApiPath()
                .path(RECIPE_ROUTE)
                .path("findById")
                .queryParam("id", id)
                .build(id);

        HttpEntity<String> entityCredentials = new HttpEntity<>(null, createHeaderBody());

        return restTemplate.exchange(uri, HttpMethod.GET, entityCredentials, Recipe.class).getBody();
    }

    public List<Recipe> getRecipeByOwnerUsername(String username) {
        URI uri = ApiConfig.foodApiPath()
                .path(RECIPE_ROUTE)
                .path("all/")
                .path("owner/")
                .path("username")
                .queryParam("name", username)
                .build(username);

        HttpEntity<String> entityCredentials = new HttpEntity<>(null, createHeaderBody());

        return List.of(Objects.requireNonNull(restTemplate.exchange(uri, HttpMethod.GET, entityCredentials, Recipe[].class).getBody()));
    }

    private static HttpHeaders createHeaderBody() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.add("authorities", "[PERMIT]");

        return httpHeaders;
    }
}
