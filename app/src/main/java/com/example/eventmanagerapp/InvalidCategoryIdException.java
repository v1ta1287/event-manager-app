package com.example.eventmanagerapp;

public class InvalidCategoryIdException extends Exception{
    public InvalidCategoryIdException(String message){
        super(message);
    }
}
