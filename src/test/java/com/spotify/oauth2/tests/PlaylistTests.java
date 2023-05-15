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

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PlaylistTests {

    @Description("should be able to create a playlist")
    @Test(description = "Create A Playlist Test")
    public void ShouldBeAbleToCreateAPlaylist(){
        Playlist requestPlaylist = playlistBuilder(generatePlaylistName(), generatePlaylistDescription(), false);
        Response response = new PlaylistApi().post(requestPlaylist, RenewToken());
        assertStatusCode(response, StatusCode.CODE_201);
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist, requestPlaylist);
    }
    @Description("should be able to get a playlist")
    @Test(description = "Get A Playlist Test")
    public void ShouldBeAbleToGetPlaylist(){
        Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);
        Response response = new PlaylistApi().get(DataLoader.getInstance().getGetPlaylistID(), RenewToken());
        assertStatusCode(response, StatusCode.CODE_200);
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist, requestPlaylist);
    }
    @Description("should be able to update a playlist")
    @Test(description = "Update Playlist Test")
    public void ShouldBeAbleToUpdate(){
        Playlist requestPlaylist = playlistBuilder(generatePlaylistName(), generatePlaylistDescription(), false);
        Response response = new PlaylistApi().update(requestPlaylist, DataLoader.getInstance().getUpdatePlaylistID(), RenewToken());
        assertStatusCode(response, StatusCode.CODE_200);
    }
    @Description("should NOT be able to create a playlist with missing name information")
    @Test(description = "(NEGATIVE) Create A Playlist With Missing Informations")
    public void ShouldNotBeAbleToCreateAPlaylistWithName(){
        Playlist requestPlaylist = playlistBuilder("", generatePlaylistDescription(), false);
        Response response = new PlaylistApi().post(requestPlaylist, RenewToken());
        assertStatusCode(response, StatusCode.CODE_400);
        Error error = response.as(Error.class);
        assertError(error, StatusCode.CODE_400);
    }
    @Description("should NOT able to create a playlist with expired token")
    @Test(description = "(NEGATIVE) Create A Playlist With Expired Token")
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){
        Playlist requestPlaylist = playlistBuilder(generatePlaylistName(), generatePlaylistDescription(), false);
        Response response = new PlaylistApi().post(requestPlaylist, ConfigLoader.getInstance().getInvalidToken());
        assertStatusCode(response, StatusCode.CODE_401);
        Error error = response.as(Error.class);
        assertError(error, StatusCode.CODE_401);
    }
}
