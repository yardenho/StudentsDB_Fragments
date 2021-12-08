package com.example.studentsdb_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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


public class fragment_edit_student extends Fragment {
    private List<Student> data;

    public fragment_edit_student(){};  // empty ctor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_student, container, false);

        data = Model.getInstance().getStudentList();
        TextView nameTv = view.findViewById(R.id.editStudent_name_et);
        TextView idTv = view.findViewById(R.id.editStudent_id_et);
        CheckBox cbTv = view.findViewById(R.id.editStudent_checked_cb);
        TextView phoneTv = view.findViewById(R.id.editStudent_phone_et);
        TextView addressTv = view.findViewById(R.id.editStudent_address_et);
        Button cancelBtn = view.findViewById(R.id.editStudent_cancel_btn);
        Button deleteBtn = view.findViewById(R.id.editStudent_delete_btn);
        Button saveBtn = view.findViewById(R.id.editStudent_save_btn);

        String studentID = fragment_edit_studentArgs.fromBundle(getArguments()).getStudentID();

        Student student = Model.getInstance().getStudentByID(studentID);
        if (student != null ) {
            nameTv.setText(student.getName());
            idTv.setText(student.getID());
            cbTv.setChecked(student.getcb());
            phoneTv.setText(student.getPhoneNumber());
            addressTv.setText(student.getAddress());
        }

        FragmentManager fm = getActivity().getSupportFragmentManager();
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().deleteStudentByID(student.getID());
                Navigation.findNavController(view).navigate(R.id.fragment_students_list);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setName(nameTv.getText().toString());
                student.setID(idTv.getText().toString());
                student.setPhoneNumber(phoneTv.getText().toString());
                student.setAddress(addressTv.getText().toString());
                student.setCB(cbTv.isChecked());
                Navigation.findNavController(view).navigateUp();
            }
        });
        return view;
    }
}