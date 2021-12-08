package com.example.studentsdb_fragments.model;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class Model {
    static  final private Model instance = new Model();
    private List<Student> data = new LinkedList<Student>();

    private Model(){ }
    public List<Student> getStudentList(){
        return data;
    }
    public void addNewStudent(Student student){
        data.add(student);
    }
    public void deleteStudent(int pos){
        data.remove(pos);
    }
    public Student getStudentByID(String id)
    {
        for( Student s: data) {
            if (s.getID().equals(id))
                return s;
        }
        return null;
    }

    public void deleteStudentByID(String id)
    {
        for( Student s: data) {
            if (s.getID().equals(id)) {
                data.remove(s);
                return;
            }
        }
    }

    public static Model getInstance(){
        return instance;
    }
}
