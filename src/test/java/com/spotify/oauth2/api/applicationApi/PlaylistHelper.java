package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.models.Error;
import com.spotify.oauth2.models.Playlist;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistHelper {

    public static Playlist playlistBuilder(String name, String description, boolean _public){
        return new Playlist()
                .setName(name)
                .setDescription(description)
                .setPublic(_public);
    }

    public static void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    public static void assertStatusCode(Response response, int expectedStatusCode){
        assertThat(response.statusCode(), equalTo(expectedStatusCode));
    }

    public static void assertError(Error responseErr, int expectedStatus, String msg){
        assertThat(responseErr.getError().getStatus(), equalTo(expectedStatus));
        assertThat(responseErr.getError().getMessage(), equalTo(msg));
    }

}
