package com.restaurant.config;

import lombok.Getter;

@Getter
public class RestApis {

    public static final String AUTH = "/auth";
    public static final String REGISTER = AUTH + "/register";
    public static final String LOGIN = AUTH + "/login";
    public static final String REFRESH = AUTH + "/refresh";

}
