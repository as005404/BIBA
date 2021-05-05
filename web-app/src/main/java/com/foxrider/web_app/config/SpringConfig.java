package com.foxrider.web_app.config;

import com.foxrider.rest_client.*;
import com.foxrider.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfig {

    @Value("${rest.server.port}")
    private Integer port;

    @Value("${rest.server.url}")
    private String url;

    @Value("${rest.server.protocol}")
    private String protocol;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    LoginAndRegisterService loginAndRegisterService() {
        return new AuthenticationRestClient(String.format("%s://%s:%d", protocol, url, port), restTemplate());
    }

    @Bean
    ValueOfSensorRestClient valueOfSensorService() {
        return new ValueOfSensorRestClient(String.format("%s://%s:%d/values", protocol, url, port), restTemplate());
    }

    @Bean
    PersonRestClient personService() {
        return new PersonRestClient(String.format("%s://%s:%d/persons", protocol, url, port), restTemplate());
    }

    @Bean
    SensorRestClient sensorService() {
        return new SensorRestClient(String.format("%s://%s:%d/sensors", protocol, url, port), restTemplate());
    }

    @Bean
    ShiftRestClient shiftService() {
        return new ShiftRestClient(String.format("%s://%s:%d/shifts", protocol, url, port), restTemplate());
    }

    @Bean
    UtilRestClient utilRestClient() {
        return new UtilRestClient(String.format("%s://%s:%d/", protocol, url, port), restTemplate());
    }

    @Bean
    AccessRestClient accessRestClient() {
        return new AccessRestClient(String.format("%s://%s:%d/accesses", protocol, url, port), restTemplate());
    }
}
