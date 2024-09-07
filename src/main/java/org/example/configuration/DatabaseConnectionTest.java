package org.example;

import org.example.configuration.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        // Get the singleton instance of DatabaseConfig
        DatabaseConfig dbConfig = DatabaseConfig.getInstance();

        Connection connection = null;

        try {
            // Try to establish a connection
            connection = dbConfig.getConnection();

            // Check if the connection is established
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Failed to establish connection!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Handle any exception that might occur (e.g., wrong credentials, server not running, etc.)
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed properly
            try {
                if (connection != null && !connection.isClosed()) {
                    dbConfig.disconnect();
                    System.out.println("Connection closed successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
