package com.projet.event_manager.services.impl;

import com.projet.event_manager.dto.FeedbackRequest;
import com.projet.event_manager.exceptions.events.EventNotFoundByIdException;
import com.projet.event_manager.exceptions.eventusers.UserNotFoundByIdException;
import com.projet.event_manager.exceptions.feedback.EmptyFeedbackCommentException;
import com.projet.event_manager.exceptions.feedback.FeedbackAlreadyExistsException;
import com.projet.event_manager.exceptions.feedback.FeedbackNotFoundByIdException;
import com.projet.event_manager.models.Feedback;
import com.projet.event_manager.repositories.FeedbackRepository;
import com.projet.event_manager.services.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> retrieveAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback retrieveFeedbackById(UUID id) throws FeedbackNotFoundByIdException {
        return feedbackRepository.findById(id).orElseThrow(() -> new FeedbackNotFoundByIdException(id));
    }

    @Override
    public List<Feedback> retrieveAllFeedbacksByEventId(UUID eventId) {
        return feedbackRepository.findAllByEventId(eventId);
    }

    @Override
    public Feedback createFeedback(FeedbackRequest feedbackRequest) throws FeedbackAlreadyExistsException, EmptyFeedbackCommentException, EventNotFoundByIdException, UserNotFoundByIdException {
        if(feedbackRequest.getComment().trim().isEmpty()){
            throw new EmptyFeedbackCommentException();
        }
        if(feedbackRepository.findAll().stream().anyMatch(feedback -> feedback.getEventId().equals(feedbackRequest.getEventId()) && feedback.getEventUserId().equals(feedbackRequest.getEventUserId()))){
            throw new FeedbackAlreadyExistsException();
        }

        Feedback feedback = new Feedback(feedbackRequest.getEventId(), feedbackRequest.getEventUserId(), feedbackRequest.getComment(), feedbackRequest.getDate(), feedbackRequest.getRate());
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(UUID id, FeedbackRequest feedbackRequest) throws EmptyFeedbackCommentException, FeedbackNotFoundByIdException {
        Feedback feedback = retrieveFeedbackById(id);
        if(feedbackRequest.getComment().trim().isEmpty()){
            throw new EmptyFeedbackCommentException();
        }
        feedback.setComment(feedbackRequest.getComment());
        feedback.setRate(feedbackRequest.getRate());
        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedbackById(UUID id) throws FeedbackNotFoundByIdException {
        retrieveFeedbackById(id);
        feedbackRepository.deleteById(id);
    }
}
