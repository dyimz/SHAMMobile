package com.example.shammobile.remote;

public class APIUtils {
    private APIUtils(){
    }

    public static final String API_URL = "https://seniorhigh-svnhs.com/";
//    public static final String API_URL = "http://192.168.254.110:8080/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}
