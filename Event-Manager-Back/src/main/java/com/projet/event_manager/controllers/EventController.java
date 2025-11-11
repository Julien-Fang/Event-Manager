package com.projet.event_manager.controllers;

import com.projet.event_manager.dto.EventRequest;
import com.projet.event_manager.exceptions.events.EmptyDescriptionException;
import com.projet.event_manager.exceptions.events.EventAlreadyExistsException;
import com.projet.event_manager.exceptions.events.EventNotFoundByIdException;
import com.projet.event_manager.exceptions.eventusers.UserNotFoundByIdException;
import com.projet.event_manager.models.Event;
import com.projet.event_manager.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(
            summary = "Retrieve all events",
            description = "Return all events"
    )
    @GetMapping
    public ResponseEntity<List<Event>> retrieveAllEvents() {
        List<Event> events = eventService.retrieveAllEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping
    @Operation(
            summary = "Create a new event",
            description = "Create a new event by setting (event name, category name, date, location, description, capacity, image)"
    )
    public ResponseEntity<?> createEvent(
            @Parameter(description = "Request which contains the data of the new event")
            @RequestBody EventRequest eventRequest) throws EmptyDescriptionException, EventAlreadyExistsException {

        Event event = eventService.createEvent(eventRequest);
        return ResponseEntity
                .created(URI.create("events/"+event.getId()))
                .body(event);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update the data of an event",
            description = "Update event data by path variable 'id' and request body 'eventRequest'"
    )
    public ResponseEntity<Event> updateEvent(
            @Parameter(description = "Id of the event to updated")
            @PathVariable UUID id,
            @Parameter(description = "Request which contains new data of the event")
            @RequestBody EventRequest eventRequest) throws EventNotFoundByIdException {

        Event event = eventService.updateEvent(id,eventRequest);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a event by his id",
            description = "Return a event by path variable 'id'"
    )
    public ResponseEntity<Event> retrieveEventById(
            @Parameter(description = "Id of the event to retrieve")
            @PathVariable UUID id) throws EventNotFoundByIdException {
        Event event = eventService.retrieveEventById(id);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete event by his id",
            description = "Delete event by path variable 'id'"
    )
    public ResponseEntity<?> deleteEvent(
            @Parameter(description = "Id of the event to delete")
            @PathVariable UUID id) throws EventNotFoundByIdException {
        eventService.deleteEventById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter/{search}/{filter}")
    @Operation(
            summary = "Filter events by string",
            description = "Return a event by path variable 'search' and 'filter'"
    )
    public ResponseEntity<List<Event>> filterEvents(
            @Parameter(description = "description of the event to search")
            @PathVariable String search,
            @Parameter(description = "description of the event to search")
            @PathVariable String filter) throws UserNotFoundByIdException {
        List<Event> events = eventService.filterByString(search,filter);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/filter/date")
    @Operation(
            summary = "Filter events by date",
            description = "Return a event by date"
    )
    public ResponseEntity<List<Event>> filterEventsByDate() {
        List<Event> events = eventService.filterByDate();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/user/{eventUserId}")
    @Operation(
            summary = "Retrieve all events where a user is registered",
            description = "Return a list of events by an user id"
    )
    public ResponseEntity<List<Event>> filterEventsByDate(
            @Parameter(description = "Id of the user")
            @PathVariable UUID eventUserId) throws EventNotFoundByIdException {
        List<Event> events = eventService.retrieveUserEvents(eventUserId);
        return ResponseEntity.ok(events);
    }
    
}