package com.example.studentsdb_fragments.model;

import android.util.Log;

import com.example.studentsdb_fragments.MyApplication;

import java.util.LinkedList;
import java.util.List;

public class Model {
    static  final private Model instance = new Model();
    private List<Student> data = new LinkedList<Student>();

    public interface GetAllStudentsListener{
        void onComplete(List<Student> data);
    }

    private Model(){}

    public void getStudentList(GetAllStudentsListener listener){
        MyApplication.executorService.execute(()->{
            List <Student> data = AppLocalDB.db.studentDao().getAll();
            MyApplication.mainHandler.post(()->{
                listener.onComplete(data);
            });
        });
    }

    public interface addNewStudentListener{
        void onComplete();
    }

    public void addNewStudent(Student student,addNewStudentListener listener){

        MyApplication.executorService.execute(()->{
            AppLocalDB.db.studentDao().insertAll(student);
            MyApplication.mainHandler.post(()->{
                listener.onComplete();
            });
        });
    }


    public interface getStudentByIDListener{
        void onComplete(Student student);
    }

    public void getStudentByID(String id, getStudentByIDListener listener)
    {
        MyApplication.executorService.execute(()->{
            Student student = AppLocalDB.db.studentDao().getStudentById(id);
            MyApplication.mainHandler.post(()->{
                listener.onComplete(student);
            });
        });
    }

    public interface deleteStudentByIDListener{
        void onComplete();
    }

    public void deleteStudentByID(Student student,deleteStudentByIDListener listener )
    {
        MyApplication.executorService.execute(()->{
            AppLocalDB.db.studentDao().delete(student);
            MyApplication.mainHandler.post(()->{
                listener.onComplete();
            });
        });
    }

    public interface editStudentListener{
        void onComplete();
    }

    public void editStudent(Student oldStudent, Student s,editStudentListener listener){
        MyApplication.executorService.execute(()->{
//            oldStudent.setName(s.getName());
////            oldStudent.setId(s.getId());
//            oldStudent.setPhoneNumber(s.getPhoneNumber());
//            oldStudent.setAddress(s.getAddress());
//            oldStudent.setCb(s.getCb());
            AppLocalDB.db.studentDao().editStudent(s);
            MyApplication.mainHandler.post(()->{
                listener.onComplete();
            });
        });
    }


    public static Model getInstance(){
        return instance;
    }
}
