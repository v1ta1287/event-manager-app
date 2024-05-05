package com.example.eventmanagerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.model.Event;
import com.example.eventmanagerapp.data.repositories.CategoryRepository;
import com.example.eventmanagerapp.data.repositories.EventRepository;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    private CategoryRepository mCategoryRepository;
    private EventRepository mEventRepository;
    private LiveData<List<Category>> mAllCategories;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        mCategoryRepository = new CategoryRepository(application);
        mEventRepository = new EventRepository(application);
        mAllCategories = mCategoryRepository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

    public void insertEvent(Event event) {
        mEventRepository.insert(event);
    }
    public void deleteAllCategories(){
        mCategoryRepository.deleteAll();
    }
    public void deleteAllEvents(){
        mEventRepository.deleteAll();
    }
    public void incrementCategoryById(String id){mCategoryRepository.incrementById(id);}

}
