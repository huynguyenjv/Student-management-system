/*
 * Created by JFormDesigner on Thu Mar 16 21:15:47 ICT 2023
 */

package org.swing.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import com.toedter.calendar.*;
import org.jdesktop.swingx.*;
import org.swing.Dao.Course_DAO;
import org.swing.Dao.MarkSheet_DAO;
import org.swing.Dao.Score_DAO;
import org.swing.Dao.Student_DAO;
import org.swing.model.Score;
import org.swing.model.Student;
import org.swing.model.Course;

/**
 * @author ADMIN
 */
public class Home extends JFrame {


    private Student_DAO studentDao = new Student_DAO();
    private Course_DAO courseDao = new Course_DAO();
    private Score_DAO scoreDao = new Score_DAO();
    private MarkSheet_DAO markSheetDao = new MarkSheet_DAO();

    int xx , xy;
    private String imagePath;
    private DefaultTableModel model;
    private int rowIndex;

    public Home() {
        initComponents();
        init();
    }
    public void init(){
        // Student
        tableViewStudent();
        mssv_textField_student.setText(String.valueOf(studentDao.getMax()));
        gender_comboBox_student.setSelectedIndex(-1);

        // Course
        tableViewCourse();
        id_textField_course.setText(String.valueOf(courseDao.getMax()));
        semester_comboBox_course.setSelectedIndex(-1);

        //Score
        tableViewScore();
        id_texField_score.setText(String.valueOf(scoreDao.getMax()));
        
    }

    private void tableViewStudent(){
        studentDao.getStudentValue(student_table,"");
        model = (DefaultTableModel)  student_table.getModel();
        student_table.setRowHeight(30);
        student_table.setShowGrid(true);
        student_table.setGridColor(Color.black);
        student_table.setBackground(Color.white);
    }
    
    private void tableViewCourse(){
        courseDao.getCourseValue(course_table,"");
        model = (DefaultTableModel) course_table.getModel();
        course_table.setRowHeight(30);
        course_table.setShowGrid(true);
        course_table.setGridColor(Color.black);
        course_table.setBackground(Color.white);
    }

    private void tableViewScore(){
        scoreDao.getScoreValue(score_table,"");
        model = (DefaultTableModel) score_table.getModel();
        score_table.setRowHeight(30);
        score_table.setShowGrid(true);
        score_table.setGridColor(Color.black);
        score_table.setBackground(Color.white);
    }

    private void clearStudent(){
        mssv_textField_student.setText(String.valueOf(studentDao.getMax()));
        nameStudent_textField_student.setText(null);
        dayofBirth_jdatechooser_student.setDate(null);
        gender_comboBox_student.setSelectedIndex(-1);
        email_textField_student.setText(null);
        phoneNumber_textField_student.setText(null);
        nameFather_textField_student.setText(null);
        nameMother_textField_student.setText(null);
        country_textField_student.setText(null);
        address_textField_student.setText(null);
        image.setIcon(null);
        student_table.clearSelection();
        imagePath = null;
    }

    public void clearCourse(){
        id_textField_course.setText(String.valueOf(courseDao.getMax()));
        mssv_textField_course.setText(null);
        semester_comboBox_course.removeAllItems();
        course1_comboBox_course.setSelectedIndex(0);
        course2_comboBox_course.setSelectedIndex(0);
        course3_comboBox_course.setSelectedIndex(0);
        course4_comboBox_course.setSelectedIndex(0);
        course5_comboBox_course.setSelectedIndex(0);
    }
    
    public void clearScore(){
        id_texField_score.setText(String.valueOf(scoreDao.getMax()));
        mssv_textField_score.setText(null);
        semester_textField_score.setText(null);
        course1_textField_score.setText(null);
        course2_textField_score.setText(null);
        course3_textField_score.setText(null);
        course4_textField_score.setText(null);
        course5_textField_score.setText(null);
        score1_textField_score.setText("0.0");
        score2_textField_score.setText("0.0");
        score3_textField_score.setText("0.0");
        score4_textField_score.setText("0.0");
        score5_textField_score.setText("0.0");
    }


    public boolean isEmptyStudent(){
        if(nameStudent_textField_student.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Student name is missing");
            return false;
        }
        if(dayofBirth_jdatechooser_student.getDate() == null){
            JOptionPane.showMessageDialog(this,"Student date of birth is missing");
            return false;
        }
        if(dayofBirth_jdatechooser_student.getDate().compareTo(new Date()) > 0){
            JOptionPane.showMessageDialog(this,"No student from the future are allowed");
            return false;
        }
        if(email_textField_student.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Student email address is missing");
            return false;
        }
        if(!email_textField_student.getText().matches("^.+@.+\\..+$")){
            JOptionPane.showMessageDialog(this,"Invalid email address");
            return false;
        }
        if(phoneNumber_textField_student.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Phone number is missing");
            return false;
        }
        String regexPhoneNumber = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";
        if(phoneNumber_textField_student.getText().length() > 10 || phoneNumber_textField_student.getText().length() < 10
                || !phoneNumber_textField_student.getText().matches(regexPhoneNumber)){
            JOptionPane.showMessageDialog(this,"Invalid phone number");
            return false;
        }
        if(nameFather_textField_student.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Father name is missing");
            return false;
        }
        if(nameMother_textField_student.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Mother name is missing");
            return false;
        }
        if(country_textField_student.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Country is missing");
            return false;
        }
        if(address_textField_student.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Address is missing");
            return false;
        }
        return true;
    }

    private void logoutBtn_student(ActionEvent e) {
        int a = JOptionPane.showConfirmDialog(this,"Do you want to logout now ?","Select ",JOptionPane.YES_NO_OPTION);
        if(a == JOptionPane.YES_OPTION){
            this.dispose();
        }
    }

    private void logoutBtn_course(ActionEvent e) {
        int a = JOptionPane.showConfirmDialog(this,"Do you want to logout now ?","Select ",JOptionPane.YES_NO_OPTION);
        if(a == JOptionPane.YES_OPTION){
            this.dispose();
        }
    }

    private void logoutBtn_score(ActionEvent e) {
        int a = JOptionPane.showConfirmDialog(this,"Do you want to logout now ?","Select ",JOptionPane.YES_NO_OPTION);
        if(a == JOptionPane.YES_OPTION){
            this.dispose();
        }
    }

    private void logoutBtn_marksSheet(ActionEvent e) {
        int a = JOptionPane.showConfirmDialog(this,"Do you want to logout now ?","Select ",JOptionPane.YES_NO_OPTION);
        if(a == JOptionPane.YES_OPTION){
            this.dispose();
        }
    }

    private void headerTextMouseDragged(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        this.setLocation(x-xx,y-xy);

    }

    private void headerTextPanelMousePressed(MouseEvent e) {
        xx = e.getX();
        xy= e.getY();

    }

    //                                     STUDENT TAB

    private void browseBtn_student(ActionEvent e) {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image","jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int output = file.showSaveDialog(file);
        if(output ==  JFileChooser.APPROVE_OPTION){
            File selectFile = file.getSelectedFile();
            String path =  selectFile.getAbsolutePath();
            image.setIcon(imageAdjust(path,null));
            imagePath = path;
        }else{
            JOptionPane.showMessageDialog(this,"No image selected");
        }
    }

    private ImageIcon imageAdjust(String path,byte[] pic){
        ImageIcon myImage = null;
        if(path != null){
            myImage = new ImageIcon(path);

        }else{
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(image.getWidth(),image.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        return icon;
    }

    private void insertBtn_student(ActionEvent e) {
        if(isEmptyStudent()){
            if(!studentDao.isEmailExist(email_textField_student.getText())
            || !studentDao.isPhoneNumberExist(phoneNumber_textField_student.getText())){
                int id = studentDao.getMax();
                String name = nameStudent_textField_student.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = dateFormat.format(dayofBirth_jdatechooser_student.getDate());
                String gender =gender_comboBox_student.getSelectedItem().toString();
                String email = email_textField_student.getText();
                String phoneNumber = phoneNumber_textField_student.getText();
                String fatherName = nameFather_textField_student.getText();
                String motherName = nameMother_textField_student.getText();
                String country = country_textField_student.getText();
                String address = address_textField_student.getText();

                Student student = new Student(id,name,date,gender,email,phoneNumber,fatherName,motherName,country,address,imagePath);

                studentDao.insertStudent(student);
                clearStudent();
                student_table.setModel(new DefaultTableModel(null, new Object[]{"MSSV","Họ và tên","Ngày sinh","Giới tính","Email","SĐT",
                "Họ và tên Ba","Họ và tên Mẹ","Quê quán","Chỗ ở hiện tại","Image Path"}));
                studentDao.getStudentValue(student_table,"");
            }else{
                if(studentDao.isEmailExist(email_textField_student.getText())){
                    JOptionPane.showMessageDialog(this,"This email is already exists");
                }
                else if(studentDao.isPhoneNumberExist(phoneNumber_textField_student.getText())){
                    JOptionPane.showMessageDialog(this,"This phone number is already exists");
                }
            }
        }
    }

    private void updateBtn_student(ActionEvent e) {
        if(isEmptyStudent()){
            int  id = Integer.parseInt(mssv_textField_student.getText());
            if(studentDao.isIdExist(id)){
                if(!check()){
                    String name = nameStudent_textField_student.getText();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = dateFormat.format(dayofBirth_jdatechooser_student.getDate());
                    String gender =gender_comboBox_student.getSelectedItem().toString();
                    String email = email_textField_student.getText();
                    String phoneNumber = phoneNumber_textField_student.getText();
                    String fatherName = nameFather_textField_student.getText();
                    String motherName = nameMother_textField_student.getText();
                    String country = country_textField_student.getText();
                    String address = address_textField_student.getText();

                    Student student = new Student(id,name,date,gender,email,phoneNumber,fatherName,motherName,country,address,imagePath);
                    studentDao.updateStudent(student);
                    clearStudent();
                    student_table.setModel(new DefaultTableModel(null, new Object[]{"MSSV","Họ và tên","Ngày sinh","Giới tính","Email","SĐT",
                            "Họ và tên Ba","Họ và tên Mẹ","Quê quán","Chỗ ở hiện tại","Image Path"}));
                    studentDao.getStudentValue(student_table,"");
                }
            }else{
                JOptionPane.showMessageDialog(this,"Student id doesn't exist");
            }


        }
    }

    private void removeBtn_student(ActionEvent e) {
        if(isEmptyStudent()){
            String id = mssv_textField_student.getText();

            Student student = new Student();
            student.setId(Integer.parseInt(id));

            studentDao.removeStudent(student);
            clearStudent();
            student_table.setModel(new DefaultTableModel(null, new Object[]{"MSSV","Họ và tên","Ngày sinh","Giới tính","Email","SĐT",
                    "Họ và tên Ba","Họ và tên Mẹ","Quê quán","Chỗ ở hiện tại","Image Path"}));
            studentDao.getStudentValue(student_table,"");
        }
    }

    private void printBtn_student(ActionEvent e)  {

        try {
            MessageFormat header = new MessageFormat("Student information");
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            student_table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    private void searchBtn_student(ActionEvent e) {
        if(search_textField_student.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter student id");
        }else{
            if(!studentDao.isStudentInfoExist(search_textField_student.getText())){
                student_table.setModel( new DefaultTableModel(null , new Object[]{"MSSV","Họ và tên","Ngày sinh","Giới tính","Email","SĐT",
                        "Họ và tên Ba","Họ và tên Mẹ","Quê quán","Chỗ ở hiện tại","Image Path"}));
                studentDao.getStudentValue(student_table,"");
            }else {
                student_table.setModel(new DefaultTableModel(null, new Object[]{"MSSV", "Họ và tên", "Ngày sinh", "Giới tính", "Email", "SĐT",
                        "Họ và tên Ba", "Họ và tên Mẹ", "Quê quán", "Chỗ ở hiện tại", "Image Path"}));
                studentDao.getStudentValue(student_table, search_textField_student.getText());
            }
        }
    }

    private void refreshBtn_student(ActionEvent e) {
        search_textField_student.setText(null);
        student_table.setModel( new DefaultTableModel(null , new Object[]{"MSSV","Họ và tên","Ngày sinh","Giới tính","Email","SĐT",
                "Họ và tên Ba","Họ và tên Mẹ","Quê quán","Chỗ ở hiện tại","Image Path"}));
        studentDao.getStudentValue(student_table,"");
    }

    private void clearBtn_student(ActionEvent e) {
        clearStudent();
    }

    private void phoneNumber_textField_studentKeyTyped(KeyEvent e) {
        if(!Character.isDigit(e.getKeyChar())){
            e.consume();
        }
    }

    private void student_tableMouseClicked(MouseEvent e) {
        model = (DefaultTableModel) student_table.getModel();
        rowIndex = student_table.getSelectedRow();
        mssv_textField_student.setText(model.getValueAt(rowIndex,0).toString());
        nameStudent_textField_student.setText(model.getValueAt(rowIndex,1).toString());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(rowIndex,2).toString());
            dayofBirth_jdatechooser_student.setDate(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        String gender = model.getValueAt(rowIndex,3).toString();
        if(gender.equals("Nam")){
            gender_comboBox_student.setSelectedIndex(1);
        }else if(gender.equals("Nu")){
            gender_comboBox_student.setSelectedIndex(2);
        }
        email_textField_student.setText(model.getValueAt(rowIndex,4).toString());
        phoneNumber_textField_student.setText(model.getValueAt(rowIndex,5).toString());
        nameFather_textField_student.setText(model.getValueAt(rowIndex,6).toString());
        nameMother_textField_student.setText(model.getValueAt(rowIndex,7).toString());
        country_textField_student.setText(model.getValueAt(rowIndex,8).toString());
        address_textField_student.setText(model.getValueAt(rowIndex,9).toString());
        String path = model.getValueAt(rowIndex,10).toString();
        imagePath = path;
        image.setIcon(imageAdjust(path,null)); // get image path and called imageAdjust method convert path to ImageIcon ..
    }

    public boolean check(){
        String newEmail = email_textField_student.getText();
        String newPhone = phoneNumber_textField_student.getText();
        String oldEmail = model.getValueAt(rowIndex,4).toString();
        String oldPhone = model.getValueAt(rowIndex,5).toString();
        if(newEmail.equals(oldEmail) && newPhone.equals(oldPhone)){
            return false;
        }else{
            if(!newEmail.equals(oldEmail)){
                boolean result = studentDao.isEmailExist(newEmail);
                if(result){
                    JOptionPane.showMessageDialog(this,"This email is already exist");
                }
                return result;
            }
            if (!newPhone.equals(oldPhone)) {
                boolean result = studentDao.isPhoneNumberExist(newPhone);
                if(result){
                    JOptionPane.showMessageDialog(this,"This phone number is already exists");
                }
                return result;
            }
        }
        return false;
    }

    
    //                                   COURSE CODE

    private void clearBtn_course(ActionEvent e) {
        clearCourse();
    }


    private void searchInfBtn(ActionEvent e) {
        if(mssv_textField_searchInf.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter a student id ");
        }else{
            int id = Integer.parseInt(mssv_textField_searchInf.getText());
            Course course = new Course();
            course.setStudentID(id);
            if(courseDao.getId(course)){
                semester_comboBox_course.removeAllItems();
                int semester = courseDao.countSemester(course);
                if(semester >= 0){
                    for(int i = 1 ; i <= semester+1 ; i++){
                        String count = String.valueOf(i);
                        semester_comboBox_course.addItem(count);
                    }
                }
            }
        }
    }
    
    private void saveBtn_course(ActionEvent e) {
        if(mssv_textField_course.getText().isEmpty() || semester_comboBox_course.getItemCount() == 0){
            JOptionPane.showMessageDialog(this,"Student id or semester number is missing ");
        }else{
            int id = courseDao.getMax();
            int studentId = Integer.parseInt(mssv_textField_course.getText());
            String semester = semester_comboBox_course.getSelectedItem().toString();
            String course1 = course1_comboBox_course.getSelectedItem().toString();
            String course2 = course2_comboBox_course.getSelectedItem().toString();
            String course3 = course3_comboBox_course.getSelectedItem().toString();
            String course4 = course4_comboBox_course.getSelectedItem().toString();
            String course5 = course5_comboBox_course.getSelectedItem().toString();

            Course  course = new Course(id,studentId,semester,course1, course2, course3, course4, course5);

            if(courseDao.isSemesterExist(course.getStudentID(), semester)){
                int confirm = JOptionPane.showConfirmDialog(this, "This student has already taken semester \n" + course.getSemester() +
                        " Do you want to save ? ", "Note", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    courseDao.updateCourse(course);
                    clearCourse();
                    course_table.setModel(new DefaultTableModel(null, new Object[]{"ID", "MSSV", "Học Kì", "Môn học 1", "Môn học 2", "Môn học 3"
                            , "Môn học 4", "Môn học 5"}));

                    courseDao.getCourseValue(course_table, "");
                }
            }else{
                if(courseDao.isCourseExist(course,"course1",course.getCourse1())){
                    JOptionPane.showMessageDialog(this,"This student has already taken "+ course.getCourse1() +" course");
                }else{
                    if(courseDao.isCourseExist(course,"course2",course.getCourse2())){
                        JOptionPane.showMessageDialog(this,"This student has already taken "+ course.getCourse2()+ "course" );
                    }else{
                        if(courseDao.isCourseExist(course,"course3",course.getCourse3())){
                            JOptionPane.showMessageDialog(this,"This student has already taken "+ course.getCourse3() +" course");
                        }else{
                            if(courseDao.isCourseExist(course,"course4",course.getCourse4())){
                                JOptionPane.showMessageDialog(this,"This student has already taken "+ course.getCourse4() +" course");
                            }else{
                                if(courseDao.isCourseExist(course,"course5",course.getCourse5())){
                                    JOptionPane.showMessageDialog(this,"This student has already taken "+ course.getCourse5() +" course");
                                }else{
                                    courseDao.insertCourse(course);
                                    clearCourse();
                                    course_table.setModel( new DefaultTableModel(null , new Object[]{"ID","MSSV","Học Kì","Môn học 1","Môn học 2","Môn học 3"
                                    ,"Môn học 4","Môn học 5"}));

                                    courseDao.getCourseValue(course_table,"");
                                    
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void printBtn_course(ActionEvent e) {
        try {
            MessageFormat header = new MessageFormat("Course information");
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            student_table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }


    private void searchBtn_course(ActionEvent e) {
        if(search_textField_course.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter mssv of student !!");
        }else{
            if(!courseDao.isCourseInfoExist(search_textField_course.getText())){
                course_table.setModel( new DefaultTableModel(null , new Object[]{"ID","MSSV","Học Kì","Môn học 1","Môn học 2","Môn học 3"
                        ,"Môn học 4","Môn học 5"}));

                courseDao.getCourseValue(course_table,"");
            }else{
                course_table.setModel( new DefaultTableModel(null , new Object[]{"ID","MSSV","Học Kì","Môn học 1","Môn học 2","Môn học 3"
                        ,"Môn học 4","Môn học 5"}));

                courseDao.getCourseValue(course_table,search_textField_course.getText());
            }

        }
    }

    private void refreshBtn_course(ActionEvent e) {
        search_textField_course.setText(null);
        course_table.setModel( new DefaultTableModel(null , new Object[]{"ID","MSSV","Học Kì","Môn học 1","Môn học 2","Môn học 3"
                ,"Môn học 4","Môn học 5"}));

        courseDao.getCourseValue(course_table,"");
    }

    
    //                            SCORE CODE


    private void clearBtn_score(ActionEvent e) {
        clearScore();
    }

    private void searchBtn_searchInf_score(ActionEvent e) {
        Score score = new Score();

        if(mssvSearchInf_textField_score.getText().isEmpty() || semesterSearchInf_textField_score.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter missing information ! ");
        }else{
            score.setStudentID(Integer.parseInt(mssvSearchInf_textField_score.getText()));
            score.setSemester(semesterSearchInf_textField_score.getText());
            scoreDao.getDetail(score);
        }
    }

    private void saveBtn_score(ActionEvent e) {
        if (score1_textField_score.getText().isEmpty() || score2_textField_score.getText().isEmpty() || score3_textField_score.getText().isEmpty()
                || score4_textField_score.getText().isEmpty() || score5_textField_score.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Score is missing ");
        } else {
            int id = scoreDao.getMax();
            int studentId = Integer.parseInt(mssv_textField_score.getText());
            String semester = semester_textField_score.getText();
            String course1 = course1_textField_score.getText();
            String course2 = course2_textField_score.getText();
            String course3 = course3_textField_score.getText();
            String course4 = course4_textField_score.getText();
            String course5 = course5_textField_score.getText();
            Double score1 = Double.parseDouble(score1_textField_score.getText());
            Double score2 = Double.parseDouble(score2_textField_score.getText());
            Double score3 = Double.parseDouble(score3_textField_score.getText());
            Double score4 = Double.parseDouble(score4_textField_score.getText());
            Double score5 = Double.parseDouble(score5_textField_score.getText());

            Score score = new Score(id, studentId, semester, course1, course2, course3, course4, course5, score1, score2, score3, score4, score5);
            if (scoreDao.isSemesterExist(score)) {
                JOptionPane.showMessageDialog(this, "This Semester already exists ");
            } else {
                scoreDao.insertScore(score);
                clearScore();
                score_table.setModel(new DefaultTableModel(null, new Object[]{"ID", "MSSV", "Học Kì", "Môn học 1", "Điểm 1", "Môn học 2", "Điểm 2", "Môn học 3"
                        , "Điểm 3", "Môn học 4", "Điểm 4", "Môn học 5", "Điểm 5"}));

                scoreDao.getScoreValue(score_table, "");

            }

        }
    }

    private void score_tableMouseClicked(MouseEvent e) {
        DefaultTableModel model = (DefaultTableModel) score_table.getModel();
        rowIndex = score_table.getSelectedRow();

        id_texField_score.setText(model.getValueAt(rowIndex,0).toString());
        mssv_textField_score.setText(model.getValueAt(rowIndex,1).toString());
        semester_textField_score.setText(model.getValueAt(rowIndex,2).toString());
        course1_textField_score.setText(model.getValueAt(rowIndex,3).toString());
        score1_textField_score.setText(model.getValueAt(rowIndex,4).toString());
        course2_textField_score.setText(model.getValueAt(rowIndex,5).toString());
        score2_textField_score.setText(model.getValueAt(rowIndex,6).toString());
        course3_textField_score.setText(model.getValueAt(rowIndex,7).toString());
        score3_textField_score.setText(model.getValueAt(rowIndex,8).toString());
        course4_textField_score.setText(model.getValueAt(rowIndex,9).toString());
        score4_textField_score.setText(model.getValueAt(rowIndex,10).toString());
        course5_textField_score.setText(model.getValueAt(rowIndex,11).toString());
        score5_textField_score.setText(model.getValueAt(rowIndex,12).toString());
    }

    private void updateBtn_score(ActionEvent e) {
        int id = Integer.parseInt(id_texField_score.getText());
        int studentId = Integer.parseInt(mssv_textField_score.getText());
        String semester = semester_textField_score.getText();
        String course1 = course1_textField_score.getText();
        String course2 = course2_textField_score.getText();
        String course3 = course3_textField_score.getText();
        String course4 = course4_textField_score.getText();
        String course5 = course5_textField_score.getText();
        Double score1 = Double.parseDouble(score1_textField_score.getText());
        Double score2 = Double.parseDouble(score2_textField_score.getText());
        Double score3 = Double.parseDouble(score3_textField_score.getText());
        Double score4 = Double.parseDouble(score4_textField_score.getText());
        Double score5 = Double.parseDouble(score5_textField_score.getText());

        Score score = new Score(id, studentId, semester, course1, course2, course3, course4, course5, score1, score2, score3, score4, score5);
        scoreDao.updateScore(score);
        clearScore();
        score_table.setModel(new DefaultTableModel(null, new Object[]{"ID", "MSSV", "Học Kì", "Môn học 1", "Điểm 1", "Môn học 2", "Điểm 2", "Môn học 3"
                , "Điểm 3", "Môn học 4", "Điểm 4", "Môn học 5", "Điểm 5"}));

        scoreDao.getScoreValue(score_table, "");
    }


    private void printBtn_score(ActionEvent e) {
        try {
            MessageFormat header = new MessageFormat("SCORE COURSE OF STUDENT");
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            student_table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    private void searchBtn_score(ActionEvent e) {
        if(search_textField_score.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter mssv of student");
        }else{
            if(!scoreDao.isScoreInfoExist(search_textField_score.getText())) {
                score_table.setModel(new DefaultTableModel(null, new Object[]{"ID", "MSSV", "Học Kì", "Môn học 1", "Điểm 1", "Môn học 2", "Điểm 2", "Môn học 3"
                        , "Điểm 3", "Môn học 4", "Điểm 4", "Môn học 5", "Điểm 5"}));
                scoreDao.getScoreValue(score_table,"");
            }else{
                String searchValue = search_textField_score.getText();
                score_table.setModel(new DefaultTableModel(null, new Object[]{"ID", "MSSV", "Học Kì", "Môn học 1", "Điểm 1", "Môn học 2", "Điểm 2", "Môn học 3"
                        , "Điểm 3", "Môn học 4", "Điểm 4", "Môn học 5", "Điểm 5"}));
                scoreDao.getScoreValue(score_table, searchValue);
            }
        }
    }

    private void refreshBtn_score(ActionEvent e) {
        search_textField_score.setText(null);
        score_table.setModel(new DefaultTableModel(null, new Object[]{"ID", "MSSV", "Học Kì", "Môn học 1", "Điểm 1", "Môn học 2", "Điểm 2", "Môn học 3"
                , "Điểm 3", "Môn học 4", "Điểm 4", "Môn học 5", "Điểm 5"}));
        scoreDao.getScoreValue(score_table, "");
    }


    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Nguyễn Huy
        headerPanel = new JPanel();
        headerTextPanel = new JPanel();
        headerText = new JLabel();
        tabbedPane1 = new JTabbedPane();
        student = new JPanel();
        Information = new JPanel();
        mssv_textField_student = new JTextField();
        nameStudent_textField_student = new JTextField();
        gender_comboBox_student = new JComboBox<>();
        email_textField_student = new JTextField();
        phoneNumber_textField_student = new JTextField();
        nameFather_textField_student = new JTextField();
        nameMother_textField_student = new JTextField();
        country_textField_student = new JTextField();
        address_textField_student = new JTextField();
        mssv_labe_studentl = new JLabel();
        nameStudent_label_student = new JLabel();
        dayofbirth_label_student = new JLabel();
        gender_label_student = new JLabel();
        email_label_student = new JLabel();
        phoneNumber_label_student = new JLabel();
        nameFather_label_student = new JLabel();
        nameMother_label_student = new JLabel();
        country_label_student = new JLabel();
        address_label_student = new JLabel();
        image_panel_student = new JPanel();
        browseBtn_student = new JButton();
        showImage_panel_student = new JPanel();
        image = new JLabel();
        dayofBirth_jdatechooser_student = new JDateChooser();
        content_panel_student = new JPanel();
        searchStudentPanel_student = new JPanel();
        search_textField_student = new JTextField();
        search_label_student = new JLabel();
        searchBtn_student = new JButton();
        refreshBtn_student = new JButton();
        table_panel_student = new JPanel();
        scrollPane1 = new JScrollPane();
        student_table = new JTable();
        btnGroup_Student = new JPanel();
        insertBtn_student = new JButton();
        updateBtn_student = new JButton();
        removeBtn_student = new JButton();
        printBtn_student = new JButton();
        clearBtn_student = new JButton();
        logoutBtn_student = new JButton();
        Course = new JPanel();
        Information_Course_Panel = new JPanel();
        search_Infro_panel = new JPanel();
        mssv_label_SearchInf = new JLabel();
        mssv_textField_searchInf = new JTextField();
        searchInfBtn = new JButton();
        id_label_course = new JLabel();
        mssv_label_course = new JLabel();
        semester_label_course = new JLabel();
        course1_label_course = new JLabel();
        course2_label_course = new JLabel();
        course3_label_course = new JLabel();
        course4_label_course = new JLabel();
        course5_label_course = new JLabel();
        id_textField_course = new JTextField();
        mssv_textField_course = new JTextField();
        semester_comboBox_course = new JComboBox<>();
        course1_comboBox_course = new JComboBox<>();
        course2_comboBox_course = new JComboBox<>();
        course3_comboBox_course = new JComboBox<>();
        course4_comboBox_course = new JComboBox<>();
        course5_comboBox_course = new JComboBox<>();
        content_panel_course = new JPanel();
        search_panel_course = new JPanel();
        search_textField_course = new JTextField();
        search_label_course = new JLabel();
        searchBtn_course = new JButton();
        refreshBtn_course = new JButton();
        table_panel_course = new JPanel();
        scrollPane2 = new JScrollPane();
        course_table = new JTable();
        btnGroup_course = new JPanel();
        saveBtn_course = new JButton();
        printBtn_course = new JButton();
        clearBtn_course = new JButton();
        logoutBtn_course = new JButton();
        Score = new JPanel();
        information_score = new JPanel();
        searchInf_score = new JPanel();
        mssvSearchInf_label_score = new JLabel();
        mssvSearchInf_textField_score = new JTextField();
        searchBtn_searchInf_score = new JButton();
        semesterSearchInf_label_score = new JLabel();
        semesterSearchInf_textField_score = new JTextField();
        id_label_score = new JLabel();
        mssv_label_score = new JLabel();
        semester_label_score = new JLabel();
        course1_label_score = new JLabel();
        course2_label_score = new JLabel();
        course3_label_score = new JLabel();
        course4_label_score = new JLabel();
        course5_label_score = new JLabel();
        id_texField_score = new JTextField();
        mssv_textField_score = new JTextField();
        semester_textField_score = new JTextField();
        course1_textField_score = new JTextField();
        course2_textField_score = new JTextField();
        course3_textField_score = new JTextField();
        course4_textField_score = new JTextField();
        course5_textField_score = new JTextField();
        score1_textField_score = new JTextField();
        score2_textField_score = new JTextField();
        score3_textField_score = new JTextField();
        score4_textField_score = new JTextField();
        score5_textField_score = new JTextField();
        contentPanel_score = new JPanel();
        searchPanel_score = new JPanel();
        search_textField_score = new JTextField();
        search_label_score = new JLabel();
        searchBtn_score = new JButton();
        refreshBtn_score = new JButton();
        tablePanel_score = new JPanel();
        scrollPane3 = new JScrollPane();
        score_table = new JTable();
        btnGroup_score = new JPanel();
        updateBtn_score = new JButton();
        printBtn_score = new JButton();
        clearBtn_score = new JButton();
        logoutBtn_score = new JButton();
        saveBtn_score = new JButton();
        Marks_sheet = new JPanel();
        information_marksSheet = new JPanel();
        mssv_searchInf_panel = new JPanel();
        mssv_searchInf_label_marksSheet = new JLabel();
        mssv_searchInf_textField_marksSheet = new JTextField();
        searchBtn_searchInf_marksSheet = new JButton();
        result_infoMarksSheet_panel = new JPanel();
        result_marksSheet = new JLabel();
        content_panel_marksSheet = new JPanel();
        panel26 = new JPanel();
        scrollPane4 = new JScrollPane();
        table4 = new JTable();
        panel27 = new JPanel();
        updateBtn_marksSheet = new JButton();
        printBtn_marksSheet = new JButton();
        clearBtn_marksSheet = new JButton();
        logoutBtn_marksSheet = new JButton();
        saveBtn_marksSheet = new JButton();

        //======== this ========
        setVisible(true);
        setResizable(false);
        setMaximumSize(new Dimension(-2147483647, 2147483647));
        setTitle("Student Management System");
        var contentPane = getContentPane();

        //======== headerPanel ========
        {
            headerPanel.setBackground(Color.white);
            headerPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            headerPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0
            ,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
            ,new java.awt.Font("Dialo\u0067",java.awt.Font.BOLD,12),java.awt.Color.red),
            headerPanel. getBorder()));headerPanel. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException();}});

            //======== headerTextPanel ========
            {
                headerTextPanel.setBackground(Color.lightGray);
                headerTextPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        headerTextPanelMousePressed(e);
                    }
                });

                //---- headerText ----
                headerText.setText("STUDENT MANAGEMENT SYSTEM");
                headerText.setForeground(Color.black);
                headerText.setHorizontalAlignment(SwingConstants.CENTER);
                headerText.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
                headerText.setBackground(Color.white);
                headerText.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        headerTextMouseDragged(e);
                    }
                });

                GroupLayout headerTextPanelLayout = new GroupLayout(headerTextPanel);
                headerTextPanel.setLayout(headerTextPanelLayout);
                headerTextPanelLayout.setHorizontalGroup(
                    headerTextPanelLayout.createParallelGroup()
                        .addGroup(headerTextPanelLayout.createSequentialGroup()
                            .addComponent(headerText, GroupLayout.DEFAULT_SIZE, 1458, Short.MAX_VALUE)
                            .addContainerGap())
                );
                headerTextPanelLayout.setVerticalGroup(
                    headerTextPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, headerTextPanelLayout.createSequentialGroup()
                            .addContainerGap(24, Short.MAX_VALUE)
                            .addComponent(headerText, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                            .addGap(19, 19, 19))
                );
            }

            GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
            headerPanel.setLayout(headerPanelLayout);
            headerPanelLayout.setHorizontalGroup(
                headerPanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(headerTextPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
            headerPanelLayout.setVerticalGroup(
                headerPanelLayout.createParallelGroup()
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(headerTextPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
            );
        }

        //======== tabbedPane1 ========
        {
            tabbedPane1.setBackground(Color.darkGray);
            tabbedPane1.setBorder(new BevelBorder(BevelBorder.RAISED));

            //======== student ========
            {
                student.setBackground(new Color(0xcccccc));

                //======== Information ========
                {
                    Information.setBorder(new LineBorder(Color.darkGray, 4, true));
                    Information.setBackground(Color.white);
                    Information.setForeground(new Color(0xcccccc));
                    Information.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- mssv_textField_student ----
                    mssv_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    mssv_textField_student.setEditable(false);
                    mssv_textField_student.setDisabledTextColor(new Color(0x999999));
                    mssv_textField_student.setToolTipText("Enter number here !");
                    mssv_textField_student.setBackground(new Color(0x999999));

                    //---- nameStudent_textField_student ----
                    nameStudent_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- gender_comboBox_student ----
                    gender_comboBox_student.setModel(new DefaultComboBoxModel<>(new String[] {
                        "None",
                        "Nam",
                        "Nu"
                    }));
                    gender_comboBox_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- email_textField_student ----
                    email_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- phoneNumber_textField_student ----
                    phoneNumber_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- nameFather_textField_student ----
                    nameFather_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- nameMother_textField_student ----
                    nameMother_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- country_textField_student ----
                    country_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- address_textField_student ----
                    address_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- mssv_labe_studentl ----
                    mssv_labe_studentl.setText("MSSV : ");
                    mssv_labe_studentl.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    mssv_labe_studentl.setForeground(Color.black);

                    //---- nameStudent_label_student ----
                    nameStudent_label_student.setText("H\u1ecd v\u00e0 t\u00ean : ");
                    nameStudent_label_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    nameStudent_label_student.setForeground(Color.black);

                    //---- dayofbirth_label_student ----
                    dayofbirth_label_student.setText("Ng\u00e0y sinh : ");
                    dayofbirth_label_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    dayofbirth_label_student.setForeground(Color.black);

                    //---- gender_label_student ----
                    gender_label_student.setText("Gi\u1edbi t\u00ednh :");
                    gender_label_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    gender_label_student.setForeground(Color.black);

                    //---- email_label_student ----
                    email_label_student.setText("Email :");
                    email_label_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    email_label_student.setForeground(Color.black);

                    //---- phoneNumber_label_student ----
                    phoneNumber_label_student.setText("S\u1ed1 \u0111i\u1ec7n tho\u1ea1i :");
                    phoneNumber_label_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    phoneNumber_label_student.setForeground(Color.black);

                    //---- nameFather_label_student ----
                    nameFather_label_student.setText("H\u1ecd v\u00e0 t\u00ean Ba :");
                    nameFather_label_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    nameFather_label_student.setForeground(Color.black);

                    //---- nameMother_label_student ----
                    nameMother_label_student.setText("H\u1ecd v\u00e0 t\u00ean M\u1eb9 :");
                    nameMother_label_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    nameMother_label_student.setForeground(Color.black);

                    //---- country_label_student ----
                    country_label_student.setText("Qu\u00ea qu\u00e1n :");
                    country_label_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    country_label_student.setForeground(Color.black);

                    //---- address_label_student ----
                    address_label_student.setText("Ch\u1ed7 \u1edf hi\u1ec7n t\u1ea1i :");
                    address_label_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    address_label_student.setForeground(Color.black);

                    //======== image_panel_student ========
                    {
                        image_panel_student.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //---- browseBtn_student ----
                        browseBtn_student.setText("Browse");
                        browseBtn_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                        browseBtn_student.addActionListener(e -> browseBtn_student(e));

                        //======== showImage_panel_student ========
                        {
                            showImage_panel_student.setBorder(LineBorder.createBlackLineBorder());

                            GroupLayout showImage_panel_studentLayout = new GroupLayout(showImage_panel_student);
                            showImage_panel_student.setLayout(showImage_panel_studentLayout);
                            showImage_panel_studentLayout.setHorizontalGroup(
                                showImage_panel_studentLayout.createParallelGroup()
                                    .addComponent(image, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            );
                            showImage_panel_studentLayout.setVerticalGroup(
                                showImage_panel_studentLayout.createParallelGroup()
                                    .addComponent(image, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            );
                        }

                        GroupLayout image_panel_studentLayout = new GroupLayout(image_panel_student);
                        image_panel_student.setLayout(image_panel_studentLayout);
                        image_panel_studentLayout.setHorizontalGroup(
                            image_panel_studentLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, image_panel_studentLayout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addComponent(browseBtn_student, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                    .addComponent(showImage_panel_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                        );
                        image_panel_studentLayout.setVerticalGroup(
                            image_panel_studentLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, image_panel_studentLayout.createSequentialGroup()
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(browseBtn_student, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39))
                                .addGroup(image_panel_studentLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(showImage_panel_student, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                    }

                    GroupLayout InformationLayout = new GroupLayout(Information);
                    Information.setLayout(InformationLayout);
                    InformationLayout.setHorizontalGroup(
                        InformationLayout.createParallelGroup()
                            .addGroup(InformationLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(image_panel_student, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(InformationLayout.createSequentialGroup()
                                        .addGroup(InformationLayout.createParallelGroup()
                                            .addGroup(InformationLayout.createParallelGroup()
                                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addGroup(InformationLayout.createParallelGroup()
                                                        .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                            .addComponent(nameStudent_label_student, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(mssv_labe_studentl, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(dayofbirth_label_student, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(phoneNumber_label_student, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(nameFather_label_student, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(nameMother_label_student, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(country_label_student, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(address_label_student, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(email_label_student, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(gender_label_student, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(address_textField_student, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                            .addComponent(mssv_textField_student, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                            .addComponent(nameStudent_textField_student, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                            .addComponent(email_textField_student, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                            .addComponent(nameFather_textField_student, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                            .addComponent(country_textField_student, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                            .addComponent(nameMother_textField_student, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                            .addComponent(phoneNumber_textField_student, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                            .addComponent(gender_comboBox_student, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dayofBirth_jdatechooser_student, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))))
                                .addContainerGap(39, Short.MAX_VALUE))
                    );
                    InformationLayout.setVerticalGroup(
                        InformationLayout.createParallelGroup()
                            .addGroup(InformationLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(mssv_textField_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mssv_labe_studentl))
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(nameStudent_textField_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameStudent_label_student))
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup()
                                    .addComponent(dayofbirth_label_student)
                                    .addComponent(dayofBirth_jdatechooser_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(gender_comboBox_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gender_label_student))
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(email_textField_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email_label_student))
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(phoneNumber_label_student)
                                    .addComponent(phoneNumber_textField_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(nameFather_textField_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameFather_label_student))
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(nameMother_textField_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameMother_label_student))
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(country_textField_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(country_label_student))
                                .addGap(18, 18, 18)
                                .addGroup(InformationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(address_textField_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(address_label_student))
                                .addGap(18, 18, 18)
                                .addComponent(image_panel_student, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                }

                //======== content_panel_student ========
                {
                    content_panel_student.setBorder(new LineBorder(Color.darkGray, 4, true));

                    //======== searchStudentPanel_student ========
                    {
                        searchStudentPanel_student.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //---- search_textField_student ----
                        search_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                        //---- search_label_student ----
                        search_label_student.setText("T\u00ecm ki\u1ebfm :");
                        search_label_student.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        search_label_student.setHorizontalAlignment(SwingConstants.CENTER);

                        //---- searchBtn_student ----
                        searchBtn_student.setText("Search");
                        searchBtn_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                        searchBtn_student.addActionListener(e -> searchBtn_student(e));

                        //---- refreshBtn_student ----
                        refreshBtn_student.setText("Refresh");
                        refreshBtn_student.setFont(new Font("Times New Roman", Font.BOLD, 14));
                        refreshBtn_student.addActionListener(e -> refreshBtn_student(e));

                        GroupLayout searchStudentPanel_studentLayout = new GroupLayout(searchStudentPanel_student);
                        searchStudentPanel_student.setLayout(searchStudentPanel_studentLayout);
                        searchStudentPanel_studentLayout.setHorizontalGroup(
                            searchStudentPanel_studentLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, searchStudentPanel_studentLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(search_label_student, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                    .addComponent(search_textField_student, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(searchBtn_student, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(refreshBtn_student, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23))
                        );
                        searchStudentPanel_studentLayout.setVerticalGroup(
                            searchStudentPanel_studentLayout.createParallelGroup()
                                .addGroup(searchStudentPanel_studentLayout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(searchStudentPanel_studentLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(search_label_student, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(search_textField_student, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchBtn_student, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(refreshBtn_student, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(32, Short.MAX_VALUE))
                        );
                    }

                    //======== table_panel_student ========
                    {
                        table_panel_student.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //======== scrollPane1 ========
                        {

                            //---- student_table ----
                            student_table.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                    "MSSV", "H\u1ecd v\u00e0 t\u00ean", "Ng\u00e0y sinh", "Gi\u1edbi t\u00ednh", "Email", "S\u0110T", "H\u1ecd v\u00e0 t\u00ean Ba", "H\u1ecd v\u00e0 t\u00ean M\u1eb9", "Qu\u00ea qu\u00e1n", "Ch\u1ed7 \u1edf hi\u00ean t\u1ea1i", "Image Path"
                                }
                            ));
                            {
                                TableColumnModel cm = student_table.getColumnModel();
                                cm.getColumn(9).setMinWidth(100);
                                cm.getColumn(10).setMinWidth(90);
                            }
                            student_table.setBackground(Color.lightGray);
                            student_table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            student_table.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    student_tableMouseClicked(e);
                                }
                            });
                            scrollPane1.setViewportView(student_table);
                        }

                        //======== btnGroup_Student ========
                        {
                            btnGroup_Student.setBorder(new LineBorder(Color.darkGray, 4, true));

                            //---- insertBtn_student ----
                            insertBtn_student.setText("Th\u00eam m\u1edbi");
                            insertBtn_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            insertBtn_student.addActionListener(e -> insertBtn_student(e));

                            //---- updateBtn_student ----
                            updateBtn_student.setText("C\u1eadp nh\u1eadt");
                            updateBtn_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            updateBtn_student.addActionListener(e -> updateBtn_student(e));

                            //---- removeBtn_student ----
                            removeBtn_student.setText("Xo\u00e1");
                            removeBtn_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            removeBtn_student.addActionListener(e -> removeBtn_student(e));

                            //---- printBtn_student ----
                            printBtn_student.setText("Print");
                            printBtn_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            printBtn_student.addActionListener(e -> printBtn_student(e));

                            //---- clearBtn_student ----
                            clearBtn_student.setText("Clear");
                            clearBtn_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            clearBtn_student.addActionListener(e -> clearBtn_student(e));

                            //---- logoutBtn_student ----
                            logoutBtn_student.setText("Logout");
                            logoutBtn_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            logoutBtn_student.addActionListener(e -> logoutBtn_student(e));

                            GroupLayout btnGroup_StudentLayout = new GroupLayout(btnGroup_Student);
                            btnGroup_Student.setLayout(btnGroup_StudentLayout);
                            btnGroup_StudentLayout.setHorizontalGroup(
                                btnGroup_StudentLayout.createParallelGroup()
                                    .addGroup(btnGroup_StudentLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(insertBtn_student, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(updateBtn_student, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(removeBtn_student, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54)
                                        .addComponent(printBtn_student, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                        .addComponent(clearBtn_student, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(logoutBtn_student, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23))
                            );
                            btnGroup_StudentLayout.setVerticalGroup(
                                btnGroup_StudentLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, btnGroup_StudentLayout.createSequentialGroup()
                                        .addContainerGap(16, Short.MAX_VALUE)
                                        .addGroup(btnGroup_StudentLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(insertBtn_student)
                                            .addComponent(updateBtn_student)
                                            .addComponent(removeBtn_student)
                                            .addComponent(printBtn_student)
                                            .addComponent(clearBtn_student)
                                            .addComponent(logoutBtn_student))
                                        .addGap(21, 21, 21))
                            );
                        }

                        GroupLayout table_panel_studentLayout = new GroupLayout(table_panel_student);
                        table_panel_student.setLayout(table_panel_studentLayout);
                        table_panel_studentLayout.setHorizontalGroup(
                            table_panel_studentLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, table_panel_studentLayout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addGroup(table_panel_studentLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnGroup_Student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 869, GroupLayout.PREFERRED_SIZE))
                                    .addGap(36, 36, 36))
                        );
                        table_panel_studentLayout.setVerticalGroup(
                            table_panel_studentLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, table_panel_studentLayout.createSequentialGroup()
                                    .addContainerGap(13, Short.MAX_VALUE)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnGroup_Student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                        );
                    }

                    GroupLayout content_panel_studentLayout = new GroupLayout(content_panel_student);
                    content_panel_student.setLayout(content_panel_studentLayout);
                    content_panel_studentLayout.setHorizontalGroup(
                        content_panel_studentLayout.createParallelGroup()
                            .addGroup(content_panel_studentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(content_panel_studentLayout.createParallelGroup()
                                    .addComponent(table_panel_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchStudentPanel_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                    );
                    content_panel_studentLayout.setVerticalGroup(
                        content_panel_studentLayout.createParallelGroup()
                            .addGroup(content_panel_studentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(searchStudentPanel_student, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(table_panel_student, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                    );
                }

                GroupLayout studentLayout = new GroupLayout(student);
                student.setLayout(studentLayout);
                studentLayout.setHorizontalGroup(
                    studentLayout.createParallelGroup()
                        .addGroup(studentLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(Information, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(content_panel_student, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
                studentLayout.setVerticalGroup(
                    studentLayout.createParallelGroup()
                        .addGroup(studentLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(studentLayout.createParallelGroup()
                                .addComponent(Information, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(content_panel_student, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("Student", student);

            //======== Course ========
            {
                Course.setBackground(new Color(0xcccccc));

                //======== Information_Course_Panel ========
                {
                    Information_Course_Panel.setBorder(new LineBorder(Color.darkGray, 4, true));
                    Information_Course_Panel.setBackground(Color.white);
                    Information_Course_Panel.setForeground(new Color(0xcccccc));

                    //======== search_Infro_panel ========
                    {
                        search_Infro_panel.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //---- mssv_label_SearchInf ----
                        mssv_label_SearchInf.setText("MSSV");
                        mssv_label_SearchInf.setFont(new Font("Times New Roman", Font.BOLD, 14));

                        //---- mssv_textField_searchInf ----
                        mssv_textField_searchInf.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                        //---- searchInfBtn ----
                        searchInfBtn.setText("Search");
                        searchInfBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
                        searchInfBtn.addActionListener(e -> searchInfBtn(e));

                        GroupLayout search_Infro_panelLayout = new GroupLayout(search_Infro_panel);
                        search_Infro_panel.setLayout(search_Infro_panelLayout);
                        search_Infro_panelLayout.setHorizontalGroup(
                            search_Infro_panelLayout.createParallelGroup()
                                .addGroup(search_Infro_panelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(search_Infro_panelLayout.createParallelGroup()
                                        .addComponent(mssv_label_SearchInf, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(search_Infro_panelLayout.createSequentialGroup()
                                            .addGap(25, 25, 25)
                                            .addComponent(mssv_textField_searchInf, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(searchInfBtn, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(50, Short.MAX_VALUE))
                        );
                        search_Infro_panelLayout.setVerticalGroup(
                            search_Infro_panelLayout.createParallelGroup()
                                .addGroup(search_Infro_panelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(mssv_label_SearchInf, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(search_Infro_panelLayout.createParallelGroup()
                                        .addGroup(search_Infro_panelLayout.createSequentialGroup()
                                            .addComponent(searchInfBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(8, 8, 8))
                                        .addGroup(search_Infro_panelLayout.createSequentialGroup()
                                            .addComponent(mssv_textField_searchInf, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                            .addContainerGap())))
                        );
                    }

                    //---- id_label_course ----
                    id_label_course.setText("ID : ");
                    id_label_course.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- mssv_label_course ----
                    mssv_label_course.setText("MSSV : ");
                    mssv_label_course.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- semester_label_course ----
                    semester_label_course.setText("H\u1ecdc k\u00ec : ");
                    semester_label_course.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course1_label_course ----
                    course1_label_course.setText("M\u00f4n h\u1ecdc 1 : ");
                    course1_label_course.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course2_label_course ----
                    course2_label_course.setText("M\u00f4n h\u1ecdc 2 : ");
                    course2_label_course.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course3_label_course ----
                    course3_label_course.setText("M\u00f4n h\u1ecdc 3 :");
                    course3_label_course.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course4_label_course ----
                    course4_label_course.setText("M\u00f4n h\u1ecdc 4 :");
                    course4_label_course.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course5_label_course ----
                    course5_label_course.setText("M\u00f4n h\u1ecdc 5 :");
                    course5_label_course.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- id_textField_course ----
                    id_textField_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    id_textField_course.setBackground(new Color(0x999999));

                    //---- mssv_textField_course ----
                    mssv_textField_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    mssv_textField_course.setBackground(new Color(0x999999));
                    mssv_textField_course.setEditable(false);

                    //---- semester_comboBox_course ----
                    semester_comboBox_course.setModel(new DefaultComboBoxModel<>(new String[] {
                        "H\u1ecdc k\u00ec 1 ",
                        "H\u1ecdc K\u00ec 2 ",
                        "H\u1ecdc k\u00ec 3"
                    }));
                    semester_comboBox_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course1_comboBox_course ----
                    course1_comboBox_course.setModel(new DefaultComboBoxModel<>(new String[] {
                        "C Programming ",
                        "C++ Programming",
                        "Advanced Mathematics II ",
                        "Data Structures & Algorithms",
                        "Python",
                        "Java",
                        "Comprehensive Practice I ",
                        "Engineering Mathematics"
                    }));
                    course1_comboBox_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course2_comboBox_course ----
                    course2_comboBox_course.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Principle of computer component ",
                        "Report Writing ",
                        "Web Programming",
                        "Java OOP",
                        "Design User Interface ",
                        "JSP ",
                        "Software Testing and Inspection ",
                        "Mobile App Development"
                    }));
                    course2_comboBox_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course3_comboBox_course ----
                    course3_comboBox_course.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Advanced Mathematics I ",
                        "Database ",
                        "Software Testing ",
                        "Linux ",
                        "Data Science ",
                        "Big Data I",
                        "Big Data II ",
                        "Comprehensive Practice II "
                    }));
                    course3_comboBox_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course4_comboBox_course ----
                    course4_comboBox_course.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Chinese Traditional Culture ",
                        "Computer Network",
                        "Overview of China ",
                        "Principle of Operating System",
                        "Computer Science "
                    }));
                    course4_comboBox_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course5_comboBox_course ----
                    course5_comboBox_course.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Cloud Computing ",
                        "Algorithms Analysis and Design ",
                        "Machine Learning ",
                        "Deep Learning ",
                        "HSK Testing Tutoring ",
                        "IT Project Management ",
                        "Artificial Intelligence II ",
                        "Graduation Thesis"
                    }));
                    course5_comboBox_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    GroupLayout Information_Course_PanelLayout = new GroupLayout(Information_Course_Panel);
                    Information_Course_Panel.setLayout(Information_Course_PanelLayout);
                    Information_Course_PanelLayout.setHorizontalGroup(
                        Information_Course_PanelLayout.createParallelGroup()
                            .addGroup(Information_Course_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(Information_Course_PanelLayout.createParallelGroup()
                                    .addComponent(search_Infro_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(Information_Course_PanelLayout.createSequentialGroup()
                                        .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addGroup(Information_Course_PanelLayout.createSequentialGroup()
                                                .addComponent(course5_label_course, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(course5_comboBox_course, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Information_Course_PanelLayout.createSequentialGroup()
                                                .addComponent(course4_label_course, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(course4_comboBox_course, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Information_Course_PanelLayout.createSequentialGroup()
                                                .addComponent(course3_label_course, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(course3_comboBox_course, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Information_Course_PanelLayout.createSequentialGroup()
                                                .addComponent(course2_label_course, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(course2_comboBox_course, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Information_Course_PanelLayout.createSequentialGroup()
                                                .addComponent(course1_label_course, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(course1_comboBox_course, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Information_Course_PanelLayout.createSequentialGroup()
                                                .addComponent(id_label_course, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addGap(49, 49, 49)
                                                .addComponent(id_textField_course, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.TRAILING, Information_Course_PanelLayout.createSequentialGroup()
                                                .addGroup(Information_Course_PanelLayout.createParallelGroup()
                                                    .addComponent(mssv_label_course, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(semester_label_course, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(mssv_textField_course, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                                    .addComponent(semester_comboBox_course, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))))
                                        .addGap(0, 114, Short.MAX_VALUE)))
                                .addContainerGap())
                    );
                    Information_Course_PanelLayout.setVerticalGroup(
                        Information_Course_PanelLayout.createParallelGroup()
                            .addGroup(Information_Course_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(search_Infro_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(id_label_course, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(id_textField_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(mssv_label_course, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mssv_textField_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(semester_label_course, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(semester_comboBox_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(course1_label_course, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(course1_comboBox_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(course2_label_course, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(course2_comboBox_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(course3_label_course, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(course3_comboBox_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(course4_label_course, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(course4_comboBox_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Information_Course_PanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(course5_label_course, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(course5_comboBox_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                //======== content_panel_course ========
                {
                    content_panel_course.setBorder(new LineBorder(Color.darkGray, 4, true));

                    //======== search_panel_course ========
                    {
                        search_panel_course.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //---- search_textField_course ----
                        search_textField_course.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                        //---- search_label_course ----
                        search_label_course.setText("T\u00ecm ki\u1ebfm :");
                        search_label_course.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        search_label_course.setHorizontalAlignment(SwingConstants.CENTER);

                        //---- searchBtn_course ----
                        searchBtn_course.setText("Search");
                        searchBtn_course.setFont(new Font("Times New Roman", Font.BOLD, 14));
                        searchBtn_course.addActionListener(e -> searchBtn_course(e));

                        //---- refreshBtn_course ----
                        refreshBtn_course.setText("Refresh");
                        refreshBtn_course.setFont(new Font("Times New Roman", Font.BOLD, 14));
                        refreshBtn_course.addActionListener(e -> refreshBtn_course(e));

                        GroupLayout search_panel_courseLayout = new GroupLayout(search_panel_course);
                        search_panel_course.setLayout(search_panel_courseLayout);
                        search_panel_courseLayout.setHorizontalGroup(
                            search_panel_courseLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, search_panel_courseLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(search_label_course, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(search_textField_course, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(searchBtn_course, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(refreshBtn_course, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23))
                        );
                        search_panel_courseLayout.setVerticalGroup(
                            search_panel_courseLayout.createParallelGroup()
                                .addGroup(search_panel_courseLayout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(search_panel_courseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(search_label_course, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(search_textField_course, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchBtn_course, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(refreshBtn_course, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(32, Short.MAX_VALUE))
                        );
                    }

                    //======== table_panel_course ========
                    {
                        table_panel_course.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //======== scrollPane2 ========
                        {

                            //---- course_table ----
                            course_table.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                    "ID", "MSSV", "H\u1ecdc k\u00ec", "M\u00f4n h\u1ecdc 1 ", "M\u00f4n h\u1ecdc 2", "M\u00f4n h\u1ecdc 3", "M\u00f4n h\u1ecdc 4", "M\u00f4n h\u1ecdc 5"
                                }
                            ));
                            course_table.setBackground(Color.lightGray);
                            scrollPane2.setViewportView(course_table);
                        }

                        //======== btnGroup_course ========
                        {
                            btnGroup_course.setBorder(new LineBorder(Color.darkGray, 4, true));

                            //---- saveBtn_course ----
                            saveBtn_course.setText("Save");
                            saveBtn_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            saveBtn_course.addActionListener(e -> saveBtn_course(e));

                            //---- printBtn_course ----
                            printBtn_course.setText("Print");
                            printBtn_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            printBtn_course.addActionListener(e -> printBtn_course(e));

                            //---- clearBtn_course ----
                            clearBtn_course.setText("Clear");
                            clearBtn_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            clearBtn_course.addActionListener(e -> clearBtn_course(e));

                            //---- logoutBtn_course ----
                            logoutBtn_course.setText("Logout");
                            logoutBtn_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            logoutBtn_course.addActionListener(e -> logoutBtn_course(e));

                            GroupLayout btnGroup_courseLayout = new GroupLayout(btnGroup_course);
                            btnGroup_courseLayout.setHonorsVisibility(false);
                            btnGroup_course.setLayout(btnGroup_courseLayout);
                            btnGroup_courseLayout.setHorizontalGroup(
                                btnGroup_courseLayout.createParallelGroup()
                                    .addGroup(btnGroup_courseLayout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(saveBtn_course, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                                        .addComponent(printBtn_course, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(109, 109, 109)
                                        .addComponent(clearBtn_course, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(86, 86, 86)
                                        .addComponent(logoutBtn_course, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(65, 65, 65))
                            );
                            btnGroup_courseLayout.setVerticalGroup(
                                btnGroup_courseLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, btnGroup_courseLayout.createSequentialGroup()
                                        .addContainerGap(16, Short.MAX_VALUE)
                                        .addGroup(btnGroup_courseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(saveBtn_course)
                                            .addComponent(logoutBtn_course)
                                            .addComponent(printBtn_course)
                                            .addComponent(clearBtn_course))
                                        .addGap(21, 21, 21))
                            );
                        }

                        GroupLayout table_panel_courseLayout = new GroupLayout(table_panel_course);
                        table_panel_courseLayout.setHonorsVisibility(false);
                        table_panel_course.setLayout(table_panel_courseLayout);
                        table_panel_courseLayout.setHorizontalGroup(
                            table_panel_courseLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, table_panel_courseLayout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addGroup(table_panel_courseLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(scrollPane2)
                                        .addComponent(btnGroup_course, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(36, 36, 36))
                        );
                        table_panel_courseLayout.setVerticalGroup(
                            table_panel_courseLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, table_panel_courseLayout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnGroup_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                        );
                    }

                    GroupLayout content_panel_courseLayout = new GroupLayout(content_panel_course);
                    content_panel_course.setLayout(content_panel_courseLayout);
                    content_panel_courseLayout.setHorizontalGroup(
                        content_panel_courseLayout.createParallelGroup()
                            .addGroup(content_panel_courseLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(content_panel_courseLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(search_panel_course, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(table_panel_course, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                    );
                    content_panel_courseLayout.setVerticalGroup(
                        content_panel_courseLayout.createParallelGroup()
                            .addGroup(content_panel_courseLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(search_panel_course, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(table_panel_course, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                    );
                }

                GroupLayout CourseLayout = new GroupLayout(Course);
                Course.setLayout(CourseLayout);
                CourseLayout.setHorizontalGroup(
                    CourseLayout.createParallelGroup()
                        .addGroup(CourseLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(Information_Course_Panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(12, 12, 12)
                            .addComponent(content_panel_course, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
                CourseLayout.setVerticalGroup(
                    CourseLayout.createParallelGroup()
                        .addGroup(CourseLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(CourseLayout.createParallelGroup()
                                .addComponent(Information_Course_Panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(CourseLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(content_panel_course, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("Course", Course);

            //======== Score ========
            {
                Score.setBackground(new Color(0xcccccc));

                //======== information_score ========
                {
                    information_score.setBorder(new LineBorder(Color.darkGray, 4, true));
                    information_score.setBackground(Color.white);
                    information_score.setForeground(new Color(0xcccccc));

                    //======== searchInf_score ========
                    {
                        searchInf_score.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //---- mssvSearchInf_label_score ----
                        mssvSearchInf_label_score.setText("MSSV");
                        mssvSearchInf_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                        //---- mssvSearchInf_textField_score ----
                        mssvSearchInf_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                        //---- searchBtn_searchInf_score ----
                        searchBtn_searchInf_score.setText("Search");
                        searchBtn_searchInf_score.setFont(new Font("Times New Roman", Font.BOLD, 14));
                        searchBtn_searchInf_score.addActionListener(e -> searchBtn_searchInf_score(e));

                        //---- semesterSearchInf_label_score ----
                        semesterSearchInf_label_score.setText("H\u1ecdc k\u00ec ");
                        semesterSearchInf_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                        //---- semesterSearchInf_textField_score ----
                        semesterSearchInf_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                        GroupLayout searchInf_scoreLayout = new GroupLayout(searchInf_score);
                        searchInf_score.setLayout(searchInf_scoreLayout);
                        searchInf_scoreLayout.setHorizontalGroup(
                            searchInf_scoreLayout.createParallelGroup()
                                .addGroup(searchInf_scoreLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(searchInf_scoreLayout.createParallelGroup()
                                        .addGroup(searchInf_scoreLayout.createSequentialGroup()
                                            .addComponent(mssvSearchInf_label_score, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(searchInf_scoreLayout.createSequentialGroup()
                                            .addGroup(searchInf_scoreLayout.createParallelGroup()
                                                .addGroup(searchInf_scoreLayout.createParallelGroup()
                                                    .addComponent(semesterSearchInf_label_score, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(GroupLayout.Alignment.TRAILING, searchInf_scoreLayout.createSequentialGroup()
                                                        .addGap(25, 25, 25)
                                                        .addComponent(semesterSearchInf_textField_score, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(searchInf_scoreLayout.createSequentialGroup()
                                                    .addGap(25, 25, 25)
                                                    .addComponent(mssvSearchInf_textField_score, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(searchBtn_searchInf_score, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                            .addGap(22, 22, 22))))
                        );
                        searchInf_scoreLayout.setVerticalGroup(
                            searchInf_scoreLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, searchInf_scoreLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(mssvSearchInf_label_score, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(searchInf_scoreLayout.createParallelGroup()
                                        .addGroup(searchInf_scoreLayout.createSequentialGroup()
                                            .addGap(7, 7, 7)
                                            .addComponent(mssvSearchInf_textField_score, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(semesterSearchInf_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(semesterSearchInf_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(16, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, searchInf_scoreLayout.createSequentialGroup()
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                            .addComponent(searchBtn_searchInf_score, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33))))
                        );
                    }

                    //---- id_label_score ----
                    id_label_score.setText("ID : ");
                    id_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- mssv_label_score ----
                    mssv_label_score.setText("MSSV : ");
                    mssv_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- semester_label_score ----
                    semester_label_score.setText("H\u1ecdc k\u00ec : ");
                    semester_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course1_label_score ----
                    course1_label_score.setText("M\u00f4n h\u1ecdc 1 : ");
                    course1_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course2_label_score ----
                    course2_label_score.setText("M\u00f4n h\u1ecdc 2 : ");
                    course2_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course3_label_score ----
                    course3_label_score.setText("M\u00f4n h\u1ecdc 3 :");
                    course3_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course4_label_score ----
                    course4_label_score.setText("M\u00f4n h\u1ecdc 4 :");
                    course4_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- course5_label_score ----
                    course5_label_score.setText("M\u00f4n h\u1ecdc 5 :");
                    course5_label_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

                    //---- id_texField_score ----
                    id_texField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    id_texField_score.setEditable(false);
                    id_texField_score.setBackground(new Color(0x999999));

                    //---- mssv_textField_score ----
                    mssv_textField_score.setFont(new Font("Times New Roman", Font.ITALIC, 14));
                    mssv_textField_score.setBackground(new Color(0x999999));
                    mssv_textField_score.setEditable(false);

                    //---- semester_textField_score ----
                    semester_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    semester_textField_score.setEditable(false);
                    semester_textField_score.setBackground(new Color(0x999999));

                    //---- course1_textField_score ----
                    course1_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    course1_textField_score.setEditable(false);
                    course1_textField_score.setBackground(new Color(0x999999));

                    //---- course2_textField_score ----
                    course2_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    course2_textField_score.setEditable(false);
                    course2_textField_score.setBackground(new Color(0x999999));

                    //---- course3_textField_score ----
                    course3_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    course3_textField_score.setEditable(false);
                    course3_textField_score.setBackground(new Color(0x999999));

                    //---- course4_textField_score ----
                    course4_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    course4_textField_score.setEditable(false);
                    course4_textField_score.setBackground(new Color(0x999999));

                    //---- course5_textField_score ----
                    course5_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    course5_textField_score.setEditable(false);
                    course5_textField_score.setBackground(new Color(0x999999));

                    //---- score1_textField_score ----
                    score1_textField_score.setText("0.0");
                    score1_textField_score.setHorizontalAlignment(SwingConstants.CENTER);
                    score1_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- score2_textField_score ----
                    score2_textField_score.setText("0.0");
                    score2_textField_score.setHorizontalAlignment(SwingConstants.CENTER);
                    score2_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- score3_textField_score ----
                    score3_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    score3_textField_score.setText("0.0");
                    score3_textField_score.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- score4_textField_score ----
                    score4_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    score4_textField_score.setText("0.0");
                    score4_textField_score.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- score5_textField_score ----
                    score5_textField_score.setText("0.0");
                    score5_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    score5_textField_score.setHorizontalAlignment(SwingConstants.CENTER);

                    GroupLayout information_scoreLayout = new GroupLayout(information_score);
                    information_score.setLayout(information_scoreLayout);
                    information_scoreLayout.setHorizontalGroup(
                        information_scoreLayout.createParallelGroup()
                            .addGroup(information_scoreLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(information_scoreLayout.createParallelGroup()
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addComponent(searchInf_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addGroup(information_scoreLayout.createParallelGroup()
                                            .addGroup(information_scoreLayout.createSequentialGroup()
                                                .addComponent(id_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(id_texField_score, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.TRAILING, information_scoreLayout.createSequentialGroup()
                                                .addGroup(information_scoreLayout.createParallelGroup()
                                                    .addComponent(mssv_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(semester_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course1_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course2_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course3_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course4_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course5_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(mssv_textField_score, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                    .addComponent(semester_textField_score)
                                                    .addComponent(course4_textField_score)
                                                    .addComponent(course1_textField_score)
                                                    .addComponent(course2_textField_score)
                                                    .addComponent(course3_textField_score)
                                                    .addComponent(course5_textField_score))))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(information_scoreLayout.createParallelGroup()
                                            .addComponent(score1_textField_score, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(score2_textField_score, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(score3_textField_score, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(score4_textField_score, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(score5_textField_score, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(53, 53, 53))))
                    );
                    information_scoreLayout.setVerticalGroup(
                        information_scoreLayout.createParallelGroup()
                            .addGroup(information_scoreLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(searchInf_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(id_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(id_texField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(mssv_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mssv_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(semester_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(semester_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(course1_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(score1_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(course1_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(information_scoreLayout.createParallelGroup()
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(score2_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(course2_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(score3_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(course3_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addComponent(course2_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(course3_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(information_scoreLayout.createParallelGroup()
                                    .addComponent(course4_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(course4_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(score4_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(information_scoreLayout.createParallelGroup()
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(course5_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(score5_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(course5_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(108, Short.MAX_VALUE))
                    );
                }

                //======== contentPanel_score ========
                {
                    contentPanel_score.setBorder(new LineBorder(Color.darkGray, 4, true));

                    //======== searchPanel_score ========
                    {
                        searchPanel_score.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //---- search_textField_score ----
                        search_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                        //---- search_label_score ----
                        search_label_score.setText("T\u00ecm ki\u1ebfm :");
                        search_label_score.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        search_label_score.setHorizontalAlignment(SwingConstants.CENTER);

                        //---- searchBtn_score ----
                        searchBtn_score.setText("Search");
                        searchBtn_score.setFont(new Font("Times New Roman", Font.BOLD, 14));
                        searchBtn_score.addActionListener(e -> searchBtn_score(e));

                        //---- refreshBtn_score ----
                        refreshBtn_score.setText("Refresh");
                        refreshBtn_score.setFont(new Font("Times New Roman", Font.BOLD, 14));
                        refreshBtn_score.addActionListener(e -> refreshBtn_score(e));

                        GroupLayout searchPanel_scoreLayout = new GroupLayout(searchPanel_score);
                        searchPanel_score.setLayout(searchPanel_scoreLayout);
                        searchPanel_scoreLayout.setHorizontalGroup(
                            searchPanel_scoreLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, searchPanel_scoreLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(search_label_score, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                    .addComponent(search_textField_score, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(searchBtn_score, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(refreshBtn_score, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23))
                        );
                        searchPanel_scoreLayout.setVerticalGroup(
                            searchPanel_scoreLayout.createParallelGroup()
                                .addGroup(searchPanel_scoreLayout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(searchPanel_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(search_label_score, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(search_textField_score, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchBtn_score, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(refreshBtn_score, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(32, Short.MAX_VALUE))
                        );
                    }

                    //======== tablePanel_score ========
                    {
                        tablePanel_score.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //======== scrollPane3 ========
                        {

                            //---- score_table ----
                            score_table.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                    "ID", "MSSV", "H\u1ecdc k\u00ec", "M\u00f4n h\u1ecdc 1 ", "\u0110i\u1ec3m 1", "M\u00f4n h\u1ecdc 2", "\u0110i\u1ec3m 2", "M\u00f4n h\u1ecdc 3", "\u0110i\u1ec3m 3", "M\u00f4n h\u1ecdc 4", "\u0110i\u1ec3m 4", "M\u00f4n h\u1ecdc 5", "\u0110i\u1ec3m 5"
                                }
                            ));
                            score_table.setBackground(Color.lightGray);
                            score_table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            score_table.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    score_tableMouseClicked(e);
                                }
                            });
                            scrollPane3.setViewportView(score_table);
                        }

                        //======== btnGroup_score ========
                        {
                            btnGroup_score.setBorder(new LineBorder(Color.darkGray, 4, true));

                            //---- updateBtn_score ----
                            updateBtn_score.setText("Update");
                            updateBtn_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            updateBtn_score.addActionListener(e -> updateBtn_score(e));

                            //---- printBtn_score ----
                            printBtn_score.setText("Print");
                            printBtn_score.addActionListener(e -> printBtn_score(e));

                            //---- clearBtn_score ----
                            clearBtn_score.setText("Clear");
                            clearBtn_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            clearBtn_score.addActionListener(e -> clearBtn_score(e));

                            //---- logoutBtn_score ----
                            logoutBtn_score.setText("Logout");
                            logoutBtn_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            logoutBtn_score.addActionListener(e -> logoutBtn_score(e));

                            //---- saveBtn_score ----
                            saveBtn_score.setText("Save");
                            saveBtn_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            saveBtn_score.addActionListener(e -> saveBtn_score(e));

                            GroupLayout btnGroup_scoreLayout = new GroupLayout(btnGroup_score);
                            btnGroup_scoreLayout.setHonorsVisibility(false);
                            btnGroup_score.setLayout(btnGroup_scoreLayout);
                            btnGroup_scoreLayout.setHorizontalGroup(
                                btnGroup_scoreLayout.createParallelGroup()
                                    .addGroup(btnGroup_scoreLayout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addComponent(saveBtn_score, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                        .addComponent(updateBtn_score, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)
                                        .addComponent(printBtn_score, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63)
                                        .addComponent(clearBtn_score, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(logoutBtn_score, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51))
                            );
                            btnGroup_scoreLayout.setVerticalGroup(
                                btnGroup_scoreLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, btnGroup_scoreLayout.createSequentialGroup()
                                        .addContainerGap(16, Short.MAX_VALUE)
                                        .addGroup(btnGroup_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(logoutBtn_score)
                                            .addComponent(clearBtn_score)
                                            .addComponent(printBtn_score)
                                            .addComponent(updateBtn_score)
                                            .addComponent(saveBtn_score))
                                        .addGap(21, 21, 21))
                            );
                        }

                        GroupLayout tablePanel_scoreLayout = new GroupLayout(tablePanel_score);
                        tablePanel_scoreLayout.setHonorsVisibility(false);
                        tablePanel_score.setLayout(tablePanel_scoreLayout);
                        tablePanel_scoreLayout.setHorizontalGroup(
                            tablePanel_scoreLayout.createParallelGroup()
                                .addGroup(tablePanel_scoreLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(tablePanel_scoreLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                                        .addComponent(btnGroup_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(19, 19, 19))
                        );
                        tablePanel_scoreLayout.setVerticalGroup(
                            tablePanel_scoreLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, tablePanel_scoreLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnGroup_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                        );
                    }

                    GroupLayout contentPanel_scoreLayout = new GroupLayout(contentPanel_score);
                    contentPanel_score.setLayout(contentPanel_scoreLayout);
                    contentPanel_scoreLayout.setHorizontalGroup(
                        contentPanel_scoreLayout.createParallelGroup()
                            .addGroup(contentPanel_scoreLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPanel_scoreLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(searchPanel_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tablePanel_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                    );
                    contentPanel_scoreLayout.setVerticalGroup(
                        contentPanel_scoreLayout.createParallelGroup()
                            .addGroup(contentPanel_scoreLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(searchPanel_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tablePanel_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                    );
                }

                GroupLayout ScoreLayout = new GroupLayout(Score);
                Score.setLayout(ScoreLayout);
                ScoreLayout.setHorizontalGroup(
                    ScoreLayout.createParallelGroup()
                        .addGroup(ScoreLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(information_score, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                            .addGap(12, 12, 12)
                            .addComponent(contentPanel_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
                ScoreLayout.setVerticalGroup(
                    ScoreLayout.createParallelGroup()
                        .addGroup(ScoreLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(ScoreLayout.createParallelGroup()
                                .addComponent(information_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(ScoreLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(contentPanel_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("Score", Score);

            //======== Marks_sheet ========
            {
                Marks_sheet.setBackground(new Color(0xcccccc));

                //======== information_marksSheet ========
                {
                    information_marksSheet.setBorder(new LineBorder(Color.darkGray, 4, true));
                    information_marksSheet.setBackground(Color.white);
                    information_marksSheet.setForeground(new Color(0xcccccc));

                    //======== mssv_searchInf_panel ========
                    {
                        mssv_searchInf_panel.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //---- mssv_searchInf_label_marksSheet ----
                        mssv_searchInf_label_marksSheet.setText("MSSV");
                        mssv_searchInf_label_marksSheet.setFont(new Font("Times New Roman", Font.BOLD, 14));

                        //---- mssv_searchInf_textField_marksSheet ----
                        mssv_searchInf_textField_marksSheet.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                        //---- searchBtn_searchInf_marksSheet ----
                        searchBtn_searchInf_marksSheet.setText("Search");
                        searchBtn_searchInf_marksSheet.setFont(new Font("Times New Roman", Font.BOLD, 14));

                        GroupLayout mssv_searchInf_panelLayout = new GroupLayout(mssv_searchInf_panel);
                        mssv_searchInf_panel.setLayout(mssv_searchInf_panelLayout);
                        mssv_searchInf_panelLayout.setHorizontalGroup(
                            mssv_searchInf_panelLayout.createParallelGroup()
                                .addGroup(mssv_searchInf_panelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(mssv_searchInf_label_marksSheet, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(354, Short.MAX_VALUE))
                                .addGroup(mssv_searchInf_panelLayout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addComponent(mssv_searchInf_textField_marksSheet, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(searchBtn_searchInf_marksSheet, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                    .addGap(16, 16, 16))
                        );
                        mssv_searchInf_panelLayout.setVerticalGroup(
                            mssv_searchInf_panelLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, mssv_searchInf_panelLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(mssv_searchInf_label_marksSheet, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(mssv_searchInf_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(mssv_searchInf_textField_marksSheet, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchBtn_searchInf_marksSheet))
                                    .addContainerGap(25, Short.MAX_VALUE))
                        );
                    }

                    //======== result_infoMarksSheet_panel ========
                    {
                        result_infoMarksSheet_panel.setBorder(new LineBorder(Color.darkGray, 4, true));
                        result_infoMarksSheet_panel.setFont(new Font("Times New Roman", Font.BOLD, 30));

                        //---- result_marksSheet ----
                        result_marksSheet.setFont(new Font("Times New Roman", Font.PLAIN, 30));
                        result_marksSheet.setText("CGPA : 0.0");
                        result_marksSheet.setHorizontalAlignment(SwingConstants.CENTER);

                        GroupLayout result_infoMarksSheet_panelLayout = new GroupLayout(result_infoMarksSheet_panel);
                        result_infoMarksSheet_panel.setLayout(result_infoMarksSheet_panelLayout);
                        result_infoMarksSheet_panelLayout.setHorizontalGroup(
                            result_infoMarksSheet_panelLayout.createParallelGroup()
                                .addGroup(result_infoMarksSheet_panelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(result_marksSheet, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                        result_infoMarksSheet_panelLayout.setVerticalGroup(
                            result_infoMarksSheet_panelLayout.createParallelGroup()
                                .addComponent(result_marksSheet, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        );
                    }

                    GroupLayout information_marksSheetLayout = new GroupLayout(information_marksSheet);
                    information_marksSheet.setLayout(information_marksSheetLayout);
                    information_marksSheetLayout.setHorizontalGroup(
                        information_marksSheetLayout.createParallelGroup()
                            .addGroup(information_marksSheetLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(information_marksSheetLayout.createParallelGroup()
                                    .addComponent(mssv_searchInf_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(result_infoMarksSheet_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                    );
                    information_marksSheetLayout.setVerticalGroup(
                        information_marksSheetLayout.createParallelGroup()
                            .addGroup(information_marksSheetLayout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(mssv_searchInf_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(result_infoMarksSheet_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                }

                //======== content_panel_marksSheet ========
                {
                    content_panel_marksSheet.setBorder(new LineBorder(Color.darkGray, 4, true));

                    //======== panel26 ========
                    {
                        panel26.setBorder(new LineBorder(Color.darkGray, 4, true));

                        //======== scrollPane4 ========
                        {

                            //---- table4 ----
                            table4.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                    "ID", "MSSV", "H\u1ecdc k\u00ec", "M\u00f4n h\u1ecdc 1 ", "\u0110i\u1ec3m 1", "M\u00f4n h\u1ecdc 2", "\u0110i\u1ec3m 2", "M\u00f4n h\u1ecdc 3", "\u0110i\u1ec3m 3", "M\u00f4n h\u1ecdc 4", "\u0110i\u1ec3m 4", "M\u00f4n h\u1ecdc 5", "\u0110i\u1ec3m 5"
                                }
                            ));
                            table4.setBackground(Color.lightGray);
                            scrollPane4.setViewportView(table4);
                        }

                        //======== panel27 ========
                        {
                            panel27.setBorder(new LineBorder(Color.darkGray, 4, true));

                            //---- updateBtn_marksSheet ----
                            updateBtn_marksSheet.setText("Update");

                            //---- printBtn_marksSheet ----
                            printBtn_marksSheet.setText("Print");

                            //---- clearBtn_marksSheet ----
                            clearBtn_marksSheet.setText("Clear");

                            //---- logoutBtn_marksSheet ----
                            logoutBtn_marksSheet.setText("Logout");
                            logoutBtn_marksSheet.addActionListener(e -> logoutBtn_marksSheet(e));

                            //---- saveBtn_marksSheet ----
                            saveBtn_marksSheet.setText("Save");

                            GroupLayout panel27Layout = new GroupLayout(panel27);
                            panel27Layout.setHonorsVisibility(false);
                            panel27.setLayout(panel27Layout);
                            panel27Layout.setHorizontalGroup(
                                panel27Layout.createParallelGroup()
                                    .addGroup(panel27Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addComponent(saveBtn_marksSheet, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(updateBtn_marksSheet, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)
                                        .addComponent(printBtn_marksSheet, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63)
                                        .addComponent(clearBtn_marksSheet, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(logoutBtn_marksSheet, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51))
                            );
                            panel27Layout.setVerticalGroup(
                                panel27Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel27Layout.createSequentialGroup()
                                        .addContainerGap(16, Short.MAX_VALUE)
                                        .addGroup(panel27Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(logoutBtn_marksSheet)
                                            .addComponent(clearBtn_marksSheet)
                                            .addComponent(printBtn_marksSheet)
                                            .addComponent(updateBtn_marksSheet)
                                            .addComponent(saveBtn_marksSheet))
                                        .addGap(21, 21, 21))
                            );
                        }

                        GroupLayout panel26Layout = new GroupLayout(panel26);
                        panel26Layout.setHonorsVisibility(false);
                        panel26.setLayout(panel26Layout);
                        panel26Layout.setHorizontalGroup(
                            panel26Layout.createParallelGroup()
                                .addGroup(panel26Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel26Layout.createParallelGroup()
                                        .addComponent(panel27, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 897, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap())
                        );
                        panel26Layout.setVerticalGroup(
                            panel26Layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, panel26Layout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(panel27, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(16, 16, 16))
                        );
                    }

                    GroupLayout content_panel_marksSheetLayout = new GroupLayout(content_panel_marksSheet);
                    content_panel_marksSheet.setLayout(content_panel_marksSheetLayout);
                    content_panel_marksSheetLayout.setHorizontalGroup(
                        content_panel_marksSheetLayout.createParallelGroup()
                            .addGroup(content_panel_marksSheetLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel26, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    content_panel_marksSheetLayout.setVerticalGroup(
                        content_panel_marksSheetLayout.createParallelGroup()
                            .addGroup(content_panel_marksSheetLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel26, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                    );
                }

                GroupLayout Marks_sheetLayout = new GroupLayout(Marks_sheet);
                Marks_sheet.setLayout(Marks_sheetLayout);
                Marks_sheetLayout.setHorizontalGroup(
                    Marks_sheetLayout.createParallelGroup()
                        .addGroup(Marks_sheetLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(information_marksSheet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(12, 12, 12)
                            .addComponent(content_panel_marksSheet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
                Marks_sheetLayout.setVerticalGroup(
                    Marks_sheetLayout.createParallelGroup()
                        .addGroup(Marks_sheetLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(Marks_sheetLayout.createParallelGroup()
                                .addComponent(information_marksSheet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(content_panel_marksSheet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("Marks Sheet", Marks_sheet);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(headerPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 1480, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Nguyễn Huy
    private JPanel headerPanel;
    private JPanel headerTextPanel;
    private JLabel headerText;
    private JTabbedPane tabbedPane1;
    private JPanel student;
    private JPanel Information;
    public static JTextField mssv_textField_student;
    private JTextField nameStudent_textField_student;
    private JComboBox<String> gender_comboBox_student;
    private JTextField email_textField_student;
    private JTextField phoneNumber_textField_student;
    private JTextField nameFather_textField_student;
    private JTextField nameMother_textField_student;
    private JTextField country_textField_student;
    private JTextField address_textField_student;
    private JLabel mssv_labe_studentl;
    private JLabel nameStudent_label_student;
    private JLabel dayofbirth_label_student;
    private JLabel gender_label_student;
    private JLabel email_label_student;
    private JLabel phoneNumber_label_student;
    private JLabel nameFather_label_student;
    private JLabel nameMother_label_student;
    private JLabel country_label_student;
    private JLabel address_label_student;
    private JPanel image_panel_student;
    private JButton browseBtn_student;
    private JPanel showImage_panel_student;
    private JLabel image;
    private JDateChooser dayofBirth_jdatechooser_student;
    private JPanel content_panel_student;
    private JPanel searchStudentPanel_student;
    private JTextField search_textField_student;
    private JLabel search_label_student;
    private JButton searchBtn_student;
    private JButton refreshBtn_student;
    private JPanel table_panel_student;
    private JScrollPane scrollPane1;
    private JTable student_table;
    private JPanel btnGroup_Student;
    private JButton insertBtn_student;
    private JButton updateBtn_student;
    private JButton removeBtn_student;
    private JButton printBtn_student;
    private JButton clearBtn_student;
    private JButton logoutBtn_student;
    private JPanel Course;
    private JPanel Information_Course_Panel;
    private JPanel search_Infro_panel;
    private JLabel mssv_label_SearchInf;
    private JTextField mssv_textField_searchInf;
    private JButton searchInfBtn;
    private JLabel id_label_course;
    private JLabel mssv_label_course;
    private JLabel semester_label_course;
    private JLabel course1_label_course;
    private JLabel course2_label_course;
    private JLabel course3_label_course;
    private JLabel course4_label_course;
    private JLabel course5_label_course;
    public static JTextField id_textField_course;
    public static JTextField mssv_textField_course;
    private JComboBox<String> semester_comboBox_course;
    private JComboBox<String> course1_comboBox_course;
    private JComboBox<String> course2_comboBox_course;
    private JComboBox<String> course3_comboBox_course;
    private JComboBox<String> course4_comboBox_course;
    private JComboBox<String> course5_comboBox_course;
    private JPanel content_panel_course;
    private JPanel search_panel_course;
    private JTextField search_textField_course;
    private JLabel search_label_course;
    private JButton searchBtn_course;
    private JButton refreshBtn_course;
    private JPanel table_panel_course;
    private JScrollPane scrollPane2;
    private JTable course_table;
    private JPanel btnGroup_course;
    private JButton saveBtn_course;
    private JButton printBtn_course;
    private JButton clearBtn_course;
    private JButton logoutBtn_course;
    private JPanel Score;
    private JPanel information_score;
    private JPanel searchInf_score;
    private JLabel mssvSearchInf_label_score;
    private JTextField mssvSearchInf_textField_score;
    private JButton searchBtn_searchInf_score;
    private JLabel semesterSearchInf_label_score;
    private JTextField semesterSearchInf_textField_score;
    private JLabel id_label_score;
    private JLabel mssv_label_score;
    private JLabel semester_label_score;
    private JLabel course1_label_score;
    private JLabel course2_label_score;
    private JLabel course3_label_score;
    private JLabel course4_label_score;
    private JLabel course5_label_score;
    public static JTextField id_texField_score;
    public static JTextField mssv_textField_score;
    public static JTextField semester_textField_score;
    public static JTextField course1_textField_score;
    public static JTextField course2_textField_score;
    public static JTextField course3_textField_score;
    public static JTextField course4_textField_score;
    public static JTextField course5_textField_score;
    private JTextField score1_textField_score;
    private JTextField score2_textField_score;
    private JTextField score3_textField_score;
    private JTextField score4_textField_score;
    private JTextField score5_textField_score;
    private JPanel contentPanel_score;
    private JPanel searchPanel_score;
    private JTextField search_textField_score;
    private JLabel search_label_score;
    private JButton searchBtn_score;
    private JButton refreshBtn_score;
    private JPanel tablePanel_score;
    private JScrollPane scrollPane3;
    private JTable score_table;
    private JPanel btnGroup_score;
    private JButton updateBtn_score;
    private JButton printBtn_score;
    private JButton clearBtn_score;
    private JButton logoutBtn_score;
    private JButton saveBtn_score;
    private JPanel Marks_sheet;
    private JPanel information_marksSheet;
    private JPanel mssv_searchInf_panel;
    private JLabel mssv_searchInf_label_marksSheet;
    private JTextField mssv_searchInf_textField_marksSheet;
    private JButton searchBtn_searchInf_marksSheet;
    private JPanel result_infoMarksSheet_panel;
    private JLabel result_marksSheet;
    private JPanel content_panel_marksSheet;
    private JPanel panel26;
    private JScrollPane scrollPane4;
    private JTable table4;
    private JPanel panel27;
    private JButton updateBtn_marksSheet;
    private JButton printBtn_marksSheet;
    private JButton clearBtn_marksSheet;
    private JButton logoutBtn_marksSheet;
    private JButton saveBtn_marksSheet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
   