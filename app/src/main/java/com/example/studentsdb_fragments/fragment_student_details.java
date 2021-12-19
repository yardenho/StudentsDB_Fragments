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

public class fragment_student_details extends Fragment {
    private TextView nameTv;
    private TextView idTv;
    private CheckBox cbTv;
    private TextView phoneTv;
    private TextView addressTv;
    private View view;
    private Student s = null;
    private ProgressBar pb;
    String studentID = null;

    public fragment_student_details() {};   // empty Ctor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_details, container, false);
        if(studentID == null)
            studentID = fragment_student_detailsArgs.fromBundle(getArguments()).getStudentID();
        nameTv = view.findViewById(R.id.studentDetails_name_p);
        idTv = view.findViewById(R.id.studentDetails_id_p);
        cbTv = view.findViewById(R.id.studentDetails_checked_cb_p);
        phoneTv = view.findViewById(R.id.studentDetails_phone_p);
        addressTv = view.findViewById(R.id.studentDetails_address_p);
        pb = view.findViewById(R.id.studentDetails_progressBar);
        pb.setVisibility(View.VISIBLE);

        Model.getInstance().getStudentByID(studentID, (student) -> {
            updateDisplay(student);
        });

        Button editBtn = view.findViewById(R.id.studentDetails_edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_student_detailsDirections.ActionFragmentStudentDetailsToFragmentEditStudent action = fragment_student_detailsDirections.actionFragmentStudentDetailsToFragmentEditStudent(s.getId());
                Navigation.findNavController(v).navigate(action);
            }
        });
        return view;
    }

    private void updateDisplay(Student student) {
        s = student;
        setDetails(s);
        pb.setVisibility(View.GONE);
    }

    private void setDetails(Student student) {
        nameTv.setText(student.getName());
        idTv.setText(student.getId());
        cbTv.setChecked(student.getCb());
        phoneTv.setText(student.getPhoneNumber());
        addressTv.setText(student.getAddress());
    }
}