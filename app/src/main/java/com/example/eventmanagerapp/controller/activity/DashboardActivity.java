package com.example.eventmanagerapp.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.controller.fragment.FragmentListCategory;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    Toolbar toolbar;

    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        drawerlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.dashboard_toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());

        getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new FragmentListCategory()).addToBackStack("f2").commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.option_refresh) {

        } else if (itemId == R.id.option_clear_event) {

        } else if (itemId == R.id.option_delete_categories) {

        } else if (itemId == R.id.option_delete_events){

        }
        return true;
    }
    public void startCategoryListButton(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, ListCategoryActivity.class);
        startActivity(intent);
    }

    public void startEventListButton(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, ListEventActivity.class);
        startActivity(intent);
    }

    public void startLoginButton(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // get the id of the selected item
            int id = item.getItemId();

            if (id == R.id.option_view_categories) {
                startCategoryListButton();
            } else if (id == R.id.option_add_category) {
                // Do something
            } else if (id == R.id.option_view_events) {
                startEventListButton();
            } else if (id == R.id.option_logout) {
                startLoginButton();
            }
            // close the drawer
            drawerlayout.closeDrawers();
            // tell the OS
            return true;
        }
    }
}