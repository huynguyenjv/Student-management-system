/*
 * Created by JFormDesigner on Thu Mar 16 21:15:47 ICT 2023
 */

package org.swing.view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import com.toedter.calendar.*;
import org.jdesktop.swingx.*;
import org.swing.Dao.Student_DAO;

/**
 * @author ADMIN
 */
public class Home extends JFrame {
    Student_DAO studentDao = new Student_DAO();
    int xx , xy;
    private String imagePath;
    private DefaultTableModel model;
    public Home() {
        initComponents();
        init();
    }

    public void init(){
        tableViewStudent();
        mssv_textField_student.setText(String.valueOf(studentDao.getMax()));
    }

    private void tableViewStudent(){
        model = (DefaultTableModel)  student_table.getModel();
        student_table.setRowHeight(30);
        student_table.setShowGrid(true);
        student_table.setGridColor(Color.black);
        student_table.setBackground(Color.white);
    }

    private void clearStudent(){
        mssv_textField_student.setText(String.valueOf(studentDao.getMax()));
        nameStudent_textField_student.setText(null);
        dayofBirth_jdatechooser_student.setDate(null);
        gender_comboBox_student.setSelectedIndex(0);
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

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
    }

    private void clearBtn_student(ActionEvent e) {
        clearStudent();
    }

    private void insertBtn_student(ActionEvent e) {
        if(isEmptyStudent()){
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
        }
    }

    private void phoneNumber_textField_studentKeyTyped(KeyEvent e) {
        if(!Character.isDigit(e.getKeyChar())){
            e.consume();
        }
    }

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
//    private void thisWindowOpened(WindowEvent e) {
//        try {
//            for(double i = 0.1 ; i <= 1.0 ; i += 0.1){
//                String s = i +"";
//                float f = Float.valueOf(s);
//                this.setOpacity(f);
//                Thread.sleep(40);
//            }
//        } catch (InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }
//    }

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
        search_student_texField = new JTextField();
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
        table2 = new JTable();
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
        table3 = new JTable();
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
            headerPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,headerPanel. getBorder( )) ); headerPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

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

                    //---- mssv_textField_student ----
                    mssv_textField_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                    mssv_textField_student.setEditable(false);
                    mssv_textField_student.setDisabledTextColor(new Color(0x999999));
                    mssv_textField_student.setToolTipText("Enter number here !");

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

                        //---- search_student_texField ----
                        search_student_texField.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                        //---- search_label_student ----
                        search_label_student.setText("T\u00ecm ki\u1ebfm :");
                        search_label_student.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        search_label_student.setHorizontalAlignment(SwingConstants.CENTER);

                        //---- searchBtn_student ----
                        searchBtn_student.setText("Search");
                        searchBtn_student.setFont(new Font("Times New Roman", Font.BOLD, 14));

                        //---- refreshBtn_student ----
                        refreshBtn_student.setText("Refresh");
                        refreshBtn_student.setFont(new Font("Times New Roman", Font.BOLD, 14));

                        GroupLayout searchStudentPanel_studentLayout = new GroupLayout(searchStudentPanel_student);
                        searchStudentPanel_student.setLayout(searchStudentPanel_studentLayout);
                        searchStudentPanel_studentLayout.setHorizontalGroup(
                            searchStudentPanel_studentLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, searchStudentPanel_studentLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(search_label_student, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                    .addComponent(search_student_texField, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(search_student_texField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
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
                                    {null, null, null, null, null, null, null, null, null, null, null},
                                    {null, null, null, null, null, null, null, null, null, null, null},
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

                            //---- removeBtn_student ----
                            removeBtn_student.setText("Xo\u00e1");
                            removeBtn_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                            //---- printBtn_student ----
                            printBtn_student.setText("Print");
                            printBtn_student.setFont(new Font("Times New Roman", Font.PLAIN, 14));

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

                    //---- mssv_textField_course ----
                    mssv_textField_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

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

                        //---- refreshBtn_course ----
                        refreshBtn_course.setText("Refresh");
                        refreshBtn_course.setFont(new Font("Times New Roman", Font.BOLD, 14));

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

                            //---- table2 ----
                            table2.setModel(new DefaultTableModel(
                                new Object[][] {
                                    {null, null, null, null, null, null, null, null},
                                    {null, null, null, null, null, null, null, null},
                                },
                                new String[] {
                                    "ID", "MSSV", "H\u1ecdc k\u00ec", "M\u00f4n h\u1ecdc 1 ", "M\u00f4n h\u1ecdc 2", "M\u00f4n h\u1ecdc 3", "M\u00f4n h\u1ecdc 4", "M\u00f4n h\u1ecdc 5"
                                }
                            ));
                            table2.setBackground(Color.lightGray);
                            scrollPane2.setViewportView(table2);
                        }

                        //======== btnGroup_course ========
                        {
                            btnGroup_course.setBorder(new LineBorder(Color.darkGray, 4, true));

                            //---- saveBtn_course ----
                            saveBtn_course.setText("Save");
                            saveBtn_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                            //---- printBtn_course ----
                            printBtn_course.setText("Print");
                            printBtn_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                            //---- clearBtn_course ----
                            clearBtn_course.setText("Clear");
                            clearBtn_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));

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
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
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
                                            .addContainerGap(12, Short.MAX_VALUE))
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

                    //---- mssv_textField_score ----
                    mssv_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- semester_textField_score ----
                    semester_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course1_textField_score ----
                    course1_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course2_textField_score ----
                    course2_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course3_textField_score ----
                    course3_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course4_textField_score ----
                    course4_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    //---- course5_textField_score ----
                    course5_textField_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

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
                                        .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addGroup(information_scoreLayout.createSequentialGroup()
                                                .addComponent(id_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addGap(49, 49, 49)
                                                .addComponent(id_texField_score, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.TRAILING, information_scoreLayout.createSequentialGroup()
                                                .addGroup(information_scoreLayout.createParallelGroup()
                                                    .addComponent(mssv_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(semester_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course1_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course2_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course3_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course4_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(course5_label_score, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(mssv_textField_score, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                                    .addComponent(semester_textField_score, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                                        .addComponent(course1_textField_score, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(score1_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                                        .addGroup(information_scoreLayout.createParallelGroup()
                                                            .addGroup(information_scoreLayout.createSequentialGroup()
                                                                .addGroup(information_scoreLayout.createParallelGroup()
                                                                    .addComponent(course4_textField_score)
                                                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                                                        .addGroup(information_scoreLayout.createParallelGroup()
                                                                            .addComponent(course2_textField_score, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(course3_textField_score, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
                                                            .addGroup(information_scoreLayout.createSequentialGroup()
                                                                .addComponent(course5_textField_score, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)))
                                                        .addGroup(information_scoreLayout.createParallelGroup()
                                                            .addComponent(score2_textField_score, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(score3_textField_score, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(score4_textField_score, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(score5_textField_score, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
                                        .addGap(0, 114, Short.MAX_VALUE))
                                    .addComponent(searchInf_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
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
                                    .addComponent(course1_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(score1_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(information_scoreLayout.createParallelGroup()
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(score2_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(course2_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(information_scoreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(course3_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(score3_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addComponent(course2_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(course3_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(information_scoreLayout.createParallelGroup()
                                    .addComponent(course4_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(score4_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(course4_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(information_scoreLayout.createParallelGroup()
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(course5_label_score, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(information_scoreLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(information_scoreLayout.createParallelGroup()
                                            .addComponent(score5_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(course5_textField_score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(103, Short.MAX_VALUE))
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

                        //---- refreshBtn_score ----
                        refreshBtn_score.setText("Refresh");
                        refreshBtn_score.setFont(new Font("Times New Roman", Font.BOLD, 14));

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

                            //---- table3 ----
                            table3.setModel(new DefaultTableModel(
                                new Object[][] {
                                    {null, null, null, null, null, null, null, null, null, null, null, null, null},
                                    {null, null, null, null, null, null, null, null, null, null, null, null, null},
                                },
                                new String[] {
                                    "ID", "MSSV", "H\u1ecdc k\u00ec", "M\u00f4n h\u1ecdc 1 ", "\u0110i\u1ec3m 1", "M\u00f4n h\u1ecdc 2", "\u0110i\u1ec3m 2", "M\u00f4n h\u1ecdc 3", "\u0110i\u1ec3m 3", "M\u00f4n h\u1ecdc 4", "\u0110i\u1ec3m 4", "M\u00f4n h\u1ecdc 5", "\u0110i\u1ec3m 5"
                                }
                            ));
                            table3.setBackground(Color.lightGray);
                            table3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            scrollPane3.setViewportView(table3);
                        }

                        //======== btnGroup_score ========
                        {
                            btnGroup_score.setBorder(new LineBorder(Color.darkGray, 4, true));

                            //---- updateBtn_score ----
                            updateBtn_score.setText("Update");
                            updateBtn_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                            //---- printBtn_score ----
                            printBtn_score.setText("Print");

                            //---- clearBtn_score ----
                            clearBtn_score.setText("Clear");
                            clearBtn_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                            //---- logoutBtn_score ----
                            logoutBtn_score.setText("Logout");
                            logoutBtn_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                            logoutBtn_score.addActionListener(e -> logoutBtn_score(e));

                            //---- saveBtn_score ----
                            saveBtn_score.setText("Save");
                            saveBtn_score.setFont(new Font("Times New Roman", Font.PLAIN, 14));

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
                            .addComponent(information_score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                    {null, null, null, null, null, null, null, null, null, null, null, null, null},
                                    {null, null, null, null, null, null, null, null, null, null, null, null, null},
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
    private JTextField mssv_textField_student;
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
    private JTextField search_student_texField;
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
    private JTextField id_textField_course;
    private JTextField mssv_textField_course;
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
    private JTable table2;
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
    private JTextField id_texField_score;
    private JTextField mssv_textField_score;
    private JTextField semester_textField_score;
    private JTextField course1_textField_score;
    private JTextField course2_textField_score;
    private JTextField course3_textField_score;
    private JTextField course4_textField_score;
    private JTextField course5_textField_score;
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
    private JTable table3;
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
