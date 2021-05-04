package com.foxrider.rest_client.utils;

public class PrefixAdder {

    public static String addPrefix(String token) {
        return "Bearer " + token;
    }

}
