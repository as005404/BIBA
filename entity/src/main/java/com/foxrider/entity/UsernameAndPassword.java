package com.foxrider.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsernameAndPassword {

    @NotBlank(message = "Email is mandatory")
    @Size(min = 2, message = "Email should be b-n 2 and 30 characters", max = 30)
    private String username;

    @Size(min = 2, message = "Password should be b-n 2 and 512 characters", max = 512)
    private String password;

    public UsernameAndPassword() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
