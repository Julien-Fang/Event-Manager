package com.projet.event_manager.exceptions.feedback;

public class EmptyFeedbackCommentException extends Exception{
    public EmptyFeedbackCommentException(){
        super("Feedback comment is required !!!");
    }
}
