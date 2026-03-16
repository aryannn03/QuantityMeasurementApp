package com.QuantityMeasurementApp.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {

    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(ApplicationConfig.getDbUrl());
            config.setUsername(ApplicationConfig.getDbUsername());
            config.setPassword(ApplicationConfig.getDbPassword());
            config.setDriverClassName(ApplicationConfig.getDbDriver());

            config.setMaximumPoolSize(ApplicationConfig.getMaxPoolSize());
            config.setMinimumIdle(ApplicationConfig.getMinIdle());

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
    public static void initializeDatabase() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            InputStream input = ConnectionPool.class
                    .getClassLoader()
                    .getResourceAsStream("db/schema.sql");

            if (input == null) {
                throw new RuntimeException("schema.sql not found");
            }

            String sql = new String(input.readAllBytes());
            statement.execute(sql);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }
}