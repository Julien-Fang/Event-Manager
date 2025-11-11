package com.projet.event_manager.services;

import com.projet.event_manager.dto.RegistrationRequest;
import com.projet.event_manager.exceptions.registrations.RegistrationAlreadyExistsException;
import com.projet.event_manager.exceptions.registrations.RegistrationNotFoundByIdException;
import com.projet.event_manager.models.Registration;

import java.util.List;
import java.util.UUID;

public interface RegistrationService {
    Registration retrieveRegistrationById(UUID id) throws RegistrationNotFoundByIdException;
    List<Registration> retrieveAllRegistrationsByEventId(UUID id);
    List<Registration> retrieveAllRegistrationsByUserId(UUID eventUserId);
    Registration createRegistration(RegistrationRequest registrationRequest) throws RegistrationAlreadyExistsException;
    void deleteRegistrationById(UUID id) throws RegistrationNotFoundByIdException;
}
