package com.example.studentsdb_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsdb_fragments.model.Model;
import com.example.studentsdb_fragments.model.Student;

import java.util.List;


public class fragment_student_details extends Fragment {

    List<Student> data;
    TextView nameTv;
    TextView idTv;
    CheckBox cbTv;
    TextView phoneTv;
    TextView addressTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_details, container, false);
        String studentID = fragment_student_detailsArgs.fromBundle(getArguments()).getStudentID();
        data = Model.getInstance().getStudentList();
        nameTv = view.findViewById(R.id.studentDetails_name_p);
        idTv = view.findViewById(R.id.studentDetails_id_p);
        cbTv = view.findViewById(R.id.studentDetails_checked_cb_p);
        phoneTv = view.findViewById(R.id.studentDetails_phone_p);
        addressTv = view.findViewById(R.id.studentDetails_address_p);

        Student s = Model.getInstance().getStudentByID(studentID);
        if (s != null )
            setDetails(s);

        Button editBtn = view.findViewById(R.id.studentDetails_edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_student_detailsDirections.ActionFragmentStudentDetailsToFragmentEditStudent action = fragment_student_detailsDirections.actionFragmentStudentDetailsToFragmentEditStudent(s.getID());
                Navigation.findNavController(v).navigate(action);
            }
        });

        return view;
    }

    private void setDetails(Student student) {
        nameTv.setText(student.getName());
        idTv.setText(student.getID());
        cbTv.setChecked(student.getcb());
        phoneTv.setText(student.getPhoneNumber());
        addressTv.setText(student.getAddress());
    }
}