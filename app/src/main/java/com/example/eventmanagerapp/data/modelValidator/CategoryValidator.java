package com.example.eventmanagerapp.data.modelValidator;

import com.example.eventmanagerapp.data.model.Category;

public class CategoryValidator {
    private final Category validatedCategory;
    public CategoryValidator(String categoryId, String name, int eventCount, boolean isActive, String eventLocation) throws InvalidNameException, PositiveIntegerException {
        if (!(name.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$"))){
            throw new InvalidNameException("Category name is invalid");
        }
        if (eventCount < 0) {
            throw new PositiveIntegerException("Event count is invalid");
        }
        validatedCategory = new Category(categoryId, name, eventCount, isActive, eventLocation);
    }

    public Category getValidatedCategory() {
        return validatedCategory;
    }
}
