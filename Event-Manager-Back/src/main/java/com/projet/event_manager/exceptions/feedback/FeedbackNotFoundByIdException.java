package com.projet.event_manager.exceptions.feedback;

import java.util.UUID;

public class FeedbackNotFoundByIdException extends  Exception {
    public FeedbackNotFoundByIdException(UUID id) {
        super("No feedback with the id : " + id + "have been found!!!");
    }
}