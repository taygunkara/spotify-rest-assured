package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.models.Playlist;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class PlaylistApi {


    public static Response post(Playlist requestPlaylist, String token){
        return RestResource.post("/users/kara.taygun/playlists", token, requestPlaylist);
    }

    public Response get(String playlistID, String token){
        return RestResource.get("/playlists/" + playlistID, token);
    }

    public Response update(Playlist requestPlaylist, String playlistID, String token){
        return RestResource.update("/playlists/", playlistID, requestPlaylist, token);
    }

}
