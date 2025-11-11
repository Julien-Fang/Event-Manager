package com.projet.event_manager.services;

import com.projet.event_manager.dto.EventRequest;
import com.projet.event_manager.exceptions.events.*;
import com.projet.event_manager.exceptions.eventusers.UserNotFoundByIdException;
import com.projet.event_manager.models.Event;

import java.util.List;
import java.util.UUID;

public interface EventService {
    List<Event> retrieveAllEvents();
    Event retrieveEventById(UUID id) throws EventNotFoundByIdException;
    Event createEvent(EventRequest eventRequest) throws EventAlreadyExistsException, EmptyDescriptionException;
    Event updateEvent(UUID id, EventRequest eventRequest) throws EventNotFoundByIdException;
    void deleteEventById(UUID id) throws EventNotFoundByIdException;
    List<Event> filterByString(String search,String filter) throws UserNotFoundByIdException;
    List<Event> findByCreatedBy(UUID createdBy);
    List<Event> filterByDate();
    List<Event> retrieveUserEvents(UUID eventUserId) throws EventNotFoundByIdException;
}
