package com.example.eventmanagerapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventmanagerapp.R;

/**
 * Controller for user registration page
 * SharedPreference logic is still implemented as part of the controller from legacy code
 * In the future, this will be moved to SharedPreferencesUtility
 */
public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.registerUsernameEdit);
        password = findViewById(R.id.registerPasswordEdit);
        confirmPassword = findViewById(R.id.registerConfirmedEdit);
    }


    public void onRegisterButton(View view) {
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();
        String confirmPasswordString = confirmPassword.getText().toString();

        // first check that username and password fields are not empty
        if (usernameString.length() > 0 && passwordString.length() > 0) {
            // check for matching passwords
            if (passwordString.equals(confirmPasswordString)) {
                saveUserToSharedPreference(usernameString, passwordString);
                Toast.makeText(getApplicationContext(), "Account successfully registered", Toast.LENGTH_LONG).show();
                startLoginActivity();
            }
            else {
                Toast.makeText(getApplicationContext(), "Passwords are not matching", Toast.LENGTH_LONG).show();
                password.getText().clear();
                confirmPassword.getText().clear();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Username and password must be filled", Toast.LENGTH_LONG).show();
        }

    }

    public void onLoginButton(View view) {
        startLoginActivity();
    }

    public void saveUserToSharedPreference(String username, String password ){
        // save arguments to shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("LOGIN_INFO", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("USERNAME", username);
        editor.putString("PASSWORD", password);

        editor.apply();
    }

    public void startLoginActivity(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}