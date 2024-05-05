package com.example.eventmanagerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.repositories.CategoryRepository;

import java.util.List;

public class NewCategoryViewModel extends AndroidViewModel {
    private CategoryRepository mCategoryRepository;

    public NewCategoryViewModel(@NonNull Application application) {
        super(application);
        mCategoryRepository = new CategoryRepository(application);
    }

    public void insert(Category category) {
        mCategoryRepository.insert(category);
    }
}
