package com.spotify.oauth2.tests;

import static com.spotify.oauth2.api.TokenManager.RenewToken;
import static com.spotify.oauth2.api.applicationApi.PlaylistHelper.*;
import static com.spotify.oauth2.utils.FakerUtils.generatePlaylistDescription;
import static com.spotify.oauth2.utils.FakerUtils.generatePlaylistName;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.models.Error;
import com.spotify.oauth2.models.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import com.spotify.oauth2.utils.DataLoader;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PlaylistTests {
    @Test
    public void ShouldBeAbleToCreateAPlaylist(){
        Playlist requestPlaylist = playlistBuilder(generatePlaylistName(), generatePlaylistDescription(), false);
        Response response = new PlaylistApi().post(requestPlaylist, RenewToken());
        assertStatusCode(response, StatusCode.CODE_201);
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist, requestPlaylist);
    }
    @Test
    public void ShouldBeAbleToGetPlaylist(){
        Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);
        Response response = new PlaylistApi().get(DataLoader.getInstance().getGetPlaylistID(), RenewToken());
        assertStatusCode(response, StatusCode.CODE_200);
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist, requestPlaylist);
    }
    @Test
    public void ShouldBeAbleToUpdate(){
        Playlist requestPlaylist = playlistBuilder(generatePlaylistName(), generatePlaylistDescription(), false);
        Response response = new PlaylistApi().update(requestPlaylist, DataLoader.getInstance().getUpdatePlaylistID(), RenewToken());
        assertStatusCode(response, StatusCode.CODE_200);
    }
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithName(){
        Playlist requestPlaylist = playlistBuilder("", generatePlaylistDescription(), false);
        Response response = new PlaylistApi().post(requestPlaylist, RenewToken());
        assertStatusCode(response, StatusCode.CODE_400);
        Error error = response.as(Error.class);
        assertError(error, StatusCode.CODE_400);
    }
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){
        Playlist requestPlaylist = playlistBuilder(generatePlaylistName(), generatePlaylistDescription(), false);
        Response response = new PlaylistApi().post(requestPlaylist, ConfigLoader.getInstance().getInvalidToken());
        assertStatusCode(response, StatusCode.CODE_401);
        Error error = response.as(Error.class);
        assertError(error, StatusCode.CODE_401);
    }
}
