package org.swing.Dao;

import org.swing.db.myConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student_DAO {
    Connection con = myConnection.getConnection();
    PreparedStatement preparedStatement ;

    // get table max row
    public int getMax(){
        int id = 0;
        try {
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select max(id) from Student");

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return id + 1 ;
    }
}
