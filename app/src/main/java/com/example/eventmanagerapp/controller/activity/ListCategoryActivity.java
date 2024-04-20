package com.example.eventmanagerapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.controller.fragment.FragmentListCategory;

public class ListCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_bar_layout);
        Toolbar toolbar = findViewById(R.id.category_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCategory,new FragmentListCategory()).addToBackStack("f2").commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        startDashboardButton();
        return true;
    }

    public void startDashboardButton(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}