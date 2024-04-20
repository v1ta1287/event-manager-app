package com.example.eventmanagerapp.controller.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.controller.util.IdGeneratorUtility;
import com.example.eventmanagerapp.controller.util.SharedPreferencesUtility;
import com.example.eventmanagerapp.model.Category;


public class NewCategoryActivity extends AppCompatActivity {
    EditText categoryName;
    EditText categoryCount;
    EditText categoryId;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch categoryIsActive;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryName = findViewById((R.id.categoryNameEdit));
        categoryCount = findViewById((R.id.categoryCountEdit));
        categoryIsActive = findViewById((R.id.categoryActiveSwitch));
        categoryId = findViewById((R.id.categoryIdEdit));
    }


    public void onSaveButton(View view){
        // save filled out category to shared preferences and show success message
        // first check that name is not empty
        if (categoryName.getText().toString().length() > 0){
            String randomCategoryId = IdGeneratorUtility.generateCategoryId();

            Category newCategory = new Category(randomCategoryId, categoryName.getText().toString(),
                    Integer.parseInt(categoryCount.getText().toString()), Boolean.parseBoolean(categoryIsActive.getText().toString()));

            // save to shared preferences
            SharedPreferencesUtility.saveCategoryToSharedPreference(getApplicationContext(), newCategory);
            Toast.makeText(getApplicationContext(), "Category saved successfully: " + randomCategoryId, Toast.LENGTH_LONG).show();
            categoryId.setText(randomCategoryId);

            Intent intent = new Intent(this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(intent, 0);
        }
        else {
            Toast.makeText(getApplicationContext(), "Name field cannot be empty", Toast.LENGTH_LONG).show();
        }
    }



}