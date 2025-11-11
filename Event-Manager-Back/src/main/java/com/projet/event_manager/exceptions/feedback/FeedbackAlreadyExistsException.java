package com.projet.event_manager.exceptions.feedback;

public class FeedbackAlreadyExistsException extends Exception{
    public FeedbackAlreadyExistsException() {
        super("You've already give a feedback for this event !!!");
    }
}