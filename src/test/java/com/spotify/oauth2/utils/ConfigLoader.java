package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {
    static Properties properties;
    static ConfigLoader configLoader;

    public ConfigLoader(){
        properties = PropertyUtils.propertyUtils("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientID() {
        String prop = properties.getProperty("client_id");
        if (prop == null) {
            throw new RuntimeException("client_id can't find in config.property");
        }
        return prop;
    }
    public String getClientSecret() {
        String prop = properties.getProperty("client_secret");
        if (prop == null) {
            throw new RuntimeException("client_secret can't find in config.property");
        }
        return prop;
    }
    public String getGrantType() {
        String prop = properties.getProperty("grant_type");
        if (prop == null) {
            throw new RuntimeException("grant_type can't find in config.property");
        }
        return prop;
    }
    public String getRefreshToken() {
        String prop = properties.getProperty("refresh_token");
        if (prop == null) {
            throw new RuntimeException("refresh_token can't find in config.property");
        }
        return prop;
    }
    public String getUserID() {
        String prop = properties.getProperty("user_id");
        if (prop == null) {
            throw new RuntimeException("user_id can't find in config.property");
        }
        return prop;
    }
}
