package com.example.studentsdb_fragments.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private boolean cb;
    private String phoneNumber;
    private String address;

    public Student(){
        name ="";
        id="";
        cb=false;
        phoneNumber="";
        address="";
    }

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public boolean getCb(){
        return cb;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getAddress(){
        return address;
    }
    public void setName(String newName){
        name = newName;
    }
    public void setId(String newID){
        id = newID;
    }
    public void setCb(boolean newCB){
        cb = newCB;
    }
    public void setPhoneNumber(String newNum){
        phoneNumber = newNum;
    }
    public void setAddress(String newAdd){
        address = newAdd;
    }
}
