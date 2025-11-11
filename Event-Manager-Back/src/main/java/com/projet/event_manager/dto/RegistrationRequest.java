package com.projet.event_manager.dto;

import java.util.UUID;

public class RegistrationRequest {
    private UUID eventId;
    private UUID userId;

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
