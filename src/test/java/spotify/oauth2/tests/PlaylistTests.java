package spotify.oauth2.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQA2KRNy4ClSwhwK1uWZwDucmqjmHavddUloSerfN8DecPS_AOSRIbUPx5FTkA410l2KsnfcytPD-NLhpZ_0Sc5Vl_ak-PiP8eLYem6wYhGnSjy869w_SYgrKyiRbMDZboHLJBT73fyNTmCpLjVeb8mhzEhPMUBlHswtWD9oHmTTsPmgPDVMlHA6PVckdzNasGBx4tpp4klAnQCBuY8Hdq_lPKsvptfOUmsug-y1oCis0JGyr5-7IhMh1c0vcts";

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer " + access_token)
                .setContentType(ContentType.JSON);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void ShouldBeAbleToCreateAPlaylist(){

        String payload = "{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"ddddddddescription\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification)
                .body(payload)
                .when()
                .post("/users/kara.taygun/playlists")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("New Playlist"),
                        "description", equalTo("ddddddddescription"),
                        "public", equalTo(false));

    }

    @Test
    public void ShouldBeAbleToGetPlaylist(){

        given(requestSpecification)
                .when()
                .get("/playlists/7wtmZ1I1XDWMQPcqXPVB0N")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("New Playlist"),
                        "description", equalTo("ddddddddescription"),
                        "public", equalTo(false));

    }
    @Test
    public void ShouldBeAbleToUpdate(){

        String updatedBody = "{\n" +
                "    \"name\": \"Updated Playlist Name\",\n" +
                "    \"description\": \"Updated playlist description\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification)
                .body(updatedBody)
                .when()
                .put("/playlists/7wtmZ1I1XDWMQPcqXPVB0N")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(200);
    }




}
