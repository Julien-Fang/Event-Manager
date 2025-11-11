package com.projet.event_manager.exceptions.categories;

public class CategoryNameAlreadyExistsException extends Exception {
    public CategoryNameAlreadyExistsException(String name) {
        super("Already a category with the name " + name + "exists !!!");
    }
}