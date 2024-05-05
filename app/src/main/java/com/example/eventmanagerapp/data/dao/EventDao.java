package com.example.eventmanagerapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eventmanagerapp.data.model.Event;

import java.util.List;

@Dao
public interface EventDao {
    @Query("select * from events")
    LiveData<List<Event>> getAllEvents();

    @Query("select * from events where eventName=:name")
    List<Event> getEvents(String name);

    @Insert
    void addEvent(Event event);

    @Query("delete from events where eventName= :name")
    void deleteEvent(String name);

    @Query("delete FROM events")
    void deleteAllEvents();
}
