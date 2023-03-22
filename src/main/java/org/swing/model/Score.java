package org.swing.model;

public class Score {
    private int id;
    private int studentID;
    private int semester;
    private String course1;
    private String course2;
    private String course3;
    private String course4;
    private String course5;
    private double score1;
    private double score2;
    private double score3;
    private double score4;
    private double score5;

    public Score(int id, int studentID, int semester, String course1, String course2, String course3, String course4, String course5
            , double score1, double score2, double score3, double score4, double score5) {
        this.id = id;
        this.studentID = studentID;
        this.semester = semester;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.course5 = course5;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.score5 = score5;
    }

    public Score(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourse1() {
        return course1;
    }

    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    public String getCourse2() {
        return course2;
    }

    public void setCourse2(String course2) {
        this.course2 = course2;
    }

    public String getCourse3() {
        return course3;
    }

    public void setCourse3(String course3) {
        this.course3 = course3;
    }

    public String getCourse4() {
        return course4;
    }

    public void setCourse4(String course4) {
        this.course4 = course4;
    }

    public String getCourse5() {
        return course5;
    }

    public void setCourse5(String course5) {
        this.course5 = course5;
    }

    public double getScore1() {
        return score1;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public double getScore2() {
        return score2;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }

    public double getScore3() {
        return score3;
    }

    public void setScore3(double score3) {
        this.score3 = score3;
    }

    public double getScore4() {
        return score4;
    }

    public void setScore4(double score4) {
        this.score4 = score4;
    }

    public double getScore5() {
        return score5;
    }

    public void setScore5(double score5) {
        this.score5 = score5;
    }
}
