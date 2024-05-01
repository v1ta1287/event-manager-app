package com.example.eventmanagerapp.data.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.eventmanagerapp.data.dao.CategoryDao;
import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.model.Event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, Event.class}, version = 1)
public abstract class EventManagerDB extends RoomDatabase {

    // database name, this is important as data is contained inside a file named "card_database"
    public static final String EVENT_MANAGER_DATABASE_NAME = "event_manager_database_v2";

    public abstract CategoryDao categoryDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile EventManagerDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    // ExecutorService is a JDK API that simplifies running tasks in asynchronous mode.
    // Generally speaking, ExecutorService automatically provides a pool of threads and an API
    // for assigning tasks to it.
    static public final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static public EventManagerDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EventManagerDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EventManagerDB.class, EVENT_MANAGER_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}