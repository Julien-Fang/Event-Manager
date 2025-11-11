package com.projet.event_manager.exceptions.events;

import java.util.UUID;

public class EventNotFoundByIdException extends  Exception {
    public EventNotFoundByIdException(UUID id) {
        super("No event with the id : " + id + "have been found!!!");
    }
}