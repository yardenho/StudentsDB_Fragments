package com.example.studentsdb_fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.studentsdb_fragments.model.Model;
import com.example.studentsdb_fragments.model.Student;
import java.util.LinkedList;
import java.util.List;

public class fragment_students_list extends Fragment {
    private List<Student> data = new LinkedList<Student>();
    private View view;
    private MyAdapter adapter;

    public fragment_students_list() {
        // Required empty public constructor
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

        Model.getInstance().getStudentList(new Model.GetAllStudentsListener() {
            @Override
            public void onComplete(List<Student> d) {
                data = d;
                adapter.notifyDataSetChanged();
            }
        });
        //נחבר את האקטיביטי עם האדפטר
        adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position, View v) {
                String studentID = data.get(position).getId();
                fragment_students_listDirections.ActionFragmentStudentsListToFragmentStudentDetails action = fragment_students_listDirections.actionFragmentStudentsListToFragmentStudentDetails(studentID);
                Navigation.findNavController(v).navigate(action);
            }
        });

        adapter.setOnCbClickListener(new OnCbClickListener() {
            @Override
            public void OnCbClick(int position) {
                Student student = data.get(position);
                student.setCb(!student.getCb());
                Model.getInstance().editStudent(student, () -> {
                    adapter.notifyDataSetChanged();
                });
            }
        });
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.students_list_menu, menu);
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
            id.setText(student.getId());
            cb.setChecked(student.getCb());
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