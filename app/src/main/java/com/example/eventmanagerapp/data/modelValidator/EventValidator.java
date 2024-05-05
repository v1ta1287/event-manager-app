package com.example.eventmanagerapp.data.modelValidator;

import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.data.model.Event;

import java.util.List;
import java.util.Objects;

public class EventValidator {

    private Event validatedEvent;

    public EventValidator(String eventId, String categoryId, String name, int ticketsAvailable, boolean isActive, List<Category> categoryList) throws InvalidNameException, PositiveIntegerException, InvalidCategoryIdException {
        if (!(name.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$"))){
            throw new InvalidNameException("Event name is invalid");
        }
        if (ticketsAvailable < 0) {
            throw new PositiveIntegerException("Tickets available is invalid");
        }

        boolean isValidCategoryId = false;
        for (Category category : categoryList) {
            if (Objects.equals(categoryId, category.getCategoryId())){
                isValidCategoryId = true;
                break;
            }
        }
        if (!isValidCategoryId) {
            throw new InvalidCategoryIdException("Category ID is invalid");
        }

        validatedEvent = new Event(eventId,categoryId, name, ticketsAvailable, isActive);
    }

    public Event getValidatedEvent() {
        return validatedEvent;
    }
}
