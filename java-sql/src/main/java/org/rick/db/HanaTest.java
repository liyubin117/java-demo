package org.rick.db;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HanaTest {
    @Test
    public void test1() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sap://host:39017", "myName",
                    "mySecret");
        } catch (SQLException e) {
            System.err.println("Connection Failed:");
            System.err.println(e);
            return;
        }
        if (connection != null) {
            try {
                System.out.println("Connection to HANA successful!");
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("Select 'Hello, world' from dummy");
                resultSet.next();
                String hello = resultSet.getString(1);
                System.out.println(hello);
            } catch (SQLException e) {
                System.err.println("Query failed!");
            }
        }
    }
}