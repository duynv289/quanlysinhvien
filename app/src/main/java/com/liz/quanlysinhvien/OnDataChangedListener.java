package com.liz.quanlysinhvien;

import com.liz.quanlysinhvien.entity.Student;

public interface OnDataChangedListener {
    void setOnUpdateListener(Student student);
    void setOnInsertListener(Student student);
}
