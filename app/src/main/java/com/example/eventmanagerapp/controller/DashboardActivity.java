package com.example.eventmanagerapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eventmanagerapp.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void startCategoryActivityButton(View view){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void startEventActivityButton(View view){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }
}