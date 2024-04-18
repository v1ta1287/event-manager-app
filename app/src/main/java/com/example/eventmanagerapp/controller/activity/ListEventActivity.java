package com.example.eventmanagerapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.controller.fragment.Fragment1;
import com.example.eventmanagerapp.controller.fragment.FragmentListCategory;
import com.example.eventmanagerapp.controller.fragment.FragmentListEvent;

public class ListEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentEvent,new FragmentListEvent()).addToBackStack("f1").commit();
    }
}