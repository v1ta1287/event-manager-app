package com.example.eventmanagerapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eventmanagerapp.data.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("select * from categories")
    LiveData<List<Category>> getAllCategories();

    @Insert
    void addCategory(Category category);

    @Query("delete FROM categories")
    void deleteAllCategories();

    @Query("update categories set eventCount = eventCount + 1 where categoryStringId=:categoryStringId")
    void incrementById(String categoryStringId);
}
