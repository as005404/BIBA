package com.foxrider.rest_client;

import com.foxrider.entity.Sensor;
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

public class SensorRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorRestClient.class);

    private final String url;
    private final RestTemplate restTemplate;

    public SensorRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<Sensor> findAll(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, GET, httpEntity, new ParameterizedTypeReference<List<Sensor>>() {
        }).getBody();
    }

    public Optional<Sensor> findByName(String name, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<ValueOfSensors> httpEntity = new HttpEntity<>(headers);

        return Optional.ofNullable(restTemplate.exchange(url + "/" + name, GET, httpEntity, Sensor.class).getBody());
    }

    public Sensor create(Sensor sensor, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(sensor, headers);

        return restTemplate.exchange(url, POST, httpEntity, Sensor.class).getBody();
    }

    public Sensor update(Sensor sensor, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(sensor, headers);

        return restTemplate.exchange(url, PUT, httpEntity, Sensor.class).getBody();
    }

    public void delete(Integer sensorId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<ValueOfSensors> httpEntity = new HttpEntity<>(headers);

        restTemplate.exchange(url + "/" + sensorId, DELETE, httpEntity, Sensor.class);
    }
}
