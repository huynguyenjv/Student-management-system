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


public class Course_DAO {

    PreparedStatement preparedStatement;

    public int  getMax(){
        int id = 0 ;

        try {
            Connection conn = getConnection();

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM Course");

            while (rs.next()){

                id = rs.getInt(1);
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Course_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return id+1;
    }

    public boolean getId(Course course){
        try {
            Connection conn = getConnection();

            String sql = "SELECT * FROM Student WHERE deleted_at IS NULL and id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,course.getStudentID());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Home.mssv_textField_course.setText(String.valueOf(resultSet.getInt(1)));
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Student id doesn't exist");
            }

            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }

    public int countSemester(Course course){
        int total = 0;
        try {
            Connection conn = getConnection();

            String sql = "SELECT COUNT(*) as 'total' FROM Course WHERE  student_id =?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,course.getStudentID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                total = resultSet.getInt(1);
            }
            if( total == 8){
                JOptionPane.showMessageDialog(null, "This student has completed all the courses");
                return -1;
            }
        }catch (SQLException e){
            Logger.getLogger(Course_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return total;
    }

    // check whether the student has already taken this semester or not

    public boolean isSemesterExist(int studentID ,String semesterNo){
        try{
            Connection conn = myConnection.getConnection();
            String sql = "SELECT * FROM course WHERE student_id = ? and semester =?";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, studentID);
            preparedStatement.setString(2, semesterNo);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            Logger.getLogger(Course_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }

    public boolean isCourseExist(Course course , String courseNo , String courseName){
        try{

            Connection conn = myConnection.getConnection();
            String sql = "SELECT * FROM course WHERE student_id = ? and " + courseNo + " =? ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, course.getStudentID());
            preparedStatement.setString(2, courseName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }

        }catch (SQLException e) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    public void insertCourse(Course course){

        try {
            Connection conn = getConnection();

            String sql = "INSERT INTO Course (id,student_id,semester,course1,course2,course3,course4,course5) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setInt(2, course.getStudentID());
            preparedStatement.setString(3, course.getSemester());
            preparedStatement.setString(4, course.getCourse1());
            preparedStatement.setString(5, course.getCourse2());
            preparedStatement.setString(6, course.getCourse3());
            preparedStatement.setString(7, course.getCourse4());
            preparedStatement.setString(8, course.getCourse5());

            if( preparedStatement.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"New course added successfully");
            }

        } catch (SQLException e) {
            Logger.getLogger(Course_DAO.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public void updateCourse(Course course){

        try {
            Connection conn = myConnection.getConnection();

            String sql = " UPDATE Course " +
                    "SET course1 = ?" +
                    ", course2 = ?" +
                    ", course3 = ?" +
                    ", course4 = ?" +
                    ", course5 = ?" +
                    " WHERE student_id = ? and semester = ?";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,course.getCourse1());
            preparedStatement.setString(2,course.getCourse2());
            preparedStatement.setString(3,course.getCourse3());
            preparedStatement.setString(4,course.getCourse4());
            preparedStatement.setString(5,course.getCourse5());
            preparedStatement.setInt(6,course.getStudentID());
            preparedStatement.setString(7,course.getSemester());

            if(preparedStatement.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Course data update successfully");
            }

            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Course_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    public void getCourseValue(JTable table , String searchValue){
        try {
            String sql = "SELECT * FROM Course WHERE deleted_at is null and " +
                    "student_id like ? " +
                    "   order by id asc ";

            Connection con = getConnection();

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchValue + "%");


            ResultSet rs = preparedStatement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();

            Object[] row;

            while (rs.next()) {
                row = new Object[8];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);

                model.addRow(row);
            }

            con.close();
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean isCourseInfoExist(String searchValue){

        try {
            Connection conn = myConnection.getConnection();

            String sql = "SELECT * FROM Course WHERE student_id LIKE ? ";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"%"+searchValue+"%");

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "There are no course information for student whose student id is "+ searchValue +" in data");
                return false;
            }
        } catch (SQLException e) {
            Logger.getLogger(Score_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return true;
    }

}
