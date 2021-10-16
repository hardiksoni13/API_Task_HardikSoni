package com.vinted.api.automation.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This class will be used to read property from the given data file.
 * Here we can do property change as per the env and same testcases will work as per the env.
 */
public class PropertyReader {

    static Properties properties = new Properties();

    static {
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/application.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHostValue() {
        return properties.getProperty("host", "https://petstore3.swagger.io");
    }

    public static Long getResponseTimeLimit() {
        String value = properties.getProperty("responseTimeLimitInMS", "200");
        return Long.valueOf(value);
    }


    public static String getAuthorizeToken() {
       return properties.getProperty("authorizeToken", "1d4cbe186349adcb9640753aea68a32e7f1faacb39c92a1307d5e482bfef389f");
    }
}








