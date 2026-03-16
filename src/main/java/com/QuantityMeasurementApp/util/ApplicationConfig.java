package com.QuantityMeasurementApp.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = ApplicationConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("application.properties not found");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load application configuration", e);
        }
    }

    public static String getRepositoryType() {
        return properties.getProperty("repository.type", "cache");
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }

    public static String getDbDriver() {
        return properties.getProperty("db.driver");
    }

    public static int getMaxPoolSize() {
        return Integer.parseInt(
                properties.getProperty("db.hikari.maximum-pool-size", "10"));
    }

    public static int getMinIdle() {
        return Integer.parseInt(
                properties.getProperty("db.hikari.minimum-idle", "2"));
    }

}