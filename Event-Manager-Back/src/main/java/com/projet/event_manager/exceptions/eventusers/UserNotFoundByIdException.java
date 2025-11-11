package com.projet.event_manager.exceptions.eventusers;

import java.util.UUID;

public class UserNotFoundByIdException extends  Exception {
    public UserNotFoundByIdException(UUID id) {
        super("No user with the id : " + id + "have been found!!!");
    }
}