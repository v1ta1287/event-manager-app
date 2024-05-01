package com.example.eventmanagerapp.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.eventmanagerapp.InvalidCategoryIdException;
import com.example.eventmanagerapp.InvalidNameException;
import com.example.eventmanagerapp.PositiveIntegerException;

import java.util.ArrayList;
import java.util.Objects;

@Entity(tableName = Event.TABLE_NAME)
public class Event {
    public static final String TABLE_NAME = "events";
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "eventId")
    private int id;

    @ColumnInfo(name = "eventStringId")
    private String eventId;

    @ColumnInfo(name = "categoryStringId")
    private String categoryId;

    @ColumnInfo(name = "eventName")
    private String name;

    @ColumnInfo(name = "ticketsAvailable")
    private int ticketsAvailable;

    @ColumnInfo(name = "isActive")
    private boolean isActive;

    public Event(String eventId, String categoryId, String name, int ticketsAvailable, boolean isActive) throws InvalidNameException, PositiveIntegerException, InvalidCategoryIdException {
        if (!(name.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$"))){
            throw new InvalidNameException("Event name is invalid");
        }
        if (ticketsAvailable < 0) {
            throw new PositiveIntegerException("Tickets available is invalid");
        }

        ArrayList<Category> categoryArrayList = new ArrayList<>();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
