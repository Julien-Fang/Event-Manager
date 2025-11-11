package com.projet.event_manager.exceptions.events;

public class EventAlreadyExistsException extends Exception {
    public EventAlreadyExistsException(){
        super("You've already create the same event !!!");
    }
}
