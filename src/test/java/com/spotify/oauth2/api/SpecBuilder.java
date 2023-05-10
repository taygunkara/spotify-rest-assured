package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
    static String access_token = "BQAslwgSzDsC-bC2ojPswu8_we4b0jajGLButyW7n_VFaIuPyRjedkaOPJsfJLaUPlJlSomfe-Vc2xzl9n5tO7x85sWjuM69VSt_c4Bl3LQ2ucPSaxJGIXBPgklgvz2AlJCGugyi6uE1END3r0UV_6hBxbnhQfa4gyKUeiqDaZO9xSHrvO5PUn73gNG1CALqhnOef7BMemB1tWAxDyEeHhcN5WsMLaJtgLhzucVy6iHHYf_ie83OlZ2mImMIv1Y";

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
