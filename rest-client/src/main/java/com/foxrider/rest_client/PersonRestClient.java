package com.foxrider.rest_client;

import com.foxrider.entity.Person;
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
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;

public class PersonRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRestClient.class);

    private String url;
    private RestTemplate restTemplate;

    public PersonRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<Person> findAll(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<ValueOfSensors> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, GET, httpEntity, new ParameterizedTypeReference<List<Person>>() {
        }).getBody();
    }


    public Optional<Person> findByEmail(String email, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<ValueOfSensors> httpEntity = new HttpEntity<>(headers);


        return Optional.ofNullable(restTemplate.exchange(url + "/" + email, GET, httpEntity, Person.class).getBody());
    }

    public Person create(Person person) {
        return null;
    }

    public Person update(Person person, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", addPrefix(token));
        HttpEntity<Object> httpEntity = new HttpEntity<>(person, headers);

        return restTemplate.exchange(url, PUT, httpEntity, Person.class).getBody();
    }

    public void delete(Integer personId) {

    }
}
