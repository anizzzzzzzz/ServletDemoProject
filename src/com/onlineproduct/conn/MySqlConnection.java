package com.onlineproduct.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    public static Connection getMySqlConnection() throws ClassNotFoundException, SQLException{
        String hostName="localhost";
        String dbName="products";
        String username="root";

        return getMySqlConnection(hostName,dbName,username);
    }

    public static Connection getMySqlConnection(String hostName, String dbName, String userName)
            throws ClassNotFoundException,SQLException{

        Class.forName("com.mysql.jdbc.Driver");

        String connectionUrl= "jdbc:mysql://"+hostName+":3306/"+dbName;

        Connection connection= DriverManager.getConnection(connectionUrl,userName,"");
        return connection;
    }
}
