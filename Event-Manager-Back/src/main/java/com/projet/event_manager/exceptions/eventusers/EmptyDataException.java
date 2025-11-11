package com.projet.event_manager.exceptions.eventusers;

public class EmptyDataException extends Exception {
    public EmptyDataException(){
        super("All fields must be filled out !!!");
    }
}
