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
}
