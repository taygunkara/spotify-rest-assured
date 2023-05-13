package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.models.Playlist;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.*;


public class PlaylistApi {


    public static Response post(Playlist requestPlaylist, String token){
        return RestResource.post(USERS + "/kara.taygun" + PLAYLISTS, token, requestPlaylist);
    }

    public Response get(String playlistID, String token){
        return RestResource.get(PLAYLISTS + "/" + playlistID, token);
    }

    public Response update(Playlist requestPlaylist, String playlistID, String token){
        return RestResource.update(PLAYLISTS  + "/" +  playlistID, requestPlaylist, token);
    }

}
