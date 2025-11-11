package com.projet.event_manager.exceptions.categories;

import java.util.UUID;

public class CategoryNotFoundByIdException extends  Exception {
    public CategoryNotFoundByIdException(UUID id) {
        super("No category with the id : " + id + "have been found!!!");
    }
}