package com.example.eventmanagerapp.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.dao.EventDao;
import com.example.eventmanagerapp.data.databases.EventManagerDB;
import com.example.eventmanagerapp.data.model.Event;

import java.util.List;

public class EventRepository  {

    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvents;

    public EventRepository(Application application) {
        EventManagerDB db = EventManagerDB.getDatabase(application);
        mEventDao = db.eventDao();
        mAllEvents= mEventDao.getAllEvents();
    }
    public LiveData<List<Event>> getAllEvents() {
        return mAllEvents;
    }
    public void insert(Event event) {
        EventManagerDB.databaseWriteExecutor.execute(() -> mEventDao.addEvent(event));
    }

    public void deleteAll(){
        EventManagerDB.databaseWriteExecutor.execute(()->{
            mEventDao.deleteAllEvents();
        });
    }

    public void deleteByName(String name){
        EventManagerDB.databaseWriteExecutor.execute(()->{
            mEventDao.deleteEvent(name);
        });
    }
}
