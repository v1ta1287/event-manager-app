package com.example.eventmanagerapp.model;

import com.example.eventmanagerapp.InvalidCategoryIdException;
import com.example.eventmanagerapp.InvalidNameException;
import com.example.eventmanagerapp.PositiveIntegerException;
import com.example.eventmanagerapp.controller.util.SharedPreferencesUtility;

import java.util.ArrayList;
import java.util.Objects;

public class Event {
    private String eventId;
    private String categoryId;
    private String name;
    private int ticketsAvailable;
    private boolean isActive;

    public Event(String eventId, String categoryId, String name, int ticketsAvailable, boolean isActive, ArrayList<Category> categoryArrayList) throws InvalidNameException, PositiveIntegerException, InvalidCategoryIdException {
        if (!(name.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$"))){
            throw new InvalidNameException("Event name is invalid");
        }
        if (ticketsAvailable < 0) {
            throw new PositiveIntegerException("Tickets available is invalid");
        }
        boolean isValidCategoryId = false;
        for (Category category : categoryArrayList) {
            if (Objects.equals(categoryId, category.getCategoryId())){
                isValidCategoryId = true;
                break;
            }
        }
        if (!isValidCategoryId) {
            throw new InvalidCategoryIdException("Category ID is invalid");
        }

        this.eventId = eventId;
        this.categoryId = categoryId;
        this.name = name;
        this.ticketsAvailable = ticketsAvailable;
        this.isActive = isActive;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
