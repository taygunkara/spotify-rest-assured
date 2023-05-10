package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.models.Playlist;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class PlaylistApi {

    static String access_token = "BQDWLMQmlJvvoKWx5UrT3_XN-V68f4cv6YwJ6ZnJfHBf4WmPdSPxz-uw5RtOATdjeZhcAxok8KAhQwK20M02PlRQRgZj9MLIva1jSwkKi-KEMw_Ez6wRF45YkqjggDpOY45W6hujl-KarwL_RpM9WL3_kTEMPO99zzV9_hms6UjuLClefiwC96GNhlGYmOYhbfm6a_-Mtp6wkxLmYOUpzz3cMv94DQXfeW-r-OdElSsZor2dKbsY1EukuqsP7q4";

    public static Response post(Playlist requestPlaylist){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization", "Bearer " + access_token)
                .when()
                .post("/users/kara.taygun/playlists")
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public Response get(String playlistID){
        return given(getRequestSpec())
                .when()
                .header("Authorization", "Bearer " + access_token)
                .get("/playlists/" + playlistID)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public Response update(Playlist requestPlaylist, String playlistID){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .when()
                .header("Authorization", "Bearer " + access_token)
                .put("/playlists/" + playlistID)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String token, Playlist requestPlaylist){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/users/kara.taygun/playlists")
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }




}
