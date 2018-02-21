package com.gabriel;

import java.sql.*;
import java.util.ArrayList;

public class DAO {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/SOA";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "root";
    private Connection conn = null;
    private Statement stmt = null;

    public void executeSQL(ArrayList<String> sqlCommands) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            for (String sql : sqlCommands) {
                stmt.executeQuery(sql);
            }
            System.out.println("Successfully executed " + sqlCommands.size() + " SQL commands");
        } catch (SQLException e) { //Handle errors for JDBC
            e.printStackTrace();
        } catch (Exception e) { //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
