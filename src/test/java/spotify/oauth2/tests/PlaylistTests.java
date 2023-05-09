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
    String access_token = "BQAGr_xdwfU1IL6C0vZDmDW0Zsp5wgYCj70tOHuxWkAuhq72bepiD0ansuLJ-Jm_BExUPCJreAVDTdLmr0KMFreB1wLVkbMNoY962rPriNfPELRNJYfNXRudYR17mWyLtL0J3aMTCttPDTV9VBQfCpORcVds66GHbwKTauMYlyEyn-Q6bPBPTYCp4ZIGERrArztTbXb_yQ8itSIc-1Wo9vOwLyivwINySwDxzey5NOnPOB_R435nRyxNNo8tNb4";

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer " + access_token)
                .setContentType(ContentType.JSON);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void ShouldBeAbleToCreateAPlaylist(){

        String payload = "{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
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
                        "description", equalTo("New playlist description"),
                        "public", equalTo(false));

    }




}
