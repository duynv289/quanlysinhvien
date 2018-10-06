package com.liz.quanlysinhvien.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.liz.quanlysinhvien.R;
import com.liz.quanlysinhvien.entity.Student;

import java.util.List;

/**
 * Created by Administrator on 10/7/2018.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> mStudents;
    private OnItemClickListener mListener;
    private int mPostion;
    public StudentAdapter(List<Student> mStudents) {
        this.mStudents = mStudents;
    }

    public void setOnItemClick(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view,mListener,mStudents);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        holder.bindData(mStudents.get(position));
    }

    @Override
    public int getItemCount() {
        return mStudents != null ? mStudents.size() : 0;
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtStudentId, txtStudentName, txtGender, txtAverageMark;
        private Button btnEdit, btnDelete;
        private static OnItemClickListener mListenter;
        private static List<Student> mStudents;
        public StudentViewHolder(View itemView,OnItemClickListener listener, List<Student> mList) {
            super(itemView);
            mListenter = listener;
            mStudents = mList;
            txtStudentId = itemView.findViewById(R.id.txtStudentId);
            txtStudentName = itemView.findViewById(R.id.txtStudentName);
            txtGender = itemView.findViewById(R.id.txtGender);
            txtAverageMark = itemView.findViewById(R.id.txtAverageMark);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);

            btnDelete.setOnClickListener(this);
            btnEdit.setOnClickListener(this);
        }

        private void bindData(Student student) {
            txtStudentId.setText(student.getStudentId());
            txtStudentName.setText(student.getStudentName());
            if (student.isMale()) {
                txtGender.setText(R.string.male);
            } else {
                txtGender.setText(R.string.female);
            }
            txtAverageMark.setText(student.getAverageMark() + "");

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnDelete:
                    if(mListenter != null){
                        mListenter.setOnItemClickListener(mStudents,getLayoutPosition());
                    }
                    break;
            }
        }
    }

    public interface OnItemClickListener {
        void setOnItemClickListener(List<Student> mStudents, int position);
    }
}
