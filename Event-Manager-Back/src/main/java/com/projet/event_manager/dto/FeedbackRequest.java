package com.projet.event_manager.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class FeedbackRequest {
    private UUID eventId;
    private UUID eventUserId;
    private String comment;
    private LocalDateTime date;
    private int rate;

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
