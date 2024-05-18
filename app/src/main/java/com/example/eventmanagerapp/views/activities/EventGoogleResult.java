package com.example.eventmanagerapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.eventmanagerapp.R;

import java.util.Objects;

public class EventGoogleResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_google_result);

        Toolbar toolbar = findViewById(R.id.web_view_toolbar);
        toolbar.setTitle("View Google Results");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // using the ID set in previous step, get reference to the WebView
        WebView webView = findViewById(R.id.webView);

        // get country name from Intent
        String eventName = getIntent().getExtras().getString("eventName");


        // compile the Wikipedia URL, which will be used to load into WebView
        String googlePageURL = "https://www.google.com/search?q=" + eventName;

        // set new WebView Client for the WebView
        // This gives the WebView ability to be load the URL in the current WebView
        // instead of navigating to default web browser of the device
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(googlePageURL);
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