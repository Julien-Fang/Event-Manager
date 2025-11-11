package com.projet.event_manager.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @JoinColumn(name = "event_id", nullable = false)
    private UUID eventId;

    @JoinColumn(name = "event_user_id", nullable = false)
    private UUID eventUserId;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "rate", nullable = false)
    private int rate;

    public Feedback() {}

    public Feedback(UUID eventId, UUID eventUserId, String comment, LocalDateTime date, int rate) {
        this.id = UUID.randomUUID();
        this.eventId = eventId;
        this.eventUserId = eventUserId;
        this.comment = comment;
        this.date = date;
        this.rate = rate;
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

    public void setEventUserId(UUID eventUserId) {
        this.eventUserId = eventUserId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
