package com.example.eventmanagerapp.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.eventmanagerapp.InvalidCategoryIdException;
import com.example.eventmanagerapp.InvalidNameException;
import com.example.eventmanagerapp.PositiveIntegerException;
import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.controller.fragment.FragmentListCategory;
import com.example.eventmanagerapp.controller.util.IdGeneratorUtility;
import com.example.eventmanagerapp.controller.util.SharedPreferencesUtility;
import com.example.eventmanagerapp.model.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

/**
 * Dashboard activity controller
 * Handles logic specific to dashboard UI features
 * The dashboard is the central page after a user successfully logs in
 */
public class DashboardActivity extends AppCompatActivity {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    Toolbar toolbar;
    EditText eventName;
    EditText eventCategoryId;
    EditText eventTickets;
    EditText eventId;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch eventIsActive;
    FloatingActionButton addEventFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_bar_layout);

        drawerlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());

        eventId = findViewById((R.id.editEventId));
        eventName = findViewById((R.id.editEventName));
        eventCategoryId = findViewById((R.id.editEventCategoryId));
        eventTickets = findViewById((R.id.editTicketsAvailable));
        eventIsActive = findViewById((R.id.eventActiveSwitch));
        addEventFab = findViewById((R.id.fabAddEvent));

        // attach category list fragment when dashboard is started
        getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new FragmentListCategory()).addToBackStack("f2").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public void onFabClick(View view) {
        String randomEventId = IdGeneratorUtility.generateEventId();
        String eventTicketsAvailable;

        // if eventTickets is left blank, default to 0
        if (eventTickets.getText().toString().equals("")){
            eventTicketsAvailable = "0";
        } else {
            eventTicketsAvailable = eventTickets.getText().toString();
        }

        // try creating a new Event object and print the appropriate toast error message
        // if input validation fails
        try {
            Event newEvent = new Event(randomEventId, eventCategoryId.getText().toString(),
                    eventName.getText().toString(),
                    Integer.parseInt(eventTicketsAvailable),
                    eventIsActive.isChecked(),
                    SharedPreferencesUtility.getCategoriesFromSharedPreferences(getApplicationContext()));
            SharedPreferencesUtility.saveEventToSharedPreference(getApplicationContext(), newEvent);
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new FragmentListCategory()).addToBackStack("f2").commit();

            // after saving a new Event object to SharedPreferences, show a success Snackbar which
            // also gives the user an option to undo the most recent Event object save
            Snackbar snackbar = Snackbar.make(view, "Event " + randomEventId + " successfully added", Snackbar.LENGTH_LONG)
                    .setAction("Undo Save", v -> {
                        SharedPreferencesUtility.undoAddEventToSharedPreferences(getApplicationContext());
                        getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new FragmentListCategory()).addToBackStack("f2").commit();
                    });
            snackbar.show();
        } catch (InvalidNameException e){
            Toast.makeText(getApplicationContext(), "Invalid event name", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException | PositiveIntegerException e){
            Toast.makeText(getApplicationContext(), "Invalid tickets available", Toast.LENGTH_LONG).show();
        } catch (InvalidCategoryIdException e) {
            Toast.makeText(getApplicationContext(), "Category does not exist", Toast.LENGTH_LONG).show();
        }
    }

    public void clearForm() {
        eventId.setText("");
        eventName.setText("");
        eventCategoryId.setText("");
        eventTickets.setText("");
        eventIsActive.setChecked(false);
    }

    // Options menu options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.option_refresh) {
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new FragmentListCategory()).addToBackStack("f2").commit();
        } else if (itemId == R.id.option_clear_event) {
            clearForm();
        } else if (itemId == R.id.option_delete_categories) {
            SharedPreferencesUtility.clearCategoriesFromSharedPreferences(getApplicationContext());
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new FragmentListCategory()).addToBackStack("f2").commit();
        } else if (itemId == R.id.option_delete_events){
            SharedPreferencesUtility.clearEventsFromSharedPreferences(getApplicationContext());
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new FragmentListCategory()).addToBackStack("f2").commit();
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void startNewCategoryButton(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, NewCategoryActivity.class);
        startActivity(intent);
    }

    // Navigation drawer options
    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.option_view_categories) {
                startCategoryListButton();
            } else if (id == R.id.option_add_category) {
                startNewCategoryButton();
            } else if (id == R.id.option_view_events) {
                startEventListButton();
            } else if (id == R.id.option_logout) {
                startLoginButton();
            }
            drawerlayout.closeDrawers();
            return true;
        }
    }
}