package com.gmail.ksma.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Maksym Melanich
 * Date: 29.07.2021
 * Time: 18:10
 */
public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/delivery";
    public static final String USER = "root";
    public static final String PASS = "q?eMx\"S-W2pnM*.X";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
