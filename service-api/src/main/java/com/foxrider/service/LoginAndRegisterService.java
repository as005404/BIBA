package com.foxrider.service;

import com.foxrider.entity.UsernameAndPassword;
import org.springframework.http.ResponseEntity;

public interface LoginAndRegisterService {

    ResponseEntity<Object> login(UsernameAndPassword usernameAndPassword);

    void register(UsernameAndPassword usernameAndPassword);
}
