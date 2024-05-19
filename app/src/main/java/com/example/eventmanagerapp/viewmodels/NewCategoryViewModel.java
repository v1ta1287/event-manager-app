package com.example.eventmanagerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.repositories.EventManagerRepository;

public class NewCategoryViewModel extends AndroidViewModel {
    private EventManagerRepository mEventManagerRepository;

    public NewCategoryViewModel(@NonNull Application application) {
        super(application);
        mEventManagerRepository = new EventManagerRepository(application);
    }

    public void insert(Category category) {
        mEventManagerRepository.insertCategory(category);
    }
}
