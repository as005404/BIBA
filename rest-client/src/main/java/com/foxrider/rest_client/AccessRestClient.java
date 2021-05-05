package com.foxrider.rest_client;

import com.foxrider.entity.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.foxrider.rest_client.utils.PrefixAdder.addPrefix;
import static org.springframework.http.HttpMethod.GET;

public class AccessRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessRestClient.class);

    private final String url;
    private final RestTemplate restTemplate;

    public AccessRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<Access> findAll(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, GET, httpEntity, new ParameterizedTypeReference<List<Access>>() {
        }).getBody();
    }

}
