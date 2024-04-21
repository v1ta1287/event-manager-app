package com.example.eventmanagerapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.controller.fragment.FragmentListEvent;

import java.util.Objects;

/**
 * Controller for page that lists events
 * Not much logic is required for this page as most of it is handled by the fragment
 */
public class ListEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_bar_layout);
        Toolbar toolbar = findViewById(R.id.event_toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentEvent,new FragmentListEvent()).addToBackStack("f1").commit();
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