package com.example.eventmanagerapp.model;

import com.example.eventmanagerapp.InvalidNameException;
import com.example.eventmanagerapp.PositiveIntegerException;

public class Category {
    private String categoryId;
    private String name;
    private int eventCount;
    private boolean isActive;

    public Category(String categoryId, String name, int eventCount, boolean isActive) throws InvalidNameException, PositiveIntegerException {
        if (!(name.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$"))){
            throw new InvalidNameException("Category name is invalid");
        }
        if (eventCount < 0) {
            throw new PositiveIntegerException("Event count is invalid");
        }
        this.categoryId = categoryId;
        this.name = name;
        this.eventCount = eventCount;
        this.isActive = isActive;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
