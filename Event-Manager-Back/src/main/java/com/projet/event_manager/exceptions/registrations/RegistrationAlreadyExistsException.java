package com.projet.event_manager.exceptions.registrations;

import java.util.UUID;

public class RegistrationAlreadyExistsException extends Exception{

    public RegistrationAlreadyExistsException(UUID event_id) {
        super("You've already been register for this event !!!\" " + event_id);
    }

}
