package com.example.eventmanagerapp.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.dao.CategoryDao;
import com.example.eventmanagerapp.data.databases.EventManagerDB;
import com.example.eventmanagerapp.data.model.Category;

import java.util.List;

public class CategoryRepository  {

    private CategoryDao mCategoryDao;
    private LiveData<List<Category>> mAllCategories;

    public CategoryRepository(Application application) {
        EventManagerDB db = EventManagerDB.getDatabase(application);
        mCategoryDao = db.categoryDao();
        mAllCategories = mCategoryDao.getAllCustomer();
    }
    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }
    public void insert(Category customer) {
        EventManagerDB.databaseWriteExecutor.execute(() -> mCategoryDao.addCustomer(customer));
    }

    public void deleteAll(){
        EventManagerDB.databaseWriteExecutor.execute(()->{
            mCategoryDao.deleteAllCustomers();
        });
    }

    public void deleteByName(String name){
        EventManagerDB.databaseWriteExecutor.execute(()->{
            mCategoryDao.deleteCustomer(name);
        });
    }
}
