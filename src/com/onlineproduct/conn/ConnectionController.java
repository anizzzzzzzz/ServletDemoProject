package com.onlineproduct.conn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionController {
    private static Logger logger=Logger.getLogger("ConnectionController");
    public static Connection getConnection()
        throws ClassNotFoundException, SQLException{
        return MySqlConnection.getMySqlConnection();
    }

    public static void closeConnection(Connection conn){
        try{
            conn.close();
        }
        catch (Exception e){
            logger.info("Exception occured while closing Database Connection : "+e.getMessage());
        }
    }

    public static void rollBack(Connection conn){
        try{
            conn.rollback();
        }
        catch (Exception ex){
            logger.info("Exception occured while rolling back Database changes : "+ ex.getMessage());
        }
    }
}
