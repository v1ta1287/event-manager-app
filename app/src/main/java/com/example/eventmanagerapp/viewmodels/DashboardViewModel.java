package com.example.eventmanagerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.model.Event;
import com.example.eventmanagerapp.data.repositories.EventManagerRepository;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    private EventManagerRepository mEventManagerRepository;
    private LiveData<List<Category>> mAllCategories;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        mEventManagerRepository = new EventManagerRepository(application);
        mAllCategories = mEventManagerRepository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

    public void insertEvent(Event event) {
        mEventManagerRepository.insertEvent(event);
    }
    public void deleteAllCategories(){
        mEventManagerRepository.deleteAllCategories();
    }
    public void deleteAllEvents(){
        mEventManagerRepository.deleteAllEvents();
    }
    public void incrementCategoryById(String id){
        mEventManagerRepository.incrementById(id);}

}
