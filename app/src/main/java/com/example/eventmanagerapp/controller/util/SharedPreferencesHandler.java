package com.example.eventmanagerapp.controller.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.eventmanagerapp.model.Category;
import com.example.eventmanagerapp.model.Event;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferencesHandler {
    private final static String categorySharedPreferenceFile = "CATEGORY_LIST";
    private final static String categorySharedPreferenceString = "categories";
    private final static String eventSharedPreferenceFile = "EVENT_LIST";
    private final static String eventSharedPreferenceString = "events";
    public static void saveCategoryToSharedPreference(Context context, Category category) {
        // save arguments to shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(categorySharedPreferenceFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String sharedPreferencesString = sharedPreferences.getString(categorySharedPreferenceString, "[]");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Category>>() {}.getType();
        ArrayList<Category> categoryArrayList;
        categoryArrayList = gson.fromJson(sharedPreferencesString,type);
        categoryArrayList.add(category);
        String updatedSharedPreferencesString = gson.toJson(categoryArrayList);
        editor.putString(categorySharedPreferenceString, updatedSharedPreferencesString);
        editor.apply();
    }

    public static ArrayList<Category> getCategoriesFromSharedPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(categorySharedPreferenceFile, Context.MODE_PRIVATE);
        String sharedPreferencesString = sharedPreferences.getString(categorySharedPreferenceString, "[]");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Category>>() {}.getType();
        ArrayList<Category> categoryArrayList;
        categoryArrayList = gson.fromJson(sharedPreferencesString,type);

        return categoryArrayList;
    }

    public static void saveEventToSharedPreference(Context context, Event event) {
        // save arguments to shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(eventSharedPreferenceFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String sharedPreferencesString = sharedPreferences.getString(eventSharedPreferenceString, "[]");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Event>>() {}.getType();
        ArrayList<Event> eventArrayList;
        eventArrayList = gson.fromJson(sharedPreferencesString,type);
        eventArrayList.add(event);
        String updatedSharedPreferencesString = gson.toJson(eventArrayList);
        editor.putString(eventSharedPreferenceString, updatedSharedPreferencesString);
        editor.apply();
    }

    public static ArrayList<Event> getEventsFromSharedPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(eventSharedPreferenceFile, Context.MODE_PRIVATE);
        String sharedPreferencesString = sharedPreferences.getString(eventSharedPreferenceString, "[]");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Event>>() {}.getType();
        ArrayList<Event> eventArrayList;
        eventArrayList = gson.fromJson(sharedPreferencesString,type);

        return eventArrayList;
    }
}
