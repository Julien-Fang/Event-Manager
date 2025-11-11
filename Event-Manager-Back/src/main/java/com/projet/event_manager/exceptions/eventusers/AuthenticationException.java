package com.projet.event_manager.exceptions.eventusers;

public class AuthenticationException extends Exception {
    public AuthenticationException(){
        super("Incorrect pseudo or password !!!");
    }
}
