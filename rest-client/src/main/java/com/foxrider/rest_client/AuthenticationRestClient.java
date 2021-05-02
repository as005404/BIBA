package com.foxrider.rest_client;

import com.foxrider.entity.UsernameAndPassword;
import com.foxrider.service.LoginAndRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthenticationRestClient implements LoginAndRegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationRestClient.class);

    private String url;

    private RestTemplate restTemplate;

    public AuthenticationRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<Object> login(UsernameAndPassword usernameAndPassword) {
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url + "/login", usernameAndPassword, Object.class);
        HttpHeaders headers = responseEntity.getHeaders();

        return responseEntity;

    }

    @Override
    public void register(UsernameAndPassword usernameAndPassword) {

    }
}
