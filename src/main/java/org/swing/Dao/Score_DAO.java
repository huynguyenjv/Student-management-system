package org.swing.Dao;

import com.mysql.cj.log.Log;
import org.swing.db.myConnection;
import org.swing.model.Course;
import org.swing.model.Score;
import org.swing.view.Home;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.swing.db.myConnection.getConnection;

public class Score_DAO {
    PreparedStatement preparedStatement;
    public int  getMax(){
        int id = 0 ;

        try {
            Connection conn = getConnection();

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM Score");

            while (rs.next()){

                id = rs.getInt(1);
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Score_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return id+1;
    }

    public boolean getDetail(Score score){

        try {
            Connection conn = myConnection.getConnection();

            String sql = "SELECT * FROM Course WHERE " +
                    " student_id = ? and semester = ? " ;

            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1,score.getStudentID());
            preparedStatement.setString(2,score.getSemester());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Home.mssv_textField_score.setText(String.valueOf(resultSet.getInt(2)));
                Home.semester_textField_score.setText(String.valueOf(resultSet.getInt(3)));
                Home.course1_textField_score.setText(resultSet.getString(4));
                Home.course2_textField_score.setText(resultSet.getString(5));
                Home.course3_textField_score.setText(resultSet.getString(6));
                Home.course4_textField_score.setText(resultSet.getString(7));
                Home.course5_textField_score.setText(resultSet.getString(8));
                return true;
            }else{
                JOptionPane.showMessageDialog(null,"There are any information available");
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Score_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }
    public void insertScore(Score score){

        try {
            Connection conn = getConnection();

            String sql = "INSERT INTO Score (id,student_id,semester,course1,score1,course2,score2" +
                    ",course3,score3,course4,score4,course5,score5) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, score.getId());
            preparedStatement.setInt(2, score.getStudentID());
            preparedStatement.setString(3, score.getSemester());
            preparedStatement.setString(4, score.getCourse1());
            preparedStatement.setDouble(5,score.getScore1());
            preparedStatement.setString(6, score.getCourse2());
            preparedStatement.setDouble(7,score.getScore2());
            preparedStatement.setString(8, score.getCourse3());
            preparedStatement.setDouble(9,score.getScore3());
            preparedStatement.setString(10, score.getCourse4());
            preparedStatement.setDouble(11,score.getScore4());
            preparedStatement.setString(12, score.getCourse5());
            preparedStatement.setDouble(13,score.getScore5());

            if( preparedStatement.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"New course added successfully");
            }

        } catch (SQLException e) {
            Logger.getLogger(Course_DAO.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public void updateScore(Score score){

        try {
            Connection conn = myConnection.getConnection();

            String sql = " UPDATE Score " +
                    "SET score1 = ?" +
                    ", score2 = ?" +
                    ", score3 = ?" +
                    ", score4 = ?" +
                    ", score5 = ?" +
                    " WHERE student_id = ? AND semester = ? " ;

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDouble(1,score.getScore1());
            preparedStatement.setDouble(2, score.getScore2());
            preparedStatement.setDouble(3,score.getScore3());
            preparedStatement.setDouble(4,score.getScore4());
            preparedStatement.setDouble(5,score.getScore5());
            preparedStatement.setInt(6,score.getStudentID());
            preparedStatement.setString(7,score.getSemester());


            if(preparedStatement.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Score data update successfully !");
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Score_DAO.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public void getScoreValue(JTable table, String searchValue) {
        try {
            String sql = "SELECT * FROM Score WHERE deleted_at is null and " +
                    "Concat(student_id,semester) like ? " +
                    "   order by id asc ";

            Connection con = getConnection();

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchValue + "%");


            ResultSet rs = preparedStatement.executeQuery();


            DefaultTableModel model = (DefaultTableModel) table.getModel();

            Object[] row;

            while (rs.next()) {
                row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getString(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getString(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getDouble(11);
                row[11] = rs.getString(12);
                row[12] = rs.getDouble(13);

                model.addRow(row);
            }
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean isScoreExist(Score score ,String scoreNo ,double scoreNumber ){

        try {
            Connection conn = myConnection.getConnection();

            String sql = "SELECT * FROM Score WHERE student_id = ? and semester = ? and "+scoreNo+" = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,score.getStudentID());
            preparedStatement.setString(2,score.getSemester());
            preparedStatement.setDouble(3,scoreNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }

    public boolean isSemesterExist(Score score){

        try {
            Connection conn = myConnection.getConnection();

            String sql = "SELECT * FROM Score WHERE student_id = ? AND  semester = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,score.getStudentID());
            preparedStatement.setString(2,score.getSemester());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }

            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Score_DAO.class.getName()).log(Level.SEVERE,null,e);
        }


        return false;
    }

    public boolean isScoreInfoExist(String searchValue){

        try {
            Connection conn = myConnection.getConnection();

            String sql = "SELECT * FROM Score WHERE CONCAT(student_id,semester) LIKE ? ";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"%"+searchValue+"%");

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "There are no score information for student whose student id is "+ searchValue +" in data");
                return false;
            }
        } catch (SQLException e) {
            Logger.getLogger(Score_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return true;
    }
}
