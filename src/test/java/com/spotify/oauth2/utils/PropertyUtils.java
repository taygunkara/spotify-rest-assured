package com.spotify.oauth2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyUtils(String filePath){
        Properties properties = new Properties();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));

            try {
                properties.load(bufferedReader);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("property file can't load -- " + filePath);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("property file is not located at " + filePath);
        }
        return properties;
    }

}
