package com.projet.event_manager.controllers;

import com.projet.event_manager.dto.RegistrationRequest;
import com.projet.event_manager.exceptions.registrations.RegistrationAlreadyExistsException;
import com.projet.event_manager.exceptions.registrations.RegistrationNotFoundByIdException;
import com.projet.event_manager.models.Registration;
import com.projet.event_manager.services.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    @Operation(
            summary = "Create a registration",
            description = "Create a registration with the given request (eventId, eventUserId)"
    )
    public ResponseEntity<Registration> createRegistration(
            @Parameter(description = "Request which contains the id of the event and the  user")
            @RequestBody RegistrationRequest registrationRequest) throws RegistrationAlreadyExistsException {
        Registration registration = registrationService.createRegistration(registrationRequest);
        return ResponseEntity
                .created(URI.create("registrations/"+registration.getId()))
                .body(registration);
    }

    @DeleteMapping("/{registrationId}")
    @Operation(
            summary = "Delete a registration",
            description = "Delete a registration by id"
    )
    public ResponseEntity<?> deleteRegistrationById(
            @Parameter(description = "Id of the registration to delete")
            @PathVariable("registrationId") UUID registrationId) throws RegistrationNotFoundByIdException {
        registrationService.deleteRegistrationById(registrationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/events/{eventId}")
    @Operation(
            summary = "Retrieve all registrations by event id",
            description = "Retrieve all registrations by event id"
    )
    public ResponseEntity<List<Registration>> retrieveAllRegistrationsByEventId(
            @Parameter(description = "Id of the event")
            @PathVariable UUID eventId){
        List<Registration> registrations = registrationService.retrieveAllRegistrationsByEventId(eventId);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/users/{eventUserId}")
    @Operation(
            summary = "Retrieve all registrations by user id",
            description = "Retrieve all registrations by user id"
    )
    public ResponseEntity<List<Registration>> retrieveAllRegistrationsByUserId(
            @Parameter(description = "Id of the user")
            @PathVariable UUID eventUserId){
        List<Registration> registrations = registrationService.retrieveAllRegistrationsByUserId(eventUserId);
        return ResponseEntity.ok(registrations);
    }
}
