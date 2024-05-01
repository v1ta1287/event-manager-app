package com.example.eventmanagerapp.views.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.eventmanagerapp.data.modelValidator.InvalidNameException;
import com.example.eventmanagerapp.data.modelValidator.PositiveIntegerException;
import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.data.modelValidator.CategoryValidator;
import com.example.eventmanagerapp.utilities.IdGeneratorUtility;
import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.viewmodels.CategoryViewModel;

/**
 * Controller for new category activity
 * Handles form input logic
 */

public class NewCategoryActivity extends AppCompatActivity {
    EditText categoryName;
    EditText categoryCount;
    EditText categoryId;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch categoryIsActive;
    CategoryViewModel mCategoryViewModel;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryName = findViewById((R.id.categoryNameEdit));
        categoryCount = findViewById((R.id.categoryCountEdit));
        categoryIsActive = findViewById((R.id.categoryActiveSwitch));
        categoryId = findViewById((R.id.categoryIdEdit));

        mCategoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
    }


    public void onSaveButton(View view) {
        String randomCategoryId = IdGeneratorUtility.generateCategoryId();
        String categoryEventCount;

        // if categoryCount is left blank, default to 0
        if (categoryCount.getText().toString().equals("")){
            categoryEventCount = "0";
        } else {
            categoryEventCount = categoryCount.getText().toString();
        }
        try {
            // try creating a new Category object and print the appropriate toast error message
            // if input validation fails
            CategoryValidator newCategoryValidator = new CategoryValidator(randomCategoryId, categoryName.getText().toString(),
                    Integer.parseInt(categoryEventCount), categoryIsActive.isChecked());

            Category newCategory = newCategoryValidator.getValidatedCategory();
            mCategoryViewModel.insert(newCategory);
            Toast.makeText(getApplicationContext(), "Category saved successfully: " + randomCategoryId, Toast.LENGTH_LONG).show();
            categoryId.setText(randomCategoryId);

            // return to DashboardActivity without starting a new activity, preventing fragment refresh
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(intent, 0);
        }catch (InvalidNameException e) {
            Toast.makeText(getApplicationContext(), "Invalid category name", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException | PositiveIntegerException e) {
            Toast.makeText(getApplicationContext(), "Invalid event count", Toast.LENGTH_LONG).show();
            categoryCount.setText("0");
        }

    }

}