package com.liz.quanlysinhvien.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liz.quanlysinhvien.OnDataChangedListener;
import com.liz.quanlysinhvien.R;
import com.liz.quanlysinhvien.StudentDB;
import com.liz.quanlysinhvien.adapter.StudentAdapter;
import com.liz.quanlysinhvien.entity.Student;

import java.util.List;

/**
 * Created by Administrator on 10/7/2018.
 */

public class FragmentStudent extends Fragment implements StudentAdapter.OnItemClickListener, OnDataChangedListener {

    private RecyclerView mRecyclerView;
    private StudentAdapter mStudentAdapter;
    private StudentDB mStudentDB;

    public FragmentStudent() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStudentDB = new StudentDB(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        FragmentEntry fragmentEntry = new FragmentEntry();
        fragmentEntry.OnDataChanged(this);
        showStudents();
        return view;
    }

    public void showStudents() {
        mStudentAdapter = new StudentAdapter(mStudentDB.getAllStudent());
        mStudentAdapter.setOnItemClick(this);
        mRecyclerView.setAdapter(mStudentAdapter);
    }

    @Override
    public void setOnItemClickListener(List<Student> mStudents, int position, int id) {
        switch (id) {
            case R.id.btnDelete:
                mStudentDB.deleteStudent(mStudents.get(position));
                showStudents();
                break;
            case R.id.btnEdit:
                FragmentManager fm = getFragmentManager();
                DialogFragmentEdit dialog = DialogFragmentEdit.getNewInstance(mStudents.get(position));
                dialog.show(fm, null);
                dialog.OnDataChanged(this);
                break;
        }
    }

    @Override
    public void setOnUpdateListener(Student student) {
        mStudentDB.editStudent(student);
        showStudents();
        Toast.makeText(getActivity(), "Update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOnInsertListener(Student student) {
        Toast.makeText(getActivity(), "Insert", Toast.LENGTH_SHORT).show();
        showStudents();
    }
}
