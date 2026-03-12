package com.QuantityMeasurementApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.InputStream;
import java.util.Scanner;

public class ConnectionPool {

    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                String url = ApplicationConfig.getProperty("db.url");
                String username = ApplicationConfig.getProperty("db.username");
                String password = ApplicationConfig.getProperty("db.password");

                connection = DriverManager.getConnection(url, username, password);

                runSchema(connection);
            }

            return connection;

        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    private static void runSchema(Connection connection) {

        try {

            InputStream input = ConnectionPool.class
                    .getClassLoader()
                    .getResourceAsStream("db/schema.sql");

            if (input == null) {
                System.out.println("schema.sql not found");
                return;
            }

            Scanner scanner = new Scanner(input).useDelimiter(";");
            Statement statement = connection.createStatement();

            while (scanner.hasNext()) {

                String sql = scanner.next().trim();

                if (!sql.isEmpty()) {
                    statement.execute(sql);
                }
            }

            scanner.close();

            System.out.println("Schema loaded successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}