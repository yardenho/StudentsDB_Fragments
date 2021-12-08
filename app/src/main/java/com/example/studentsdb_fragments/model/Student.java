package com.example.studentsdb_fragments.model;

public class Student {
    private String name;
    private String id;
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
    public String getID(){
        return id;
    }
    public boolean getcb(){
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
    public void setID(String newID){
        id = newID;
    }
    public void setCB(boolean newCB){
        cb = newCB;
    }
    public void setPhoneNumber(String newNum){
        phoneNumber = newNum;
    }
    public void setAddress(String newAdd){
        address = newAdd;
    }
}
