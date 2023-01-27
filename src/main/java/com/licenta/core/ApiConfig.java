package com.licenta.core;

import org.springframework.web.util.UriComponentsBuilder;

public class ApiConfig {

    private static final Integer FOOD_PORT = 3000;

    private static final Integer RESTAURANT_PORT = 3002;

    private static final String SCHEME = "http";

    private static final String HOST = "localhost";

    private ApiConfig() {}

    public static UriComponentsBuilder foodApiPath() {
        return UriComponentsBuilder.newInstance()
                .scheme(SCHEME)
                .host(HOST)
                .port(FOOD_PORT)
                .path("v1/food-api");
    }

    public static UriComponentsBuilder restaurantApiPath() {
        return UriComponentsBuilder.newInstance()
                .scheme(SCHEME)
                .host(HOST)
                .port(RESTAURANT_PORT)
                .path("v1/restaurant-api");
    }
}
