package com.projet.event_manager.exceptions.eventusers;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(){
        super("Invalid email address format !!!");
    }
}
