package com.liz.quanlysinhvien.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.liz.quanlysinhvien.OnDataChangedListener;
import com.liz.quanlysinhvien.R;
import com.liz.quanlysinhvien.StudentDB;
import com.liz.quanlysinhvien.entity.Student;

/**
 * Created by Administrator on 10/7/2018.
 */

public class FragmentEntry extends Fragment implements View.OnClickListener {

    private Button btnSave;
    private EditText edtStudentId, edtName, edtAverageMark;
    private RadioButton radMale, radFemale;
    private StudentDB mStudentDB;
    private OnDataChangedListener mListener;

    public static FragmentEntry getNewInstance() {
        return new FragmentEntry();
    }

    public void setOnDataChanged(OnDataChangedListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStudentDB = new StudentDB(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);
        initView(view);
        btnSave.setOnClickListener(this);
        return view;
    }

    private void clearText() {
        radFemale.setChecked(false);
        radMale.setChecked(false);
        edtStudentId.setText("");
        edtAverageMark.setText("");
        edtName.setText("");
    }

    private void initView(View view) {
        btnSave = view.findViewById(R.id.btnSave);
        edtAverageMark = view.findViewById(R.id.edtAverageMark);
        edtStudentId = view.findViewById(R.id.edtStudentId);
        edtName = view.findViewById(R.id.edtName);
        radFemale = view.findViewById(R.id.radFemale);
        radMale = view.findViewById(R.id.radMale);
    }

    @Override
    public void onClick(View view) {
        try {
            String id = edtStudentId.getText().toString();
            String name = edtName.getText().toString();
            Double mark = Double.parseDouble(edtAverageMark.getText().toString());
            boolean isMale = false;
            if (radMale.isChecked()) {
                isMale = true;
            }
            Student student = new Student(id, name, isMale, mark);
            mStudentDB.addStudent(student);
            if (mListener != null) {
                mListener.setOnInsertListener(student);
            }
            clearText();
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
        }
    }
}
