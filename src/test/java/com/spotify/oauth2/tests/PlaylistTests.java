package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.models.Error;
import com.spotify.oauth2.models.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.PropertyUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.TokenManager.RenewToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    @Test
    public void ShouldBeAbleToCreateAPlaylist(){

        Playlist requestPlaylist = new Playlist()
                .setName("New Playlist")
                .setDescription("New playlist description")
                .setPublic(false);

        Response response = PlaylistApi.post(requestPlaylist, RenewToken());
        assertThat(response.statusCode(), equalTo(201));

        Playlist responsePlaylist = response.as(Playlist.class);

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

        Response response = new PlaylistApi().get(DataLoader.getInstance().getGetPlaylistID(), RenewToken());
        assertThat(response.statusCode(), equalTo(200));
        Playlist responsePlaylist = response.as(Playlist.class);

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

        Response response = new PlaylistApi().update(requestPlaylist, DataLoader.getInstance().getUpdatePlaylistID(), RenewToken());
        assertThat(response.statusCode(), equalTo(200));

    }
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithName(){

        Playlist requestPlaylist = new Playlist()
                .setName("")
                .setDescription("Updated playlist description")
                .setPublic(false);

        Response response = new PlaylistApi().post(requestPlaylist, RenewToken());
        assertThat(response.statusCode(), equalTo(400));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));


    }
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){

        String invalid_access_token = "12345";
        Playlist requestPlaylist = new Playlist()
                .setName("Updated Playlist Name")
                .setDescription("Updated playlist description")
                .setPublic(false);


        Response response = new PlaylistApi().post(requestPlaylist, invalid_access_token);
        assertThat(response.statusCode(), equalTo(401));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}
