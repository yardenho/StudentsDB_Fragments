package com.example.studentsdb_fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.studentsdb_fragments.model.Model;
import com.example.studentsdb_fragments.model.Student;

import java.util.LinkedList;
import java.util.List;


public class fragment_student_details extends Fragment {
    private List<Student> data = new LinkedList<Student>();
    private TextView nameTv;
    private TextView idTv;
    private CheckBox cbTv;
    private TextView phoneTv;
    private TextView addressTv;
    private View view;
    private Student s = null;
    ProgressBar pb;

    public fragment_student_details() {};   // empty Ctor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_details, container, false);
        String studentID = fragment_student_detailsArgs.fromBundle(getArguments()).getStudentID();
        //////////******************************עשינו שינויים עם הליסטנר****************************************
        Model.getInstance().getStudentList(new Model.GetAllStudentsListener() {
            @Override
            public void onComplete(List<Student> d) {
                data = d;
            }
        });
        nameTv = view.findViewById(R.id.studentDetails_name_p);
        idTv = view.findViewById(R.id.studentDetails_id_p);
        cbTv = view.findViewById(R.id.studentDetails_checked_cb_p);
        phoneTv = view.findViewById(R.id.studentDetails_phone_p);
        addressTv = view.findViewById(R.id.studentDetails_address_p);
        pb = view.findViewById(R.id.studentDetails_progressBar);
        pb.setVisibility(View.VISIBLE);
        //////שינויייייי
        int position = data.indexOf(s);
        //////שינויייייי



        if(s == null)
            Model.getInstance().getStudentByID(studentID, (student) -> {
                updateDisplay(student);
            });
        if (s != null ) {
            ////////*************שינויייייי//////////בשביל העידכון אחרי הedit
            Model.getInstance().getStudentList(new Model.GetAllStudentsListener() {
                @Override
                public void onComplete(List<Student> d) {
                    data = d;
                }
            });
            Log.d("TAG", "s - " + position);
            s = data.get(position);
            ////////*************שינויייייי//////////

            updateDisplay(s);
        }

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