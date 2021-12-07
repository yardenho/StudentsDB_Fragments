package com.example.studentsdb_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsdb_fragments.model.Model;
import com.example.studentsdb_fragments.model.Student;

import java.util.List;

public class fragment_students_list extends Fragment {
    List<Student> data;
    View view;

    public fragment_students_list() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_students_list, container, false);
        RecyclerView list = view.findViewById(R.id.studentList_recycler);
        list.setHasFixedSize(true);

        //connecting the layout manager -מסדר את התצוגה
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);

        data = Model.getInstance().getStudentList();
        //נחבר את האקטיביטי עם האדפטר
        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        //// ******************************************************************to check***********

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position, View v) {
                //כאן צריך לפתוח את האקטיביטי של הצגת פרטים לפי המיקום הספציפי
//                Intent intent = new Intent(StudentRecyclerActivity.this, Present_student_details.class);
//                intent.putExtra("pos", position);
//                startActivity(intent);
                Log.d("TAG", "row was clicked " + position);
                String studentID = data.get(position).getID();
                fragment_students_listDirections.ActionFragmentStudentsListToFragmentStudentDetails action = fragment_students_listDirections.actionFragmentStudentsListToFragmentStudentDetails(studentID);
                Navigation.findNavController(v).navigate(action);
            }
        });

        //// ******************************************************************to check***********

        adapter.setOnCbClickListener(new OnCbClickListener() {
            @Override
            public void OnCbClick(int position) {
                Student student = data.get(position);
                Log.d("TAG", "the cb:" +!student.getcb());
                student.setCB(!student.getcb());
                Log.d("TAG", "checkBox was clicked "+ position);
            }
        });


        return view;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final OnItemClickListener listener;
        private final OnCbClickListener cbListener;
        //תפקידו לזכור את הרפרנסים
        TextView name;
        TextView id;
        CheckBox cb;
        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener, OnCbClickListener cbListener) {
            super(itemView);

            name = itemView.findViewById(R.id.StudentsListRow_name_t);
            id = itemView.findViewById(R.id.StudentsListRow_id_t);
            cb = itemView.findViewById(R.id.StudentsListRow_cb);
            this.listener = listener;
            this.cbListener = cbListener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    listener.OnItemClick(pos, v);
                }
            });

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    cbListener.OnCbClick(pos);
                }
            });
        }

        public void bind(Student student){
            name.setText(student.getName());
            id.setText(student.getID());
            cb.setChecked(student.getcb());
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(int position, View v);
    }

    public interface OnCbClickListener{
        void OnCbClick(int position);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private OnItemClickListener listener;
        private OnCbClickListener cbListener;
        void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        void setOnCbClickListener(OnCbClickListener cbListener){
            this.cbListener = cbListener;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //יוצר אובייקט חדש מהשורה
            LayoutInflater inflater = getLayoutInflater();
            View rowView = inflater.inflate(R.layout.students_list_row, parent,false);
            //יצירת הולדר שיעטוף אותו
            MyViewHolder viewHolder = new MyViewHolder(rowView, listener, cbListener);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            //תחבר לי את הview עם הdata של אותה שורה
            Student student = data.get(position);
            holder.bind(student);

        }

        @Override
        public int getItemCount() {
            //כמה שורות יש לי בליסט
            return data.size();
        }
    }
}