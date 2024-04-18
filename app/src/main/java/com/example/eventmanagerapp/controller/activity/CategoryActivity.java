package com.example.eventmanagerapp.controller.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.controller.handler.SMSReceiver;

import java.util.Objects;
import java.util.Random;
import java.util.StringTokenizer;

public class CategoryActivity extends AppCompatActivity {

    EditText categoryName;
    EditText categoryCount;
    EditText categoryId;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch categoryIsActive;

    private final BroadcastReceiver categoryBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(SMSReceiver.SMS_KEY);
            try {
                // first split occurs at colon ":"
                StringTokenizer checkMsgType = new StringTokenizer(msg, ":");
                String msgType = checkMsgType.nextToken();
                if (Objects.equals(msgType, "category")) {
                    prefillCategoryForm(msg);
                } else {
                    Toast.makeText(CategoryActivity.this, "SMS message had incorrect values or format", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception e) {
                Log.d("ReceiverError", String.valueOf(e));
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryName = findViewById((R.id.categoryNameEdit));
        categoryCount = findViewById((R.id.categoryCountEdit));
        categoryIsActive = findViewById((R.id.categoryActiveSwitch));
        categoryId = findViewById((R.id.categoryIdEdit));


        ActivityCompat.requestPermissions(this, new String[]{
                android.Manifest.permission.SEND_SMS,
                android.Manifest.permission.RECEIVE_SMS,
                android.Manifest.permission.READ_SMS
        }, 0);

        registerReceiver(categoryBroadcastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER), RECEIVER_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(categoryBroadcastReceiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(categoryBroadcastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER), RECEIVER_EXPORTED);
    }

    // Validates SMS message and prefills category form if the SMS message is valid
    public void prefillCategoryForm(String msg){
        String attributes;
        String categoryToken;
        String nameToken = null;
        String countToken = null;
        String isActiveToken = null;
        boolean isValidMessage = true;

        try {
            // first split occurs at colon ":"
            StringTokenizer checkCategory = new StringTokenizer(msg, ":");
            categoryToken = checkCategory.nextToken();

            // rest of the attributes are tokenized with semicolon delimiter ";"
            attributes = checkCategory.nextToken();
            StringTokenizer attributesTokenizer = new StringTokenizer(attributes, ";");

            // if there are an invalid number of entries, then we have an invalid message
            int tokenNumber = attributesTokenizer.countTokens();
            if (tokenNumber != 3){
                isValidMessage = false;
            }

            // get token values
            nameToken = attributesTokenizer.nextToken();
            countToken = attributesTokenizer.nextToken();
            isActiveToken = attributesTokenizer.nextToken();

            // check if category tag is present
            if (!categoryToken.equals("category")){
                isValidMessage = false;
            }

            // check if count is a positive integer
            try {
                int check = Integer.parseInt(countToken);
                if (check < 0){
                    isValidMessage = false;
                }
            } catch (NumberFormatException e){
                isValidMessage = false;
            }

            // check if isActiveToken is a valid boolean
            if (!(isActiveToken.equalsIgnoreCase("TRUE") || isActiveToken.equalsIgnoreCase("FALSE"))){
                isValidMessage = false;
            }

        } catch (Exception e){
            isValidMessage = false;
        }

        // if the SMS message had passed all validations, then prefill form
        if (isValidMessage){
            categoryName.setText(nameToken);
            categoryCount.setText(countToken);
            categoryIsActive.setChecked(isActiveToken.equalsIgnoreCase("TRUE"));
        }
        // otherwise, show error toast message
        else {
            Toast.makeText(CategoryActivity.this, "SMS message had incorrect values or format", Toast.LENGTH_LONG).show();
        }
    }

    // generate random category id based on assignment specs
    public String generateCategoryId() {
        // Build categoryId, starting off with appending C
        StringBuilder categoryId = new StringBuilder();
        categoryId.append("C");

        // Append two random characters
        Random random = new Random();
        char randomizedCharacter;
        for (int i = 0; i < 2; i++){
            randomizedCharacter = (char) (random.nextInt(26) + 'a');
            categoryId.append(Character.toUpperCase(randomizedCharacter));
        }

        // Append hyphen
        categoryId.append("-");

        // Append four random digits
        for (int i = 0; i < 4; i++){
            categoryId.append(random.nextInt(10));
        }

        return categoryId.toString();
    }

    public void onSaveButton(View view){
        // save filled out category to shared preferences and show success message
        // first check that name is not empty
        if (categoryName.getText().toString().length() > 0){
            String randomCategoryId = generateCategoryId();

            // do error handling if category count is empty/not an integer
            int categoryCountInt;
            try {
                categoryCountInt = Integer.parseInt(categoryCount.getText().toString());
            } catch (NumberFormatException e) {
                // if theres no valid value, set default as 0
                categoryCountInt = 0;
            }

            // save to shared preferences
            saveCategoryToSharedPreference(randomCategoryId, categoryName.getText().toString(),
                    categoryCountInt,
                    Boolean.parseBoolean(categoryIsActive.getText().toString()));
            Toast.makeText(getApplicationContext(), "Category saved successfully: " + randomCategoryId, Toast.LENGTH_LONG).show();
            categoryId.setText(randomCategoryId);

        }
        else {
            Toast.makeText(getApplicationContext(), "Name field cannot be empty", Toast.LENGTH_LONG).show();
        }
    }

    // save event details to shared preferences
    public void saveCategoryToSharedPreference(String id, String name, int count, boolean isActive ){
        // save arguments to shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("CATEGORY_INFO", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("CATEGORY_ID", id);
        editor.putString("CATEGORY_NAME", name);
        editor.putInt("CATEGORY_COUNT", count);
        editor.putBoolean("CATEGORY_IS_ACTIVE", isActive);

        editor.apply();
    }

}