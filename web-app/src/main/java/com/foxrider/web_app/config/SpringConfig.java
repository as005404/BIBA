package com.foxrider.web_app.config;

import com.foxrider.rest_client.AuthenticationRestClient;
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

}
