package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
    static String access_token = "BQBPX8QBbBwhZdZHePQ0B0Icbwx1BIYLF4KE-4mv5qy46pTSKMLqbCLdqwA2dol19c4LXWv5MgmyuN7weOiNan1RmmrFp05NQr6XEBBc8-xGkHeu0gAXMbNKZmLD143U_d6schBF31vT0Zou7-OCKL_sH6tmNYjUD0lpQjb2AwQRffnZhvinIPBBhOu4RHj5jkhlzg0e2cajxcswNc2zzP6UKxx9A9gtw6Kv9CnC37TX_9cT0cksHadNikO5OTs";

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer " + access_token)
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
