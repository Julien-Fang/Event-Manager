package com.projet.event_manager.services;

import com.projet.event_manager.dto.EventUserRequest;
import com.projet.event_manager.exceptions.eventusers.*;
import com.projet.event_manager.models.EventUser;

import java.util.List;
import java.util.UUID;

public interface EventUserService {
    EventUser retrieveUserById(UUID id) throws UserNotFoundByIdException;
    EventUser createUser(EventUserRequest eventUserRequest) throws InvalidEmailException, PseudoAlreadyExistsException, EmptyDataException;
    EventUser updateUser(UUID id, EventUserRequest eventUserRequest) throws EmptyDataException, UserNotFoundByIdException;
    void deleteUserById(UUID id) throws UserNotFoundByIdException;
    EventUser authenticateUser(String pseudo, String password) throws AuthenticationException;
    List<EventUser> retrieveAllUsers();
    List<EventUser> retrieveAllUsersByEventId(UUID eventId) throws UserNotFoundByIdException;
}
