package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.models.Error;
import com.spotify.oauth2.models.Playlist;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.TokenManager.RenewToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {
    // static String access_token = "BQD46gYBbRoxUlRlUpiLm9uNS2zzfHGynw9wgMewrZ1_mYHCG7Maba6NVZ8zDyBJFzbkYVENl_DTzFMGMZMsLOzcwDjSt0Rq_kbuE3cIpt0tBDyXkdnJtxgsbQ4f1yPjFNSDDm7XgtdIdTptgiB2h0z_9CAUWDDQ8qOnnujo77cLfj2trqJlyss2Mk-AkLQicBYid4nWWvqgi9R__XIhmbLnVDzT--wGZAs0glTA2h4vSKBKLuNrcB2FVYjFou0";


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

        Response response = new PlaylistApi().get("6c4kJgjvkBkHiT4Qi0hHG8", RenewToken());
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

        Response response = new PlaylistApi().update(requestPlaylist, "6c4kJgjvkBkHiT4Qi0hHG8", RenewToken());
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
