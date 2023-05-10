package com.spotify.oauth2.tests;

import com.spotify.oauth2.models.Error;
import com.spotify.oauth2.models.Playlist;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQBblVS3YjethV81GcYLiYwPMcjGyb1Lsd_U-bFkLIhP8B00o85eaQ87lb2PpUzqqiUhsQWei6qP4EpHcWgx1jK04m0OmrxlIVROEN-ZyK1xJoJgLcJ1esrysv15OL5YBdDEXrjj06Dlcjb14cRgHZDOzlWkBgnJZHIO_g5kswaXh9Wnc8SBQ5TWwpWOdsxexyKj2WJE09kPBhnixTUEuwlpNkc1TQAVRMHeQHGTLOctedLlBTrHRVJ-N4k7zpE";

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

        Playlist requestPlaylist = new Playlist()
                .setName("New Playlist")
                .setDescription("New playlist description")
                .setPublic(false);

        Playlist responsePlaylist = given(requestSpecification)
                .body(requestPlaylist)
                .when()
                .post("/users/kara.taygun/playlists")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(201)
                .extract()
                .response()
                .as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Test
    public void ShouldBeAbleToGetPlaylist(){

        Playlist requestPlaylist = new Playlist()
                .setName("New Playlist")
                .setDescription("New playlist description")
                .setPublic(false);

        Playlist responsePlaylist = given(requestSpecification)
                .when()
                .get("/playlists/4XCIJKGwTpzL92uOAZZKg3")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));


    }
    @Test
    public void ShouldBeAbleToUpdate(){

        Playlist requestPlaylist = new Playlist()
                .setName("Updated Playlist Name")
                .setDescription("Updated playlist description")
                .setPublic(false);

        given(requestSpecification)
                .body(requestPlaylist)
                .when()
                .put("/playlists/4XCIJKGwTpzL92uOAZZKg3")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithName(){


        Playlist requestPlaylist = new Playlist()
                .setName("")
                .setDescription("Updated playlist description")
                .setPublic(false);


         Error error = given(requestSpecification)
                .body(requestPlaylist)
                .when()
                .post("/users/kara.taygun/playlists")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(400)
                .extract()
                .response()
                .as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));


    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){

        Playlist requestPlaylist = new Playlist()
                .setName("Updated Playlist Name")
                .setDescription("Updated playlist description")
                .setPublic(false);

        String expired_access_token = "QA2KRNy4ClSwhwK1uWZwDucmqjmHavddUloSerfN8DecPS_AOSRIbUPx5FTkA410l2KsnfcytPD-NLhpZ_0Sc5Vl_ak-PiP8eLYem6wYhGnSjy869w_SYgrKyiRbMDZboHLJBT73fyNTmCpLjVeb8mhzEhPMUBlHswtWD9oHmTTsPmgPDVMlHA6PVckdzNasGBx4tpp4klAnQCBuY8Hdq_lPKsvptfOUmsug-y1oCis0JGyr5-7IhMh1c0vcts";

        Error error = given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + expired_access_token)
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestPlaylist)
                .when()
                .post("/users/kara.taygun/playlists")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(401)
                .extract()
                .response()
                .as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }




}
