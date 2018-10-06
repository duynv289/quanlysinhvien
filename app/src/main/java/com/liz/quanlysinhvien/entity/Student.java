package com.liz.quanlysinhvien.entity;

/**
 * Created by Administrator on 10/7/2018.
 */

public class Student {
    private String studentId;
    private String studentName;
    private boolean male;
    private double averageMark;

    public Student() {
    }

    public Student(String studentId, String studentName, boolean male, double averageMark) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.male = male;
        this.averageMark = averageMark;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }
}
