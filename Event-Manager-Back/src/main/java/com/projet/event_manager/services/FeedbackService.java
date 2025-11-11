package com.projet.event_manager.services;

import com.projet.event_manager.dto.FeedbackRequest;
import com.projet.event_manager.exceptions.events.EventNotFoundByIdException;
import com.projet.event_manager.exceptions.eventusers.UserNotFoundByIdException;
import com.projet.event_manager.exceptions.feedback.EmptyFeedbackCommentException;
import com.projet.event_manager.exceptions.feedback.FeedbackAlreadyExistsException;
import com.projet.event_manager.exceptions.feedback.FeedbackNotFoundByIdException;
import com.projet.event_manager.models.Feedback;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {
    List<Feedback> retrieveAllFeedbacks();
    Feedback retrieveFeedbackById(UUID id) throws FeedbackNotFoundByIdException;
    List<Feedback> retrieveAllFeedbacksByEventId(UUID eventId);
    Feedback createFeedback(FeedbackRequest feedbackRequest) throws FeedbackAlreadyExistsException, EmptyFeedbackCommentException, EventNotFoundByIdException, UserNotFoundByIdException;
    Feedback updateFeedback(UUID id, FeedbackRequest feedbackRequest) throws EmptyFeedbackCommentException, FeedbackNotFoundByIdException;
    void deleteFeedbackById(UUID id) throws FeedbackNotFoundByIdException;
}
