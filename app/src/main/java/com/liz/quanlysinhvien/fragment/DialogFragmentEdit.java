package com.liz.quanlysinhvien.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.liz.quanlysinhvien.OnDataChangedListener;
import com.liz.quanlysinhvien.R;
import com.liz.quanlysinhvien.entity.Student;

public class DialogFragmentEdit extends DialogFragment {

    private EditText edtStudentId, edtName, edtAverageMark;
    private Button btnSave;
    private RadioButton radMale, radFemale;
    private OnDataChangedListener mListener;

    public static DialogFragmentEdit getNewInstance(Student student) {
        DialogFragmentEdit dialogFragmentEdit = new DialogFragmentEdit();
        Bundle args = new Bundle();
        args.putSerializable("student", student);
        dialogFragmentEdit.setArguments(args);
        return dialogFragmentEdit;
    }

    public void OnDataChanged(OnDataChangedListener listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_update, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtStudentId = view.findViewById(R.id.edtUpdateStudentId);
        edtName = view.findViewById(R.id.edtUpdateName);
        edtAverageMark = view.findViewById(R.id.edtUpdateAverageMark);
        radFemale = view.findViewById(R.id.radUpdateFemale);
        radMale = view.findViewById(R.id.radUpdateMale);
        btnSave = view.findViewById(R.id.btnUpdateSave);

        Student student = (Student) getArguments().getSerializable("student");
        edtStudentId.setText(student.getStudentId());
        edtName.setText(student.getStudentName());
        edtAverageMark.setText(student.getAverageMark() + "");
        if (student.isMale()) {
            radMale.setChecked(true);
        } else {
            radFemale.setChecked(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtStudentId.getText().toString();
                String name = edtName.getText().toString();
                boolean gender;
                if (radMale.isChecked()) gender = true;
                else gender = false;
                Double mark = Double.parseDouble(edtAverageMark.getText().toString());
                Student updateStudent = new Student(id, name, gender, mark);
                mListener.setOnUpdateListener(updateStudent);
                getDialog().dismiss();
            }
        });
    }

}
