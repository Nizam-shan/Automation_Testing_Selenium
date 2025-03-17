/*
 * Copyright (c) 2025.
 * Automation Framework Selenium - Arnest
 */

package com.arnest.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelpers {

    public DatabaseHelpers() {
        super();
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException {


        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);

        return conn;
    }

}
