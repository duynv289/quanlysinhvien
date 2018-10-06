package com.liz.quanlysinhvien.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liz.quanlysinhvien.R;
import com.liz.quanlysinhvien.StudentDB;
import com.liz.quanlysinhvien.adapter.StudentAdapter;
import com.liz.quanlysinhvien.entity.Student;

import java.util.List;

/**
 * Created by Administrator on 10/7/2018.
 */

public class FragmentStudent extends Fragment implements FragmentEntry.OnDataChangedListener,StudentAdapter.OnItemClickListener{

//    private List<Student> mStudents;
    private RecyclerView mRecyclerView;
    private StudentAdapter mStudentAdapter;
    private StudentDB mStudentDB;
    public static FragmentStudent getNewInstance(){
        return new FragmentStudent();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStudentDB = new StudentDB(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        showStudents();
        FragmentEntry.getNewInstance().setOnDataChanged(this);
        return view;
    }
    public void showStudents(){
        mStudentAdapter = new StudentAdapter(mStudentDB.getAllStudent());
        mStudentAdapter.setOnItemClick(this);
        mRecyclerView.setAdapter(mStudentAdapter);
    }
    @Override
    public void setOnDataChangedListener() {
        showStudents();
    }

    @Override
    public void setOnItemClickListener(List<Student> mStudents, int position) {
        mStudentDB.deleteStudent(mStudents.get(position));
        showStudents();
    }
}
