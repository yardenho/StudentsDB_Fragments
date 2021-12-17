package com.example.studentsdb_fragments.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.studentsdb_fragments.MyApplication;

@Database(entities = {Student.class}, version = 3)

abstract class AppLocalDBRepository extends RoomDatabase {
    public abstract StudentDao studentDao();
}

public class AppLocalDB{
    static public final AppLocalDBRepository db =
            Room.databaseBuilder(MyApplication.getContext(),
                    AppLocalDBRepository.class,
                    "dbFileName.db")
                    .fallbackToDestructiveMigration()
                    .build();
    private AppLocalDB(){}
}