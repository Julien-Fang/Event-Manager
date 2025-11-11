package com.projet.event_manager.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @JoinColumn(name = "event_id", nullable = false)
    private UUID eventId;

    @JoinColumn(name = "event_user_id", nullable = false)
    private UUID eventUserId;

    public Registration() {}

    public Registration(UUID eventId, UUID eventUserId) {
        this.id = UUID.randomUUID();
        this.eventId = eventId;
        this.eventUserId = eventUserId;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEventId() {
        return eventId;
    }

        public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getEventUserId() {
        return eventUserId;
    }

    public void setEventUser(UUID eventUserId) {
        this.eventUserId = eventUserId;
    }
}