package com.example.eventmanagerapp.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.dao.CategoryDao;
import com.example.eventmanagerapp.data.dao.EventDao;
import com.example.eventmanagerapp.data.databases.EventManagerDB;
import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.model.Event;

import java.util.List;

public class EventManagerRepository {

    private CategoryDao mCategoryDao;
    private LiveData<List<Category>> mAllCategories;
    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvents;

    public EventManagerRepository(Application application) {
        EventManagerDB db = EventManagerDB.getDatabase(application);
        mCategoryDao = db.categoryDao();
        mAllCategories = mCategoryDao.getAllCategories();
        mEventDao = db.eventDao();
        mAllEvents= mEventDao.getAllEvents();
    }
    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }
    public LiveData<List<Event>> getAllEvents() {
        return mAllEvents;
    }
    public void insertCategory(Category category) {
        EventManagerDB.databaseWriteExecutor.execute(() -> mCategoryDao.addCategory(category));
    }

    public void insertEvent(Event event) {
        EventManagerDB.databaseWriteExecutor.execute(() -> mEventDao.addEvent(event));
    }

    public void deleteAllCategories(){
        EventManagerDB.databaseWriteExecutor.execute(()->{
            mCategoryDao.deleteAllCategories();
        });
    }

    public void deleteAllEvents(){
        EventManagerDB.databaseWriteExecutor.execute(()->{
            mEventDao.deleteAllEvents();
        });
    }

    public void incrementById(String id){
        EventManagerDB.databaseWriteExecutor.execute(()->{
            mCategoryDao.incrementById(id);
        });
    }
}
