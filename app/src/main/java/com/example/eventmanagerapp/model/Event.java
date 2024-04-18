package com.example.eventmanagerapp.model;

public class Event {
    private String eventId;
    private String categoryId;
    private String name;
    private int ticketsAvailable;
    private boolean isActive;

    public Event(String eventId, String categoryId, String name, int ticketsAvailable, boolean isActive) {
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
