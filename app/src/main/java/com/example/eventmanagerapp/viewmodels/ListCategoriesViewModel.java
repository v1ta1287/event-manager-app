package com.example.eventmanagerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.repositories.CategoryRepository;

import java.util.List;

public class ListCategoriesViewModel extends AndroidViewModel {
    private CategoryRepository mCategoryRepository;
    private LiveData<List<Category>> mAllCategories;

    public ListCategoriesViewModel(@NonNull Application application) {
        super(application);
        mCategoryRepository = new CategoryRepository(application);
        mAllCategories = mCategoryRepository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

}
