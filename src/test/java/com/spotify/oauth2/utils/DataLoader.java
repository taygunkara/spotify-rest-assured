package com.spotify.oauth2.utils;

import java.util.Properties;

public class DataLoader {
    static Properties properties;
    static DataLoader dataLoader;

    public DataLoader(){
        properties = PropertyUtils.propertyUtils("src/test/resources/data.properties");
    }

    public static DataLoader getInstance() {
        if (dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getUpdatePlaylistID(){
        String prop = properties.getProperty("update_playlist_id");
        if (prop == null) {
            throw new RuntimeException("update_playlist_id can't load");
        }
        return prop;
    }

    public String getGetPlaylistID(){
        String prop = properties.getProperty("get_playlist_id");
        if (prop == null) {
            throw new RuntimeException("get_playlist_id can't load");
        }
        return prop;
    }
}
