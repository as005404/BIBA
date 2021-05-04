package com.foxrider.rest_client;

import com.foxrider.entity.ValueOfSensors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.foxrider.rest_client.utils.PrefixAdder.addPrefix;
import static org.springframework.http.HttpMethod.*;

public class ValueOfSensorRestClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationRestClient.class);

    private String url;
    private RestTemplate restTemplate;

    public ValueOfSensorRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<ValueOfSensors> findAll(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, GET, httpEntity, new ParameterizedTypeReference<List<ValueOfSensors>>() {
        }).getBody();
    }

    public Optional<ValueOfSensors> findById(Integer id, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        return Optional.ofNullable(restTemplate.exchange(url + "/" + id, GET, httpEntity, ValueOfSensors.class).getBody());

//        return restTemplate.getForObject(url + "/" + id, Optional.class);
    }


    public ValueOfSensors create(ValueOfSensors value, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<ValueOfSensors> httpEntity = new HttpEntity<>(value, headers);

        return restTemplate.exchange(url, POST, httpEntity, ValueOfSensors.class).getBody();
    }


    public ValueOfSensors update(ValueOfSensors value, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<ValueOfSensors> httpEntity = new HttpEntity<>(value, headers);

        return restTemplate.exchange(url, PUT, httpEntity, ValueOfSensors.class).getBody();

    }

    public void delete(Integer id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<ValueOfSensors> httpEntity = new HttpEntity<>(headers);

        restTemplate.exchange(url + "/" + id, DELETE, httpEntity, Object.class);
    }
}
