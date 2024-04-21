package com.example.eventmanagerapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventmanagerapp.R;

/**
 * Controller for user login page
 * SharedPreference logic is still implemented as part of the controller from legacy code
 * In the future, this will be moved to SharedPreferencesUtility
 */
public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById((R.id.loginUsernameEdit));
        password = findViewById(R.id.loginPasswordEdit);

        // prefill username from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("LOGIN_INFO", MODE_PRIVATE);
        String usernameRestored = sharedPreferences.getString("USERNAME", "");
        username.setText(usernameRestored);
    }

    public void onLoginButton (View view){
        SharedPreferences sharedPreferences = getSharedPreferences("LOGIN_INFO", MODE_PRIVATE);
        String passwordRestored = sharedPreferences.getString("PASSWORD", "");

        // check if password matches the retrieved password from shared preferences
        String passwordString = password.getText().toString();

        if (passwordRestored.equals(passwordString)) {
            startDashboardActivity();
        }
        else {
            Toast.makeText(getApplicationContext(), "Username or Password incorrect", Toast.LENGTH_LONG).show();
            password.getText().clear();
        }
    }

    public void onRegisterButton(View view) {
        startRegisterActivity();
    }

    public void startDashboardActivity(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    public void startRegisterActivity(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
