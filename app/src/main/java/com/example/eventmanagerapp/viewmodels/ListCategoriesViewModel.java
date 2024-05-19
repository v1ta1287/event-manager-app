package com.example.eventmanagerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.repositories.EventManagerRepository;

import java.util.List;

public class ListCategoriesViewModel extends AndroidViewModel {
    private EventManagerRepository mEventManagerRepository;
    private LiveData<List<Category>> mAllCategories;

    public ListCategoriesViewModel(@NonNull Application application) {
        super(application);
        mEventManagerRepository = new EventManagerRepository(application);
        mAllCategories = mEventManagerRepository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

}
