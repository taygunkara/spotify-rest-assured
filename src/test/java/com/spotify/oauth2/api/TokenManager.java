package com.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    public static String RenewToken(){

        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("client_id", "cf7748295ecb4aff86eafea95f29ff7a");
        formParams.put("client_secret", "98849a380e4048a19017cd929fc1d086");
        formParams.put("grant_type", "refresh_token");
        formParams.put("refresh_token", "AQBQvsqPCzJszh9dcwsbEHplWmp8FbRX1G6BM7SEHzjJ9ufFArWaCy0XXQMdcr8GUP-HZyC-F6oLRHDkFf0xENGDdWyghGtsWrHSHVJaOBOVg5jWmTkGl1PLLjpaV18zhyw");

        Response response = RestResource.postAccount(formParams);
        return response.path("access_token");

    }
}
