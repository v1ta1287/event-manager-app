package com.example.eventmanagerapp.model;

public class Category {
    private String categoryId;
    private String name;
    private int eventCount;
    private boolean isActive;

    public Category(String categoryId, String name, int eventCount, boolean isActive) {
        this.categoryId = categoryId;
        this.name = name;
        this.eventCount = eventCount;
        this.isActive = isActive;
    }
}
