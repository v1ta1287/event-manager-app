package com.example.eventmanagerapp.controller;

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

import java.util.Objects;
import java.util.Random;
import java.util.StringTokenizer;

public class EventActivity extends AppCompatActivity {

    EditText eventName;
    EditText eventCategoryId;
    EditText eventTickets;
    EditText eventId;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch eventIsActive;
    private final BroadcastReceiver eventBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(SMSReceiver.SMS_KEY);
            try {
                // first split occurs at colon ":"
                StringTokenizer checkMsgType = new StringTokenizer(msg, ":");
                String msgType = checkMsgType.nextToken();
                if (Objects.equals(msgType, "event")) {
                    prefillEventForm(msg);
                }else {
                    Toast.makeText(EventActivity.this, "SMS message had incorrect values or format", Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_event);

        eventName = findViewById((R.id.eventNameEdit));
        eventCategoryId = findViewById((R.id.eventCategoryIdEdit));
        eventTickets = findViewById((R.id.eventTicketsEdit));
        eventIsActive = findViewById((R.id.eventActiveSwitch));
        eventId = findViewById((R.id.eventIdEdit));

        ActivityCompat.requestPermissions(this, new String[]{
                android.Manifest.permission.SEND_SMS,
                android.Manifest.permission.RECEIVE_SMS,
                android.Manifest.permission.READ_SMS
        }, 0);

        registerReceiver(eventBroadcastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER), RECEIVER_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(eventBroadcastReceiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(eventBroadcastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER), RECEIVER_EXPORTED);
    }

    // Validates SMS message and prefills event form if the SMS message is valid
    public void prefillEventForm(String msg){
        String attributes;
        String eventToken;
        String nameToken = null;
        String categoryIdToken = null;
        String ticketToken = null;
        String isActiveToken = null;
        boolean isValidMessage = true;

        try {
            // first split occurs at colon ":"
            StringTokenizer checkEvent= new StringTokenizer(msg, ":");
            eventToken = checkEvent.nextToken();

            // rest of the attributes are tokenized with semicolon delimiter ";"
            attributes = checkEvent.nextToken();
            StringTokenizer sT = new StringTokenizer(attributes, ";");

            // if there are an invalid number of entries, then we have an invalid message
            int tokenNumber = sT.countTokens();
            if (tokenNumber != 4){
                isValidMessage = false;
            }

            // get token values
            nameToken = sT.nextToken();
            categoryIdToken = sT.nextToken();
            ticketToken = sT.nextToken();
            isActiveToken = sT.nextToken();

            // check if event tag is present
            if (!eventToken.equals("event")){
                isValidMessage = false;
            }

            // check if tickets is a positive integer
            try {
                int check = Integer.parseInt(ticketToken);
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
            eventName.setText(nameToken);
            eventCategoryId.setText(categoryIdToken);
            eventTickets.setText(ticketToken);
            eventIsActive.setChecked(isActiveToken.equalsIgnoreCase("TRUE"));
        }
        // otherwise, show error toast message
        else {
            Toast.makeText(EventActivity.this, "Event SMS message had incorrect values or format", Toast.LENGTH_LONG).show();
        }
    }

    // generates a random event ID based on assignment specs
    public String generateEventId() {
        // Build eventId, starting off with appending E
        StringBuilder eventId = new StringBuilder();
        eventId.append("E");

        // Append two random characters
        Random random = new Random();
        char randomizedCharacter;
        for (int i = 0; i < 2; i++){
            randomizedCharacter = (char) (random.nextInt(26) + 'a');
            eventId.append(Character.toUpperCase(randomizedCharacter));
        }

        // Append hyphen
        eventId.append("-");

        // Append five random digits
        for (int i = 0; i < 5; i++){
            eventId.append(random.nextInt(10));
        }

        return eventId.toString();
    }

    public void onSaveButton(View view){
        // save filled out event to shared preferences and show success message
        // first check that name and category id are not empty
        if (eventName.getText().toString().length() > 0 && eventCategoryId.getText().toString().length() > 0){
            String randomEventId = generateEventId();
            // do error handling if category count is empty/not an integer
            int eventTicketsInt;
            try {
                eventTicketsInt = Integer.parseInt(eventTickets.getText().toString());
            } catch (NumberFormatException e) {
                // if theres no valid value, set default as 0
                eventTicketsInt = 0;
            }

            // save to shared preferences
            saveCategoryToSharedPreference(randomEventId, eventName.getText().toString(),
                    eventCategoryId.getText().toString(),
                    eventTicketsInt,
                    Boolean.parseBoolean(eventIsActive.getText().toString()));
            Toast.makeText(getApplicationContext(), "Event saved: " + randomEventId + " to " + eventCategoryId.getText().toString(), Toast.LENGTH_LONG).show();
            eventId.setText(randomEventId);
        } else {
            Toast.makeText(getApplicationContext(), "Name and category id field cannot be empty", Toast.LENGTH_LONG).show();
        }

    }

    // save event details to shared preferences
    public void saveCategoryToSharedPreference(String id, String name, String categoryId, int count, boolean isActive ){
        // save arguments to shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("EVENT_INFO", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("EVENT_ID", id);
        editor.putString("EVENT_NAME", name);
        editor.putString("EVENT_CATEGORY_ID", categoryId);
        editor.putInt("EVENT_TICKETS", count);
        editor.putBoolean("EVENT_IS_ACTIVE", isActive);

        editor.apply();
    }
}