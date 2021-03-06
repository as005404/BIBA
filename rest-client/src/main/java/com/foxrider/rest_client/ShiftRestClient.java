package com.foxrider.rest_client;

import com.foxrider.entity.Shift;
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

public class ShiftRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiftRestClient.class);

    private String url;
    private RestTemplate restTemplate;

    public ShiftRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<Shift> findAll(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, GET, httpEntity, new ParameterizedTypeReference<List<Shift>>() {
        }).getBody();
    }

    public Optional<Shift> findById(Integer shiftId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        return Optional.ofNullable(restTemplate.exchange(url + "/" + shiftId, GET, httpEntity, Shift.class).getBody());
    }

    public Optional<Shift> findByName(String name, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<ValueOfSensors> httpEntity = new HttpEntity<>(headers);


        return Optional.ofNullable(restTemplate.exchange(url + "/" + name, GET, httpEntity, Shift.class).getBody());

    }

    public Shift create(Shift shift, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Shift> httpEntity = new HttpEntity<>(shift, headers);

        return restTemplate.exchange(url, POST, httpEntity, Shift.class).getBody();
    }

    public Shift update(Shift shift, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Shift> httpEntity = new HttpEntity<>(shift, headers);

        return restTemplate.exchange(url, PUT, httpEntity, Shift.class).getBody();
    }

    public void delete(Integer shiftId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        restTemplate.exchange(url + "/" + shiftId, DELETE, httpEntity, Shift.class);
    }
}
