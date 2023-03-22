package org.swing.Dao;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.swing.db.myConnection;
import org.swing.model.Student;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.swing.db.myConnection.getConnection;


public class Student_DAO {

    PreparedStatement preparedStatement ;

    // get table max row
    public int getMax(){
        int id = 0;
        try {
            Connection con = myConnection.getConnection();
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select max(id) from Student");



            while (rs.next()) {
                id = rs.getInt(1);
            }
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return id + 1 ;
    }

    public void insertStudent(Student student){

        try {
            Connection con = myConnection.getConnection();

            String sql = "INSERT INTO Student (id,name,date_of_birth,gender,email,phone,father_name,mother_name,country,address,image_path) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(3,student.getDateOfBirth());
            preparedStatement.setString(4,student.getGender());
            preparedStatement.setString(5,student.getEmail());
            preparedStatement.setString(6,student.getPhone());
            preparedStatement.setString(7,student.getFatherName());
            preparedStatement.setString(8,student.getMotherName());
            preparedStatement.setString(9,student.getCountry());
            preparedStatement.setString(10,student.getAddress());
            preparedStatement.setString(11,student.getImagePath());

            //ResultSet resultSet = preparedStatement.executeQuery();

            if( preparedStatement.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"New student added successfully");
            }

            con.close();
        } catch (SQLException e) {
           Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    //check student email address is already exists
    public boolean isEmailExist(String email){
        try{
            Connection con = myConnection.getConnection();

            String sql = "SELECT * FROM Student Where email = ? ";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }
            con.close();
        }catch(SQLException e){
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }

    // check student phone number is already exists
    public boolean isPhoneNumberExist(String phoneNumber){
        try {
            Connection con = myConnection.getConnection();

            String sql = "SELECT * FROM Student WHERE phone = ? ";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,phoneNumber);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                return true;
            }

            con.close();
        }catch (SQLException e){
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }

    public boolean isIdExist(int mssv){
        try {
            Connection con = myConnection.getConnection();

            String sql = "SELECT * FROM Student WHERE id = ? ";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,mssv);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                return true;
            }

            con.close();
        }catch (SQLException e){
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }
    public void getStudentValue(JTable table, String searchValue){
        try {
            String sql = "SELECT * FROM Student WHERE deleted_at is null and " +
                    "concat(id,name,email,phone) like ? " +
                    "   order by id asc ";

            Connection con = getConnection();

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,"%"+searchValue+"%");


            ResultSet rs = preparedStatement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();

            Object[] row ;

            while(rs.next()){
                row = new Object[11];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);

                model.addRow(row);
            }

            con.close();
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    public void removeStudent(Student student){
        int yesNo = JOptionPane.showConfirmDialog(null,"Course and score records will also be deleted","Student Delete",JOptionPane.OK_CANCEL_OPTION);
        if(yesNo==JOptionPane.OK_OPTION){
            try {
                Connection conn = myConnection.getConnection();

                String sql = "UPDATE Student SET " +
                        "deleted_at= ?" +
                        "WHERE id= ?";

                preparedStatement  = conn.prepareStatement(sql);

                preparedStatement.setString(1,java.time.LocalDateTime.now().toString());
                preparedStatement.setInt(2, student.getId());

                if(preparedStatement.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null,"Student remove successfully !!");
                }

                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
            }
        }

    }
    public void updateStudent(Student student){

        try {
            String sql = "UPDATE Student " +
                    "SET name = ?" +
                    ", date_of_birth= ?" +
                    ", gender=?" +
                    ", email= ?" +
                    ", phone=?" +
                    ", father_name=?" +
                    ", mother_name=?" +
                    ", country=?" +
                    ", address=?" +
                    ", image_path=?" +
                    "WHERE id= ?";

            Connection  conn = myConnection.getConnection();

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getDateOfBirth());
            preparedStatement.setString(3,student.getGender());
            preparedStatement.setString(4,student.getEmail());
            preparedStatement.setString(5,student.getPhone());
            preparedStatement.setString(6,student.getFatherName());
            preparedStatement.setString(7,student.getMotherName());
            preparedStatement.setString(8,student.getCountry());
            preparedStatement.setString(9,student.getAddress());
            preparedStatement.setString(10,student.getImagePath());
            preparedStatement.setInt(11, student.getId());

            if (preparedStatement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog( null,"Student data Updated Successfully");
            }

            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    public void exportFileExcel(){
        try {
            String excelFilePath = "Student-export.xlsx";

            Connection conn = myConnection.getConnection();

            String sql = "SELECT * FROM Student WHERE deleted_at IS NULL";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Student");

            writerHeaderLine(resultSet,sheet);

            writeDataLines(resultSet,workbook,sheet);

            FileOutputStream fos = new FileOutputStream(excelFilePath);
            workbook.write(fos);
            workbook.close();

            conn.close();

        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void writerHeaderLine(ResultSet resultSet , XSSFSheet sheet){
        // write header line containing column names
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            Row headerRow = sheet.createRow(0);

            // exclude the first column which is the ID field
            for(int i = 2 ; i <= numberOfColumns ; i++){
                String columnName = metaData.getColumnName(i);
                Cell headerCell = headerRow.createCell(i-2);
                headerCell.setCellValue(columnName);
            }
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    private void writeDataLines(ResultSet resultSet , XSSFWorkbook workbook , XSSFSheet sheet){
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();

            int numberOfColumns = metaData.getColumnCount();

            int rowCount = 0;

            while(resultSet.next()){
                Row row = sheet.createRow(rowCount++);

                for(int i  = 2 ; i<= numberOfColumns ; i++){
                    Object valueObject = resultSet.getObject(i);

                    Cell cell = row.createCell(i-2);

                    if(valueObject instanceof Boolean){
                        cell.setCellValue((Boolean) valueObject) ;
                    }else if (valueObject instanceof Double){
                        cell.setCellValue((double) valueObject);
                    } else if (valueObject instanceof Float){
                        cell.setCellValue((float) valueObject);
                    }
                    else if (valueObject instanceof Date) {
                        cell.setCellValue((Date) valueObject);
                        formatDateCell(workbook, cell);
                    } else cell.setCellValue((String) valueObject);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Student_DAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    private void formatDateCell(XSSFWorkbook workbook, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }

}
