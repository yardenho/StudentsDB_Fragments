package com.example.studentsdb_fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    NavController navctrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        NavHostFragment nav_host =(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.base_navHost);
//        navctrl = nav_host.getNavController();
    }
}