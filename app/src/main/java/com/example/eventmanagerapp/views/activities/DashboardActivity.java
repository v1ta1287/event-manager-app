package com.example.eventmanagerapp.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.modelValidator.EventValidator;
import com.example.eventmanagerapp.data.modelValidator.InvalidCategoryIdException;
import com.example.eventmanagerapp.data.modelValidator.InvalidNameException;
import com.example.eventmanagerapp.data.modelValidator.PositiveIntegerException;
import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.viewmodels.DashboardViewModel;
import com.example.eventmanagerapp.views.fragments.ListCategoriesFragment;
import com.example.eventmanagerapp.utilities.IdGeneratorUtility;
import com.example.eventmanagerapp.data.model.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

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

    List<Category> categoryList;
    DashboardViewModel mDashboardViewModel;

    private GestureDetectorCompat mDetector;
    TextView tvGesture;

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
        getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new ListCategoriesFragment()).addToBackStack("f2").commit();

        mDashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        categoryList = new ArrayList<>();
        mDashboardViewModel.getAllCategories().observe(this, newData -> {
            categoryList.addAll(newData);
        });

        View touchpad = findViewById(R.id.touchpad);
        tvGesture = findViewById(R.id.tvGesture);

        // initialise new instance of CustomGestureDetector class
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mDetector = new GestureDetectorCompat(this, customGestureDetector);
        touchpad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @SuppressLint("SetTextI18n")
        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            tvGesture.setText("onLongPress");
            clearForm();
            super.onLongPress(e);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            tvGesture.setText("onDoubleTap");
            addEvent();
            return super.onDoubleTap(e);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public void onFabClick(View view) {
        addEvent();
    }

    public void addEvent(){
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
            EventValidator newEventValidator = new EventValidator(randomEventId, eventCategoryId.getText().toString(),
                    eventName.getText().toString(),
                    Integer.parseInt(eventTicketsAvailable),
                    eventIsActive.isChecked(), categoryList);
            Event newEvent = newEventValidator.getValidatedEvent();
            mDashboardViewModel.insertEvent(newEvent);
            mDashboardViewModel.incrementCategoryById(eventCategoryId.getText().toString());
            //getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new ListCategoriesFragment()).addToBackStack("f2").commit();
            Toast.makeText(getApplicationContext(), "Event saved successfully: " + randomEventId, Toast.LENGTH_LONG).show();

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
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_fragment,new ListCategoriesFragment()).addToBackStack("f2").commit();
        } else if (itemId == R.id.option_clear_event) {
            clearForm();
        } else if (itemId == R.id.option_delete_categories) {
            mDashboardViewModel.deleteAllCategories();
        } else if (itemId == R.id.option_delete_events){
            mDashboardViewModel.deleteAllEvents();
        }
        return true;
    }
    public void startCategoryListButton(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, ShowCategoriesActivity.class);
        startActivity(intent);
    }

    public void startEventListButton(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, ShowEventsActivity.class);
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