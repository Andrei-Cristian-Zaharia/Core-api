package com.licenta.core;

import com.licenta.core.filters.RouteFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreConfiguration {

    private final CoreConfigurations coreConfigurations;

    public CoreConfiguration(CoreConfigurations coreConfigurations) {
        this.coreConfigurations = coreConfigurations;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FilterRegistrationBean<RouteFilter> filterRoute() {
        FilterRegistrationBean<RouteFilter> filter = new FilterRegistrationBean<>();

        filter.setFilter(new RouteFilter(coreConfigurations));

        return filter;
    }
}
