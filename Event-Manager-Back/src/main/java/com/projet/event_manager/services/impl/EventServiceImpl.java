package com.projet.event_manager.services.impl;

import com.projet.event_manager.dto.EventRequest;
import com.projet.event_manager.exceptions.events.*;
import com.projet.event_manager.exceptions.eventusers.UserNotFoundByIdException;
import com.projet.event_manager.models.Event;
import com.projet.event_manager.models.Registration;
import com.projet.event_manager.repositories.EventRepository;
import com.projet.event_manager.services.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final RegistrationServiceImpl registrationService;

    public EventServiceImpl(EventRepository eventRepository, RegistrationServiceImpl registrationService) {
        this.eventRepository = eventRepository;
        this.registrationService = registrationService;
    }
    @Override
    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event retrieveEventById(UUID id) throws EventNotFoundByIdException{
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundByIdException(id));

    }

    @Override
    public Event createEvent(EventRequest eventRequest) throws EventAlreadyExistsException, EmptyDescriptionException {
        if(eventRequest.getEventDescription().trim().isEmpty()){
            throw new EmptyDescriptionException();
        }
        if(!eventRepository.findEvent(eventRequest.getCreatedBy(),eventRequest.getEventDate(),eventRequest.getCategoryName(),eventRequest.getEventLocation(),eventRequest.getEventDescription(),eventRequest.getMaxCapacity(),eventRequest.getImage()).isEmpty()) {
            throw new EventAlreadyExistsException();
        }
        Event event = new Event(eventRequest.getEventName(),eventRequest.getCreatedBy(), LocalDateTime.now(),eventRequest.getCategoryName(),eventRequest.getEventDate(),eventRequest.getEventLocation(),eventRequest.getEventDescription(),eventRequest.getMaxCapacity(),eventRequest.getImage());
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(UUID id, EventRequest eventRequest) throws EventNotFoundByIdException {
        Event event = retrieveEventById(id);
        event.setAll(eventRequest.getEventName(), eventRequest.getCategoryName(), eventRequest.getEventDate(),eventRequest.getEventLocation(),eventRequest.getEventDescription(),eventRequest.getMaxCapacity(),eventRequest.getImage());
        return eventRepository.save(event);
    }

    @Override
    public void deleteEventById(UUID id) throws EventNotFoundByIdException {
        retrieveEventById(id);
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> findByCreatedBy(UUID creator){
        return eventRepository.findByCreatedBy(creator);
    }

    @Override
    public List<Event> filterByString(String search,String filter) throws UserNotFoundByIdException {
        return switch (filter) {
            case "category" -> eventRepository.findByCategoryName(search);
            case "eventName" -> eventRepository.findByEventName(search);
            case "location" -> eventRepository.findByLocation(search);
            case "description" -> eventRepository.findByDescription(search);
            case "creator" -> {
                UUID creator = eventRepository.findCreator(search);
                yield findByCreatedBy(creator);
            }
            default -> eventRepository.searchEvents(search, search, search, search, search);
        };
    }

    @Override
    public List<Event> filterByDate() {
        return eventRepository.filterByDate();
    }

    @Override
    public List<Event> retrieveUserEvents(UUID eventUseriId) throws EventNotFoundByIdException {
        List<Registration> registration = registrationService.retrieveAllRegistrationsByUserId(eventUseriId);
        List<Event> events = new ArrayList<>();
        for(Registration r: registration){
            events.add(retrieveEventById(r.getEventId()));
        }
        return events;
    }
}
