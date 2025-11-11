package com.projet.event_manager.controllers;
import com.projet.event_manager.dto.AuthenticationRequest;
import com.projet.event_manager.dto.EventUserRequest;
import com.projet.event_manager.exceptions.eventusers.*;
import com.projet.event_manager.models.Event;
import com.projet.event_manager.models.EventUser;
import com.projet.event_manager.services.EventUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class EventUserController {

    private final EventUserService eventUserService;

    public EventUserController(EventUserService eventUserService) {
        this.eventUserService = eventUserService;
    }

    @Operation(
            summary = "Retrieve all users",
            description = "Return all users"
    )
    @GetMapping
    public ResponseEntity<List<EventUser>> retrieveAllEvents() {
        List<EventUser> users = eventUserService.retrieveAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a user by his id",
            description = "Retrieve a user by path variable 'id'"
    )
    public ResponseEntity<EventUser> retrieveUserById(
            @Parameter(description = "Id of the user to retrieve")
            @PathVariable UUID id) throws UserNotFoundByIdException {
        EventUser user = eventUserService.retrieveUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(
            summary = "Create a new user",
            description = "Create a user with the given data (username, pseudo, password, email)"
    )
    public ResponseEntity<?> createUser(
            @Parameter(description = "Request which contains the username, pseudo, password and email for create a new user")
            @RequestBody EventUserRequest user) throws PseudoAlreadyExistsException, InvalidEmailException, EmptyDataException {
        EventUser registeredUSer = eventUserService.createUser(user);
        return ResponseEntity.created(URI.create("users/"+registeredUSer.getId())).body(registeredUSer);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a field of user",
            description = "Update a user data: username, pseudo, password, email"
    )
    public ResponseEntity<EventUser> updateUser(
            @Parameter(description = "Id of the user to update")
            @PathVariable UUID id,
            @Parameter(description = "Request which contains the new data of the user")
            @RequestBody EventUserRequest eventUserRequest) throws UserNotFoundByIdException, EmptyDataException{
        EventUser updatedUser = eventUserService.updateUser(id, eventUserRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a user by his id",
            description = "Delete user by path variable 'id'"
    )
    public ResponseEntity<?> deleteUser(
            @Parameter(description = "Id of the user to delete")
            @PathVariable UUID id) throws UserNotFoundByIdException {
        eventUserService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login the user",
            description = "Authenticate a user by his pseudo and password"
    )
    public ResponseEntity<EventUser> login(
            @Parameter(description = "Pseudo and password to use for authentication")
            @RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        EventUser user = eventUserService.authenticateUser(authenticationRequest.getPseudo(), authenticationRequest.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping("events/{eventId}")
    public ResponseEntity<List<EventUser>> retrieveAllUsersByEventId(
            @Parameter(description = "Id of the event to retrieve users")
            @PathVariable UUID eventId) throws UserNotFoundByIdException {
        List<EventUser> users = eventUserService.retrieveAllUsersByEventId(eventId);
        return ResponseEntity.ok(users);
    }
}