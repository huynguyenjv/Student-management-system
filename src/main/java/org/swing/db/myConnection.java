package org.swing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class myConnection {
    private static final String username="root";
    private static final String password="baohuy2501";
    private static final String dataConnection = "jdbc:mysql://localhost:3306/student_management";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            connection = DriverManager.getConnection(dataConnection,username,password);

        } catch (SQLException e) {
            Logger.getLogger(myConnection.class.getName()).log(Level.SEVERE,null,e);
        }
        return connection;
    }
}
