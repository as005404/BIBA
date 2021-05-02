package com.foxrider.service;

public interface LoginAndRegisterService {

    void login(String username, String password);

    void register(String username, String password);
}
