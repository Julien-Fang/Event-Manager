package com.projet.event_manager.controllers;

import com.projet.event_manager.dto.FeedbackRequest;
import com.projet.event_manager.exceptions.events.EventNotFoundByIdException;
import com.projet.event_manager.exceptions.eventusers.UserNotFoundByIdException;
import com.projet.event_manager.exceptions.feedback.EmptyFeedbackCommentException;
import com.projet.event_manager.exceptions.feedback.FeedbackAlreadyExistsException;
import com.projet.event_manager.exceptions.feedback.FeedbackNotFoundByIdException;
import com.projet.event_manager.models.Feedback;
import com.projet.event_manager.services.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new feedback",
            description = "Create a feedback with the given data"
    )
    public ResponseEntity<?> createFeedback(
            @Parameter(description = "Request which content the given comment and rate")
            @RequestBody FeedbackRequest feedbackRequest) throws FeedbackAlreadyExistsException, EmptyFeedbackCommentException, EventNotFoundByIdException, UserNotFoundByIdException {
        Feedback feedback = feedbackService.createFeedback(feedbackRequest);
        return ResponseEntity.created(URI.create("feedbacks/"+feedback.getId())).body(feedback);
    }

    @GetMapping
    @Operation(
            summary = "Retrieve all feedbacks",
            description = "Retrieve all feedbacks"
    )
    public ResponseEntity<List<Feedback>> retrieveAllFeedbacks(){
        List<Feedback> feedbacks = feedbackService.retrieveAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{eventId}")
    @Operation(
            summary = "Retrieve all feedbacks by event id",
            description = "Retrieve all feedbacks by event id"
    )
    public ResponseEntity<List<Feedback>> retrieveAllFeedbacksByEventId(
            @Parameter(description = "The id of the event")
            @PathVariable("eventId") UUID eventId){
        List<Feedback> feedbacks = feedbackService.retrieveAllFeedbacksByEventId(eventId);
        return ResponseEntity.ok(feedbacks);
    }

    @PutMapping("/{feedbackId}")
    @Operation(
            summary = "Update a feedback",
            description = "Update a feedback by its id and the new feedback data"
    )
    public ResponseEntity<Feedback> updateFeedback(
            @Parameter(description = "Id of the feedback to update")
            @PathVariable("feedbackId") UUID feedbackId, @RequestBody FeedbackRequest feedbackRequest) throws FeedbackNotFoundByIdException, EmptyFeedbackCommentException {
        Feedback feedback = feedbackService.updateFeedback(feedbackId, feedbackRequest);
        return ResponseEntity.ok(feedback);
    }

    @DeleteMapping("/{feedbackId}")
    @Operation(
            summary = "Delete a feedback",
            description = "Delete a feedback by his id"
    )
    public ResponseEntity<?> deleteFeedback(
            @Parameter(description = "Id of the category to retrieve")
            @PathVariable("feedbackId") UUID feedbackId) throws FeedbackNotFoundByIdException {
        feedbackService.deleteFeedbackById(feedbackId);
        return ResponseEntity.ok().build();
    }
}
