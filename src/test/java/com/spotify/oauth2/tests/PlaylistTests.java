package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.models.Error;
import com.spotify.oauth2.models.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import com.spotify.oauth2.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.TokenManager.RenewToken;
import static com.spotify.oauth2.api.applicationApi.PlaylistHelper.*;

public class PlaylistTests {

    @Test
    public void ShouldBeAbleToCreateAPlaylist(){

        Playlist requestPlaylist = playlistBuilder("New playlist", "New playlist description", false);

        Response response = new PlaylistApi().post(requestPlaylist, RenewToken());
        assertStatusCode(response, 201);

        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist, requestPlaylist);
    }
    @Test
    public void ShouldBeAbleToGetPlaylist(){

        Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);

        Response response = new PlaylistApi().get(DataLoader.getInstance().getGetPlaylistID(), RenewToken());
        assertStatusCode(response, 200);

        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist, requestPlaylist);


    }
    @Test
    public void ShouldBeAbleToUpdate(){

        Playlist requestPlaylist = playlistBuilder("Updated Playlist Name", "Updated playlist description", false);

        Response response = new PlaylistApi().update(requestPlaylist, DataLoader.getInstance().getUpdatePlaylistID(), RenewToken());
        assertStatusCode(response, 200);

    }
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithName(){

        Playlist requestPlaylist = playlistBuilder("", "Playlist description", false);

        Response response = new PlaylistApi().post(requestPlaylist, RenewToken());
        assertStatusCode(response, 400);

        Error error = response.as(Error.class);
        assertError(error, 400, "Missing required field: name");

    }
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){

        Playlist requestPlaylist = playlistBuilder("Playlist Name", "Playlist description", false);

        Response response = new PlaylistApi().post(requestPlaylist, ConfigLoader.getInstance().getInvalidToken());
        assertStatusCode(response, 401);

        Error error = response.as(Error.class);
        assertError(error, 401, "Invalid access token");

    }

}
