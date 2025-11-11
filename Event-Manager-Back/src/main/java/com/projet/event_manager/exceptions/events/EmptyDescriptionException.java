package com.projet.event_manager.exceptions.events;

public class EmptyDescriptionException extends Exception{
    public EmptyDescriptionException(){
        super("Event description is required !!");
    }
}
