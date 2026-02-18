package com.akash.automation.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();

    public static void loadConfig(String platform) {
        try {
            prop.clear();
            prop.load(new FileInputStream(
                    "src/test/resources/config/" + platform + ".properties"));
            prop.load(new FileInputStream(
                    "src/test/resources/config/env.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static String get(String key) {
        return System.getProperty(key) != null
                ? System.getProperty(key)
                : prop.getProperty(key);
    }
}
