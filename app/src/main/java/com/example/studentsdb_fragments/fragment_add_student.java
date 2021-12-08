package com.example.studentsdb_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentsdb_fragments.model.Model;
import com.example.studentsdb_fragments.model.Student;

import java.util.List;


public class fragment_add_student extends Fragment {
    private List<Student> data;
    public fragment_add_student() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);

        Button saveBtn = view.findViewById(R.id.addStudent_save_btn);
        Button cancelBtn  = view.findViewById(R.id.addStudent_cancel_btn);
        EditText nameTv = view.findViewById(R.id.addStudent_name_et);
        EditText idTv = view.findViewById(R.id.addStudent_id_et);
        EditText phoneTv = view.findViewById(R.id.addStudent_phone_et);
        EditText addressTv = view.findViewById(R.id.addStudent_address_et);
        CheckBox cb = view.findViewById(R.id.addStudent_cb);

        data = Model.getInstance().getStudentList();
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student= new Student();
                student.setName(nameTv.getText().toString());
                student.setID(idTv.getText().toString());
                student.setPhoneNumber(phoneTv.getText().toString());
                student.setAddress(addressTv.getText().toString());
                student.setCB(cb.isChecked());
                data.add(student);
                Navigation.findNavController(v).navigateUp();
            }
        });

        return view;
    }
}