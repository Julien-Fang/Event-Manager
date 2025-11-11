package com.projet.event_manager.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @JoinColumn(name="category_name", nullable = false)
    private String categoryName;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "event_location", nullable = false)
    private String eventLocation;

    @Column(name = "event_description", nullable = false)
    private String eventDescription;

    @Column(name = "max_capacity", nullable = false)
    private int maxCapacity;

    @Column(name = "image", nullable = true)
    private byte[] image;

    public Event() {}

    public Event(String eventName, UUID createdBy, LocalDateTime createdOn, String categoryName, LocalDateTime eventDate, String eventLocation, String eventDescription, int maxCapacity, byte[] image){
        this.id = UUID.randomUUID();
        this.eventName = eventName;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.categoryName = categoryName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.maxCapacity = maxCapacity;
        this.image = image;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setAll(String eventName, String categoryName, LocalDateTime eventDate, String eventLocation, String eventDescription, int maxCapacity, byte[] image){
        this.eventName = eventName;
        this.categoryName = categoryName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.maxCapacity = maxCapacity;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
