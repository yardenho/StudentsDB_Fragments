package com.example.studentsdb_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.studentsdb_fragments.model.Model;
import com.example.studentsdb_fragments.model.Student;

public class fragment_edit_student extends Fragment {
    private Student student=null;
    private TextView nameTv;
    private TextView idTv;
    private CheckBox cbTv;
    private TextView phoneTv;
    private TextView addressTv;
    private Button cancelBtn;
    private Button deleteBtn;
    private Button saveBtn;
    private ProgressBar pb;
    private String studentID;

    public fragment_edit_student(){};  // empty ctor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_student, container, false);
        nameTv = view.findViewById(R.id.editStudent_name_et);
        idTv = view.findViewById(R.id.editStudent_id_et);
        cbTv = view.findViewById(R.id.editStudent_checked_cb);
        phoneTv = view.findViewById(R.id.editStudent_phone_et);
        addressTv = view.findViewById(R.id.editStudent_address_et);
        cancelBtn = view.findViewById(R.id.editStudent_cancel_btn);
        deleteBtn = view.findViewById(R.id.editStudent_delete_btn);
        saveBtn = view.findViewById(R.id.editStudent_save_btn);
        pb = view.findViewById(R.id.editStudent_progressBar);
        pb.setVisibility(View.VISIBLE);

        studentID = fragment_edit_studentArgs.fromBundle(getArguments()).getStudentID();
        Model.getInstance().getStudentByID(studentID, (s)->{
            updateDisplay(s);
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().deleteStudentByID(student, new Model.deleteStudentByIDListener() {
                    @Override
                    public void onComplete() {
                        Navigation.findNavController(view).navigate(R.id.fragment_students_list);
                    }
                });
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student newStudent = new Student();
                newStudent.setName(nameTv.getText().toString());
                newStudent.setId(idTv.getText().toString());
                newStudent.setPhoneNumber(phoneTv.getText().toString());
                newStudent.setAddress(addressTv.getText().toString());
                newStudent.setCb(cbTv.isChecked());
                Model.getInstance().editStudent(newStudent, () -> {
                    Navigation.findNavController(view).navigateUp();
                });
            }
        });
        return view;
    }

    private void updateDisplay(Student s) {
        student = s;
        if (student != null ) {
            nameTv.setText(student.getName());
            idTv.setText(student.getId());
            cbTv.setChecked(student.getCb());
            phoneTv.setText(student.getPhoneNumber());
            addressTv.setText(student.getAddress());
        }
        pb.setVisibility(View.GONE);
    }
}