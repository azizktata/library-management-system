package com.example.librarybooks.error;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
