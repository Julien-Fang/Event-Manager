package com.projet.event_manager.exceptions.eventusers;

public class PseudoAlreadyExistsException extends Exception {
    public PseudoAlreadyExistsException(String name){
        super("Already a user with the pseudo " + name + "exists !!!");
    }
}
