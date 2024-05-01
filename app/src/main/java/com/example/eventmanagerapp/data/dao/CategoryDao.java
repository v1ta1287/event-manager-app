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
    LiveData<List<Category>> getAllCustomer();

    @Query("select * from categories where categoryName=:name")
    List<Category> getCustomer(String name);

    @Insert
    void addCustomer(Category customer);

    @Query("delete from categories where categoryName= :name")
    void deleteCustomer(String name);

    @Query("delete FROM categories")
    void deleteAllCustomers();
}
