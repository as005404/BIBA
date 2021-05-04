package com.foxrider.rest_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static com.foxrider.rest_client.utils.PrefixAdder.addPrefix;
import static org.springframework.http.HttpMethod.GET;

public class UtilRestClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilRestClient.class);

    private String url;
    private RestTemplate restTemplate;

    public UtilRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public String getUsername(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(url + "/getUsername", GET, httpEntity, String.class).getBody();
    }

    public Set<String> getRoles(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(url + "/getRoles", GET, httpEntity, new ParameterizedTypeReference<Set<String>>() {
        }).getBody();
    }
}
